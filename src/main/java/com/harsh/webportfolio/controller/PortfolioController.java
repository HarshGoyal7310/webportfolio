package com.harsh.webportfolio.controller;

import com.harsh.webportfolio.model.ContactMessage;
import com.harsh.webportfolio.model.Project;
import com.harsh.webportfolio.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
public class PortfolioController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/")
    public String index(Model model) {
        if (!model.containsAttribute("contactMessage")) {
            model.addAttribute("contactMessage", new ContactMessage());
        }
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/experience")
    public String experience() {
        return "experience";
    }

    @GetMapping("/projects")
    public String projects(Model model) {
        model.addAttribute("projects", getProjects());
        return "projects-list";
    }

    @GetMapping("/education")
    public String education() {
        return "education";
    }

    @PostMapping("/contact")
    public String submitContact(@ModelAttribute ContactMessage contactMessage, RedirectAttributes redirectAttributes) {
        try {
            if (contactMessage.getName().trim().isEmpty() || contactMessage.getEmail().trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Please fill in all required fields.");
                return "redirect:/#contact";
            }
            
            contactRepository.save(contactMessage);
            redirectAttributes.addFlashAttribute("success", "Message received! I'll contact you at " + contactMessage.getEmail() + " shortly.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Technical error occurred. Please try again or email me directly.");
        }
        return "redirect:/#contact";
    }

    // Secure Admin Route (Spring Security will handle authentication)
    @GetMapping("/admin/messages")
    public String viewMessages(Model model) {
        model.addAttribute("messages", contactRepository.findAll());
        return "admin-messages";
    }

    private List<Project> getProjects() {
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
                "https://github.com/HarshGoyal7310/Personality-Development", "#")
        );
    }
}
