package com.dolbunmark.dto;

import com.dolbunmark.domain.Category;
import com.dolbunmark.enums.Urgency;

public class CreateTicketDto {
    private String name;
    private Category category;
    private String description;
    private Urgency urgency;
    private String desired_resolution_date;
    private String Comment;
    // Attachment
//    private String created_on;
//    private String assignee;
//    private String owner;
//    private State state;
//    private String approver;


    public CreateTicketDto() {
    }

    public CreateTicketDto( String name, Category category, String description, Urgency urgency, String desired_resolution_date, String comment) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.urgency = urgency;
        this.desired_resolution_date = desired_resolution_date;
        Comment = comment;
    }


    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public String getDesired_resolution_date() {
        return desired_resolution_date;
    }

    public String getComment() {
        return Comment;
    }
}


