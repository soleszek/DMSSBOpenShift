package com.oleszeksylwester.dmssb.DMSSB.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long task_id;
    private String name;
    @ManyToOne(optional = false)
    @JoinColumn(name="owner_id")
    private User owner;
    @ManyToOne(optional = false)
    @JoinColumn(name="assignedTo_id")
    private User assignedTo;
    @ManyToOne(optional = false)
    @JoinColumn(name="document_d")
    private Document processedDocument;
    private String state;
    private LocalDate dueDate;
    private LocalDate completionDate;
    private String comments;
    @ManyToOne(optional = false)
    @JoinColumn(name="route_id")
    private Route parentRoute;

    public Task(){

    }

    public static class Builder {
        private String name;
        private User owner;
        private User assignedTo;
        private Document processedDocument;
        private String state;
        private LocalDate dueDate;
        private LocalDate completionDate;
        private String comments;
        private Route parentRoute;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder owner (User owner){
            this.owner = owner;
            return this;
        }

        public Builder assignedTo (User assignedTo){
            this.assignedTo = assignedTo;
            return this;
        }

        public Builder processedDocument(Document processedDocument){
            this.processedDocument = processedDocument;
            return this;
        }

        public Builder state (String state) {
            this.state = state;
            return this;
        }

        public Builder dueDate (LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder completionDate (LocalDate completionDate) {
            this.completionDate = completionDate;
            return this;
        }

        public Builder comments(String comments) {
            this.comments = comments;
            return this;
        }

        public Builder parentRoute(Route parentRoute) {
            this.parentRoute = parentRoute;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }

    private Task (Builder builder){
        this.name = builder.name;
        this.owner = builder.owner;
        this.assignedTo = builder.assignedTo;
        this.processedDocument = builder.processedDocument;
        this.state = builder.state;
        this.dueDate = builder.dueDate;
        this.completionDate = builder.completionDate;
        this.comments = builder.comments;
        this.parentRoute = builder.parentRoute;
    }

    public Long getId() {
        return task_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Document getProcessedDocument() {
        return processedDocument;
    }

    public void setProcessedDocument(Document processedDocument) {
        this.processedDocument = processedDocument;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Route getParentRoute() {
        return parentRoute;
    }

    public void setParentRoute(Route parentRoute) {
        this.parentRoute = parentRoute;
    }
}
