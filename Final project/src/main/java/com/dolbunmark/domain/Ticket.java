package com.dolbunmark.domain;

import com.dolbunmark.enums.State;
import com.dolbunmark.enums.Urgency;

import javax.persistence.*;
import java.sql.Date;


@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "data")
    private Date created_on;

    @Column
    private Date desired_resolution_date;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "state_id")
    @Enumerated(EnumType.ORDINAL)
    private State state;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "urgency_id")
    @Enumerated(EnumType.ORDINAL)
    private Urgency urgency;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "approver_id")
    private User approver;

    public Ticket() {
    }

    public Ticket(String name, String description, Date created_on, Date desired_resolution_date, User assignee, User owner, State state, Category category, Urgency urgency, User approver) {
        this.name = name;
        this.description = description;
        this.created_on = created_on;
        this.desired_resolution_date = desired_resolution_date;
        this.assignee = assignee;
        this.owner = owner;
        this.state = state;
        this.category = category;
        this.urgency = urgency;
        this.approver = approver;
    }

    public Ticket(String name, String description, Date created_on, Date desired_resolution_date, User owner, State state, Category category, Urgency urgency) {
        this.name = name;
        this.description = description;
        this.created_on = created_on;
        this.desired_resolution_date = desired_resolution_date;
        this.owner = owner;
        this.state = state;
        this.category = category;
        this.urgency = urgency;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public Date getDesired_resolution_date() {
        return desired_resolution_date;
    }

    public void setDesired_resolution_date(Date desired_resolution_date) {
        this.desired_resolution_date = desired_resolution_date;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
        this.approver = approver;
    }
}
