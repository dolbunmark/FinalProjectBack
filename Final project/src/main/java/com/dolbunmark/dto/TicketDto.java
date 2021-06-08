package com.dolbunmark.dto;

import com.dolbunmark.enums.State;
import com.dolbunmark.enums.Urgency;

public class TicketDto {
    private long id;
    private String name;
    private String description;
    private String desired_resolution_date;
    private String owner;
    private State state;
    private String category;
    private Urgency urgency;
    private String comment;
    private String approve;
    private String assignee;
    private String create_on;

    public TicketDto() {
    }

    public TicketDto(long id, String name, String desired_resolution_date, State state, Urgency urgency) {
        this.id = id;
        this.name = name;
        this.desired_resolution_date = desired_resolution_date;
        this.state = state;
        this.urgency = urgency;
    }

    public TicketDto(String category) {
        this.category = category;
    }

    public TicketDto( String name, String category, String description, Urgency urgency, String desired_resolution_date, String comment) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.urgency = urgency;
        this.desired_resolution_date = desired_resolution_date;
        this.comment = comment;
    }

    public TicketDto(long id, String name, String description, String desired_resolution_date, String owner, State state, String category, Urgency urgency) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.desired_resolution_date = desired_resolution_date;
        this.owner = owner;
        this.state = state;
        this.category = category;
        this.urgency = urgency;
    }

    public TicketDto(long id, String name, String description, String desired_resolution_date, String owner, State state, String category, Urgency urgency, String approve, String assignee, String create_on) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.desired_resolution_date = desired_resolution_date;
        this.owner = owner;
        this.state = state;
        this.category = category;
        this.urgency = urgency;
        this.approve = approve;
        this.assignee = assignee;
        this.create_on = create_on;
    }

    public String getCreate_on() {
        return create_on;
    }

    public String getApprove() {
        return approve;
    }

    public String getAssignee() {
        return assignee;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDesired_resolution_date() {
        return desired_resolution_date;
    }

    public String getOwner() {
        return owner;
    }

    public State getState() {
        return state;
    }

    public String getCategory() {
        return category;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public String getComment() {
        return comment;
    }
}

