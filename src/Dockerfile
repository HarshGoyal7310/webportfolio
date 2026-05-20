FROM eclipse-temurin:17

WORKDIR /app

COPY . .

RUN chmod +x mvnw || true

RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

EXPOSE 8082

CMD ["java", "-jar", "target/*.jar"]

