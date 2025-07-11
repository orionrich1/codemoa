//종효
package com.codemoa.project.domain.employment.entity;

import java.time.LocalDateTime;

public class Employment {
    
    private int employmentId;       // employment_id (PK)
    private String companyName;
    private String title;
    private String jobCategory;
    private String requiredCareer;
    private String location;
    private String content;
    private LocalDateTime dueDate;
    private LocalDateTime regDate;

    // --- Getters and Setters ---
    
    public int getEmploymentId() { return employmentId; }
    public void setEmploymentId(int employmentId) { this.employmentId = employmentId; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getJobCategory() { return jobCategory; }
    public void setJobCategory(String jobCategory) { this.jobCategory = jobCategory; }

    public String getRequiredCareer() { return requiredCareer; }
    public void setRequiredCareer(String requiredCareer) { this.requiredCareer = requiredCareer; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }

    public LocalDateTime getRegDate() { return regDate; }
    public void setRegDate(LocalDateTime regDate) { this.regDate = regDate; }
}