package com.harsh.webportfolio.controller;

import com.harsh.webportfolio.model.ContactMessage;
import com.harsh.webportfolio.repository.ContactRepository;
import com.harsh.webportfolio.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class PortfolioController {

    private static final Logger logger = LoggerFactory.getLogger(PortfolioController.class);

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private PortfolioService portfolioService;

    @Value("${portfolio.profile.name}")
    private String profileName;
    @Value("${portfolio.profile.title}")
    private String profileTitle;
    @Value("${portfolio.profile.subtitle}")
    private String profileSubtitle;
    @Value("${portfolio.resume.path}")
    private String resumePath;
    @Value("${portfolio.social.instagram.url}")
    private String instagramUrl;
    @Value("${portfolio.social.github.url}")
    private String githubUrl;
    @Value("${portfolio.social.linkedin.url}")
    private String linkedinUrl;
    @Value("${portfolio.social.email.url}")
    private String emailUrl;
    @Value("${portfolio.contact.phone}")
    private String phoneNumber;
    @Value("${portfolio.avatar.path}")
    private String avatarPath;


    @GetMapping("/")
    public String index(Model model) {
        if (!model.containsAttribute("contactMessage")) {
            model.addAttribute("contactMessage", new ContactMessage());
        }
        populateSharedProfile(model);
        // Removed extra data attributes as they are no longer displayed on index.html
        // model.addAttribute("skills", portfolioService.getSkills());
        // model.addAttribute("stats", portfolioService.getStats());
        // model.addAttribute("highlights", portfolioService.getHighlights());
        // model.addAttribute("testimonials", portfolioService.getTestimonials());
        // model.addAttribute("certificates", portfolioService.getCertificates());
        // model.addAttribute("educationTimeline", portfolioService.getEducationTimeline());
        // model.addAttribute("services", portfolioService.getServices());
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        populateSharedProfile(model);
        model.addAttribute("skills", portfolioService.getSkills());
        model.addAttribute("highlights", portfolioService.getHighlights());
        model.addAttribute("educationTimeline", portfolioService.getEducationTimeline());
        return "about";
    }

    @GetMapping("/experience")
    public String experience() {
        return "experience";
    }

    @GetMapping("/projects")
    public String projects(Model model) {
        model.addAttribute("projects", portfolioService.getProjects());
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
            logger.error("Error submitting contact message: {}", e.getMessage(), e);
            redirectAttributes.addFlashAttribute("error", "Technical error occurred. Please try again or email me directly.");
        }
        return "redirect:/#contact";
    }

    @GetMapping("/admin/messages")
    public String viewMessages(Model model) {
        model.addAttribute("messages", contactRepository.findAll());
        return "admin-messages";
    }

    private void populateSharedProfile(Model model) {
        model.addAttribute("profileName", profileName);
        model.addAttribute("profileTitle", profileTitle);
        model.addAttribute("profileSubtitle", profileSubtitle);
        model.addAttribute("resumePath", resumePath);
        model.addAttribute("instagramUrl", instagramUrl);
        model.addAttribute("githubUrl", githubUrl);
        model.addAttribute("linkedinUrl", linkedinUrl);
        model.addAttribute("emailUrl", emailUrl);
        model.addAttribute("phoneNumber", phoneNumber);
        model.addAttribute("avatarPath", avatarPath); // Keep avatarPath for other pages if needed, or remove if not used anywhere else
    }
}
