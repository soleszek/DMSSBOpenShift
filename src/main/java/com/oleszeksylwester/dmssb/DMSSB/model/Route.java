package com.oleszeksylwester.dmssb.DMSSB.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long route_id;
    private String name;
    @ManyToOne(optional = false)
    @JoinColumn(name="owner_id")
    private User owner;
    private String state;
    private LocalDate creationDate;
    private LocalDate finishDate;
    private LocalDate deadline;
    @ManyToOne(optional = false)
    @JoinColumn(name="document_id")
    private Document documentBeingApproved;
    private LocalDate checkingDueDate;
    @ManyToOne(optional = false)
    @JoinColumn(name="checker_id")
    private User responsibleForChecking;
    @ManyToOne(optional = false)
    @JoinColumn(name="approver_id")
    private User responsibleForApproving;
    private String comments;

    public Route() {
    }

    public Route(String name, User owner, String state, LocalDate creationDate, LocalDate finishDate, LocalDate deadline, Document documentBeingApproved, LocalDate checkingDueDate, User responsibleForChecking, User responsibleForApproving, String comments) {
        this.name = name;
        this.owner = owner;
        this.state = state;
        this.creationDate = creationDate;
        this.finishDate = finishDate;
        this.deadline = deadline;
        this.documentBeingApproved = documentBeingApproved;
        this.checkingDueDate = checkingDueDate;
        this.responsibleForChecking = responsibleForChecking;
        this.responsibleForApproving = responsibleForApproving;
        this.comments = comments;
    }

    public Long getId() {
        return route_id;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Document getDocumentBeingApproved() {
        return documentBeingApproved;
    }

    public void setDocumentBeingApproved(Document documentBeingApproved) {
        this.documentBeingApproved = documentBeingApproved;
    }

    public LocalDate getCheckingDueDate() {
        return checkingDueDate;
    }

    public void setCheckingDueDate(LocalDate checkingDueDate) {
        this.checkingDueDate = checkingDueDate;
    }

    public User getResponsibleForChecking() {
        return responsibleForChecking;
    }

    public void setResponsibleForChecking(User responsibleForChecking) {
        this.responsibleForChecking = responsibleForChecking;
    }

    public User getResponsibleForApproving() {
        return responsibleForApproving;
    }

    public void setResponsibleForApproving(User responsibleForApproving) {
        this.responsibleForApproving = responsibleForApproving;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
