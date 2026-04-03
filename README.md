# Professional Full Stack Portfolio - Harsh Goyal

A modern, responsive portfolio website built using Java Spring Boot, Thymeleaf (for dynamic rendering), and MySQL.

## Features
- **Hero Section**: Personalized branding and primary CTAs.
- **Projects**: Detailed case studies of major projects like Stock Management, AI Chatbot, etc.
- **Skills**: Categorized technical expertise.
- **Contact Form**: Functional form that stores messages in a MySQL database.
- **Responsive Design**: Mobile-friendly UI using Bootstrap.
- **Social Integration**: GitHub, LinkedIn, and WhatsApp links.

## Tech Stack
- **Backend**: Java 17, Spring Boot 3, Spring Data JPA, Thymeleaf.
- **Frontend**: HTML5, CSS3, JavaScript, Bootstrap 5.
- **Database**: MySQL.

## Setup Instructions

### 1. Prerequisites
- Java 17 or higher
- Maven
- MySQL Server

### 2. Database Configuration
1. Open MySQL Workbench or your preferred SQL tool.
2. Create a database named `portfolio_db`:
   ```sql
   CREATE DATABASE portfolio_db;
   ```
3. Update `src/main/resources/application.properties` with your MySQL username and password:
   ```properties
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

### 3. Running the Application
1. Open the project in your IDE (IntelliJ IDEA or VS Code).
2. Run the `WebportfolioApplication.java` file.
3. Alternatively, use terminal:
   ```bash
   mvn spring-boot:run
   ```
4. Visit `http://localhost:8080` in your browser.

### 4. Project Structure
- `com.harsh.webportfolio.controller`: Portfolio logic and form handling.
- `com.harsh.webportfolio.model`: Entity and Data objects.
- `com.harsh.webportfolio.repository`: JPA repository for contact messages.
- `src/main/resources/templates/index.html`: The main frontend template.
