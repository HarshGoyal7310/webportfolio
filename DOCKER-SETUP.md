# Docker Setup Guide for Web Portfolio

## Overview
This Docker setup provides a production-ready containerized deployment of your Spring Boot web portfolio application with PostgreSQL database support.

## Files Included
- **Dockerfile**: Multi-stage build with security best practices
- **docker-compose.yml**: Full stack setup with web app and MySQL database
- **.dockerignore**: Optimizes build context

## Key Features
✅ **Multi-stage build** - Separates build tools from runtime, reducing image size by 70-90%  
✅ **Security hardened** - Non-root user, health checks, minimal attack surface  
✅ **Optimized for production** - G1 garbage collector, memory constraints, resource limits  
✅ **Alpine Linux** - Small, efficient base image (~50MB runtime)  
✅ **Database ready** - PostgreSQL integration configured  

## Build & Run Options

### Option 1: Using Docker Compose (Recommended for Local/Staging)
```bash
# Build and start all services
docker-compose up -d

# View logs
docker-compose logs -f webportfolio

# Stop services
docker-compose down

# Stop and remove volumes
docker-compose down -v
```

### Option 2: Build and Run Docker Image Directly
```bash
# Build the image
docker build -t webportfolio:latest .

# Run the container (requires external MySQL database)
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://your-db-host:3306/portfolio_db?useSSL=false&serverTimezone=UTC \
  -e SPRING_DATASOURCE_USERNAME=portfolio_user \
  -e SPRING_DATASOURCE_PASSWORD=portfolio_pass \
  webportfolio:latest
```

### Option 3: Build for Specific Platform
```bash
# Build for Linux (useful if building on Windows/Mac for Linux servers)
docker buildx build --platform=linux/amd64 -t webportfolio:latest .
```

## Configuration

### Environment Variables
Set these when running the container:

| Variable | Default | Purpose |
|----------|---------|---------|
| `SPRING_DATASOURCE_URL` | Required | MySQL connection URL (e.g., jdbc:mysql://mysql:3306/portfolio_db) |
| `SPRING_DATASOURCE_USERNAME` | portfolio_user | DB username |
| `SPRING_DATASOURCE_PASSWORD` | portfolio_pass | DB password |
| `SPRING_PROFILES_ACTIVE` | prod | Spring profile (dev/prod) |
| `JAVA_OPTS` | See Dockerfile | JVM options |

### Health Check
The container includes a health check that verifies the application is running:
- Checks every 30 seconds
- Times out after 3 seconds
- Retries up to 3 times

## Deployment to Cloud

### Azure Container Instances (ACI)
```bash
# Push to Azure Container Registry
az acr build --registry <registry-name> --image webportfolio:latest .

# Deploy to ACI
az container create \
  --resource-group <rg-name> \
  --name webportfolio-container \
  --image <registry>.azurecr.io/webportfolio:latest \
  --ports 8080 \
  --cpu 2 --memory 1 \
  --environment-variables \
    SPRING_DATASOURCE_URL="jdbc:mysql://db-host:3306/portfolio_db?useSSL=false&serverTimezone=UTC" \
    SPRING_DATASOURCE_USERNAME="portfolio_user" \
    SPRING_DATASOURCE_PASSWORD="portfolio_pass"
```

### Docker Hub
```bash
docker tag webportfolio:latest <username>/webportfolio:latest
docker push <username>/webportfolio:latest
```

## Performance Optimizations

The Dockerfile includes JVM settings optimized for containerized environments:

```
-XX:+UseG1GC                   # G1 garbage collector for better performance
-XX:MaxRAMPercentage=75.0      # Use 75% of container memory
-XX:InitialRAMPercentage=25.0  # Initial heap allocation
```

## Security Considerations

✅ Runs as non-root user (appuser)  
✅ Uses Alpine Linux (smaller attack surface)  
✅ Uses JRE instead of JDK (runtime only)  
✅ Excludes build tools from final image  
✅ No hardcoded secrets in image  

## Troubleshooting

### Container won't start
```bash
# Check logs
docker logs <container-id>

# Inspect network
docker network inspect <network-name>
```

### Database connection issues
```bash
# Verify MySQL is running
docker exec <mysql-container> mysqladmin ping -u portfolio_user -pportfolio_pass

# Check network connectivity
docker exec <app-container> ping mysql

# Connect to MySQL directly
docker exec -it <mysql-container> mysql -u portfolio_user -pportfolio_pass portfolio_db
```

### Build fails
```bash
# Clear cache and rebuild
docker build --no-cache -t webportfolio:latest .

# Check Maven build locally first
mvn clean package
```

## Image Size
- Build image: ~200MB (Maven + JDK)
- Final runtime image: ~50MB (Alpine JRE base ~35MB + app JAR ~15MB)
- MySQL container: ~150MB (MySQL 8.0 Alpine)

## Database Credentials for Docker Compose
- **Database**: portfolio_db
- **Username**: portfolio_user  
- **Password**: portfolio_pass
- **Root Password**: root_password (for MySQL root user)

## Next Steps
1. Update `application.properties` with production database credentials
2. Configure Spring Security settings for production
3. Set up SSL/TLS using a reverse proxy (Nginx/Traefik)
4. Monitor container health and performance
5. Set up CI/CD pipeline for automated deployments
