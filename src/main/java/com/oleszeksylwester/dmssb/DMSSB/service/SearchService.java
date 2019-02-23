package com.oleszeksylwester.dmssb.DMSSB.service;

import com.oleszeksylwester.dmssb.DMSSB.model.Document;
import com.oleszeksylwester.dmssb.DMSSB.model.Route;
import com.oleszeksylwester.dmssb.DMSSB.model.Task;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final DocumentService documentService;
    private final RouteService routeService;
    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public SearchService(DocumentService documentService, RouteService routeService, TaskService taskService, UserService userService) {
        this.documentService = documentService;
        this.routeService = routeService;
        this.taskService = taskService;
        this.userService = userService;
    }

    public List<Document> searchDocuments(String phrase){
        List<Document> documents = documentService.findAll();

        return documents.stream()
                .filter(d ->
                                d.getName().equals(phrase) ||
                                d.getType().equals(phrase) ||
                                d.getTitle().equals(phrase) ||
                                d.getDescription().equals(phrase) ||
                                d.getState().equals(phrase) ||
                                d.getOwner().getUsername().equals(phrase))
                .collect(Collectors.toList());
    }

    public List<Document> advancedSearchDocuments(String name, String title, String doctype, String revision, String docstate, String owner, String creationDate, String lastModified, String description){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<Document> documents = documentService.findAll();

        return documents.stream()
                .filter(!name.isEmpty() ? e -> e.getName().equals(name) : e -> true)
                .filter(!title.isEmpty() ? e -> e.getTitle().equals(title) : e -> true)
                .filter(!doctype.isEmpty() ? e -> e.getType().equals(doctype) : e -> true)
                .filter(!revision.isEmpty() ? e -> e.getRevision() == Integer.valueOf(revision) : e -> true)
                .filter(!docstate.isEmpty() ? e -> e.getState().equals(docstate) : e -> true)
                .filter(!owner.isEmpty() ? e -> e.getOwner().getUsername().equals(owner) : e -> true)
                .filter(!creationDate.isEmpty() ? e -> e.getCreationDate().equals(LocalDate.parse(creationDate, formatter)) : e -> true)
                .filter(!lastModified.isEmpty() ? e -> e.getLastModification().equals(LocalDate.parse(lastModified, formatter)) : e -> true)
                .filter(!description.isEmpty() ? e -> e.getDescription().equals(description) : e -> true)
                .collect(Collectors.toList());
    }

    public List<Route> advancedSearchRoute(String name, String owner, String documentBeingApproved, String routestate, String checkingDueDate, String responsibleForChecking, String deadline, String responsibleForApproving, String comments, String creationDate, String finishDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<Route> routes = routeService.findAll();

        return routes.stream()
                .filter(!name.isEmpty() ? e -> e.getName().equals(name) : e -> true)
                .filter(!owner.isEmpty() ? e -> e.getOwner().getUsername().equals(owner) : e -> true)
                .filter(!documentBeingApproved.isEmpty() ? e -> e.getDocumentBeingApproved().getName().equals(documentBeingApproved) : e -> true)
                .filter(!routestate.isEmpty() ? e -> e.getState().equals(routestate) : e -> true)
                .filter(!checkingDueDate.isEmpty() ? e -> e.getCheckingDueDate().equals(LocalDate.parse(checkingDueDate, formatter)) : e -> true)
                .filter(!responsibleForChecking.isEmpty() ? e -> e.getResponsibleForChecking().getUsername().equals(responsibleForChecking) : e -> true)
                .filter(!deadline.isEmpty() ? e -> e.getDeadline().equals(LocalDate.parse(deadline, formatter)) : e -> true)
                .filter(!responsibleForApproving.isEmpty() ? e -> e.getResponsibleForApproving().getUsername().equals(responsibleForApproving) : e -> true)
                .filter(!comments.isEmpty() ? e -> e.getComments().equals(comments) : e -> true)
                .filter(!creationDate.isEmpty() ? e -> e.getCreationDate().equals(LocalDate.parse(creationDate, formatter)) : e -> true)
                .filter(!finishDate.isEmpty() ? e -> e.getFinishDate().equals(LocalDate.parse(finishDate, formatter)) : e -> true)
                .collect(Collectors.toList());
    }

    public List<Task> advancedSearchTask(String name, String owner, String assignedTo, String processedDocument, String state, String dueDate, String completionDate, String comment, String parentRoute){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<Task> tasks = taskService.findAll();

        return tasks.stream()
                .filter(!name.isEmpty() ? e -> e.getName().equals(name) : e -> true)
                .filter(!owner.isEmpty() ? e -> e.getOwner().getUsername().equals(owner) : e -> true)
                .filter(!assignedTo.isEmpty() ? e -> e.getAssignedTo().getUsername().equals(assignedTo) : e -> true)
                .filter(!processedDocument.isEmpty() ? e -> e.getProcessedDocument().getName().equals(processedDocument) : e -> true)
                .filter(!state.isEmpty() ? e -> e.getState().equals(state) : e -> true)
                .filter(!owner.isEmpty() ? e -> e.getOwner().getUsername().equals(owner) : e -> true)
                .filter(!dueDate.isEmpty() ? e -> e.getDueDate().equals(LocalDate.parse(dueDate, formatter)) : e -> true)
                .filter(!completionDate.isEmpty() ? e -> e.getCompletionDate().equals(LocalDate.parse(completionDate, formatter)) : e -> true)
                .filter(!comment.isEmpty() ? e -> e.getComments().equals(comment) : e -> true)
                .filter(!parentRoute.isEmpty() ? e -> e.getParentRoute().getName().equals(parentRoute) : e -> true)
                .collect(Collectors.toList());
    }

    public List<User> advancedSearchUser(String name, String firstName, String lastName, String role, String username){

        List<User> users = userService.findAll();

        return users.stream()
                .filter(!name.isEmpty() ? e -> e.getName().equals(name) : e -> true)
                .filter(!firstName.isEmpty() ? e -> e.getFirstName().equals(firstName) : e -> true)
                .filter(!lastName.isEmpty() ? e -> e.getLastName().equals(lastName) : e -> true)
                .filter(!role.isEmpty() ? e -> e.getRoles().stream().anyMatch(r -> r.getRole().equals(role)) : e -> true)
                .filter(!username.isEmpty() ? e -> e.getUsername().equals(username) : e -> true)
                .collect(Collectors.toList());
    }
}
