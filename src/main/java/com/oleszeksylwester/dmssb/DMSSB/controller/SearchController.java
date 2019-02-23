package com.oleszeksylwester.dmssb.DMSSB.controller;

import com.oleszeksylwester.dmssb.DMSSB.model.Document;
import com.oleszeksylwester.dmssb.DMSSB.model.Route;
import com.oleszeksylwester.dmssb.DMSSB.model.Task;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class SearchController {

    private static final Logger LOGGER = Logger.getLogger(SearchController.class.getName());

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/advancedsearch")
    private String displayAllDocuments(){

        return "advancedsearch";
    }

    @GetMapping("/quicksearch")
    private ModelAndView displayQuickSearchResults(@RequestParam ("phrase") String phrase){
        ModelAndView mov = new ModelAndView();

        List<Document> results = searchService.searchDocuments(phrase);

        mov.addObject("results", results);
        mov.setViewName("quicksearch");

        return mov;
    }

    @PostMapping("/advancedsearch/document")
    private ModelAndView displayAdvancedSearchDocument(@RequestParam("name") String name,
                                                       @RequestParam("title") String title,
                                                       @RequestParam("doctypeinput") String doctype,
                                                       @RequestParam("docstateinput") String docstate,
                                                       @RequestParam("revision") String revision,
                                                       @RequestParam("owner") String owner,
                                                       @RequestParam("creationDate") String creationDate,
                                                       @RequestParam("lastModified") String lastModified,
                                                       @RequestParam("description") String description) {
        ModelAndView mov = new ModelAndView();

        List<Document> results = searchService.advancedSearchDocuments(name, title, doctype, revision, docstate, owner, creationDate, lastModified, description);

        mov.addObject("results", results);
        mov.setViewName("quicksearch");
        return mov;
    }

    @PostMapping("/advancedsearch/route")
    private ModelAndView displayAdvancedSearchRoute(@RequestParam("name") String name,
                                                    @RequestParam("owner") String owner,
                                                    @RequestParam("documentBeingApproved") String documentBeingApproved,
                                                    @RequestParam("routestate") String routestate,
                                                    @RequestParam("checkingDueDate") String checkingDueDate,
                                                    @RequestParam("responsibleForChecking") String responsibleForChecking,
                                                    @RequestParam("deadline") String deadline,
                                                    @RequestParam("responsibleForApproving") String responsibleForApproving,
                                                    @RequestParam("comments") String comments,
                                                    @RequestParam("creationDate") String creationDate,
                                                    @RequestParam("finishDate") String finishDate){
        ModelAndView mov = new ModelAndView();

        List<Route> results = searchService.advancedSearchRoute(name, owner, documentBeingApproved, routestate, checkingDueDate, responsibleForChecking, deadline, responsibleForApproving, comments, creationDate, finishDate);

        mov.addObject("results", results);
        mov.setViewName("searchresultsroutes");
        return mov;
    }

    @PostMapping("/advancedsearch/task")
    private ModelAndView displayAdvancedSearchTask(@RequestParam("name") String name,
                                                   @RequestParam("owner") String owner,
                                                   @RequestParam("assignedTo") String assignedTo,
                                                   @RequestParam("processedDocument") String processedDocument,
                                                   @RequestParam("state") String state,
                                                   @RequestParam("dueDate") String dueDate,
                                                   @RequestParam("completionDate") String completionDate,
                                                   @RequestParam("comment") String comment,
                                                   @RequestParam("parentRoute") String parentRoute){
        ModelAndView mov = new ModelAndView();

        List<Task> results = searchService.advancedSearchTask(name, owner, assignedTo, processedDocument, state, dueDate, completionDate, comment, parentRoute);

        mov.addObject("results", results);
        mov.setViewName("searchresultstasks");

        return mov;
    }

    @PostMapping("/advancedsearch/user")
    private ModelAndView displayAdvancedSearchUser(@RequestParam("name") String name,
                                                   @RequestParam("firstName") String firstName,
                                                   @RequestParam("lastName") String lastName,
                                                   @RequestParam("role") String role,
                                                   @RequestParam("username") String username){
        ModelAndView mov = new ModelAndView();

        List<User> results = searchService.advancedSearchUser(name, firstName, lastName, role, username);

        mov.addObject("results", results);
        mov.setViewName("searchresultsusers");

        return mov;
    }
}
