package com.harsh.webportfolio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private String title;
    private String problemStatement;
    private String techStack;
    private String keyFeatures;
    private String architecture;
    private String challengesSolved;
    private String githubLink;
    private String liveDemo;
}
