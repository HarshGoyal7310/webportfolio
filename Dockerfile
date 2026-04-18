# Build stage
FROM maven:3.9-eclipse-temurin-17-alpine AS build
WORKDIR /build

# Copy POM and cache dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -q

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests -q

# Runtime stage
FROM eclipse-temurin:17-jre-alpine

# Create non-root user for security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

WORKDIR /opt/app

# Copy JAR from build stage
COPY --from=build /build/target/webportfolio-0.0.1-SNAPSHOT.jar app.jar

# Change ownership to non-root user
RUN chown -R appuser:appgroup /opt/app

# Switch to non-root user
USER appuser

# Port exposure
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
    CMD wget --no-verbose --tries=1 --spider http://localhost:8080 || exit 1

# JVM optimizations for containerized environments
ENV JAVA_OPTS="-XX:+UseG1GC -XX:MaxRAMPercentage=75.0 -XX:InitialRAMPercentage=25.0 -Dserver.tomcat.threads.max=200 -Dserver.tomcat.threads.min-spare=10"

# Run application
ENTRYPOINT exec java ${JAVA_OPTS} -jar app.jar
