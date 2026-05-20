package com.harsh.webportfolio.service;

import com.harsh.webportfolio.model.Project;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class PortfolioService {

    public List<Project> getProjects() {
        return Arrays.asList(
                new Project("Complaint & Stock Management System (SECL)",
                        "Developed for SECL to manage industrial complaints and inventory with real-time updates.",
                        "HTML, CSS, JavaScript, PHP, MySQL",
                        "Role-based dashboards, Encrypted authentication, Auto-generated IDs",
                        "Secure Web Portal Architecture",
                        "Improved operational efficiency for South Eastern Coalfields Ltd.",
                        "https://github.com/HarshGoyal7310/Complaint-and-Stock-Management-System-SECL", "#"),
                new Project("Real-time Chat Host",
                        "A WebSocket-based chat application enabling seamless real-time communication.",
                        "HTML, CSS, JavaScript, WebSocket",
                        "Real-time message sync, Responsive cross-device design",
                        "Client-Server WebSocket Architecture",
                        "Successfully handled concurrent connections.",
                        "https://github.com/HarshGoyal7310", "#"),
                new Project("Personality Development Website",
                        "A dynamic infographic platform with interactive UI elements.",
                        "HTML, CSS, JavaScript",
                        "Interactive Infographics, Multi-device compatibility",
                        "Frontend Optimized Responsive Design",
                        "Enhanced user engagement through interactive visuals.",
                        "https://github.com/HarshGoyal7310/Personality-Development", "#"),
                new Project("AI Medibot - Healthcare Assistant",
                        "An AI-powered medical support system providing intelligent healthcare assistance and real-time medical guidance.",
                        "Python, AI/ML, NLP, Flask, JavaScript, HTML, CSS",
                        "AI chatbot for medical queries, Real-time health recommendations, Symptom analyzer, Multi-language support",
                        "Microservices with AI ML Pipeline Architecture",
                        "Deployed on Render for scalability, Integrated advanced NLP models for accurate health guidance.",
                        "https://github.com/HarshGoyal7310/AI-powered-medical-support-system", "https://medibot-healthcare-assistant.onrender.com/")
        );
    }

    public List<String> getSkills() {
        return Arrays.asList(
                "Java",
                "Spring Boot",
                "REST APIs",
                "Thymeleaf",
                "MySQL",
                "PHP",
                "HTML & CSS",
                "JavaScript",
                "WebSocket",
                "Git & GitHub",
                "Responsive UI",
                "Security Basics"
        );
    }

    public List<Map<String, String>> getStats() {
        return Arrays.asList(
                stat("4", "Projects Showcased"),
                stat("1", "Industry Internship"),
                stat("10+", "Core Technologies"),
                stat("Practical", "UI and Backend Focus")
        );
    }

    public List<Map<String, String>> getHighlights() {
        return Arrays.asList(
                stat("Schooling", "Completed 10th and 12th in UP Board Science"),
                stat("SECL Intern", "Worked on complaint and stock management portal"),
                stat("Spring Boot", "Building secure backend and API-driven web apps"),
                stat("Freelance Work", "Creating simple and responsive websites")
        );
    }

    public List<Map<String, String>> getTestimonials() {
        return Arrays.asList(
                stat("SECL Team", "The portal was practical and easy to use for daily work."),
                stat("Freelance Client", "Clear communication and a clean, responsive website."),
                stat("Peer Review", "Steady work quality and attention to detail.")
        );
    }

    public List<Map<String, String>> getCertificates() {
        return Arrays.asList(
                stat("Java & Spring Boot", "Backend development, REST basics, and layered architecture."),
                stat("Web Development", "Responsive UI, HTML/CSS/JavaScript and deployment fundamentals."),
                stat("Database & SQL", "Schema design, query optimization basics, and MySQL workflows.")
        );
    }

    public List<Map<String, String>> getEducationTimeline() {
        return Arrays.asList(
                stat("B.Tech - Computer Science", "Mahatma Gandhi Mission college of engineering and technology Noida (2023 - 2026)"),
                stat("Polytechnic Diploma - ECE", "Govt. Polytechnic Saharanpur (2020 - 2022)"),
                stat("Class 12 - Science (UP Board)", "Saharanpur, Uttar Pradesh (2020)"),
                stat("Class 10 - Science (UP Board)", "Saharanpur, Uttar Pradesh (2018)")
        );
    }

    public List<Map<String, String>> getServices() {
        return Arrays.asList(
                stat("Full Stack Development", "End-to-end websites with a clean frontend and dependable backend."),
                stat("Custom Websites", "Simple, modern websites tailored to a brand or personal portfolio."),
                stat("E-commerce Web Solutions", "Store setups with products, cart flow, and basic admin support."),
                stat("Web Applications", "Interactive web apps built for smooth use and easy maintenance."),
                stat("Authentication & Database Management", "Login systems, roles, and database structure with practical security.")
        );
    }

    private Map<String, String> stat(String value, String label) {
        Map<String, String> item = new LinkedHashMap<>();
        item.put("value", value);
        item.put("label", label);
        return item;
    }
}
