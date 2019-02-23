package com.oleszeksylwester.dmssb.DMSSB.controller;

import com.oleszeksylwester.dmssb.DMSSB.model.Document;
import com.oleszeksylwester.dmssb.DMSSB.model.Route;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.service.DocumentService;
import com.oleszeksylwester.dmssb.DMSSB.service.RouteService;
import com.oleszeksylwester.dmssb.DMSSB.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class RouteController {

    private static final Logger LOGGER = Logger.getLogger(RouteController.class.getName());

    private RouteService routeService;
    private UserService userService;
    private DocumentService documentService;

    @Autowired
    public RouteController(RouteService routeService, UserService userService, DocumentService documentService) {
        this.routeService = routeService;
        this.userService = userService;
        this.documentService = documentService;
    }

    @GetMapping("/routeslist")
    private ModelAndView displayAllRoutes(){
        ModelAndView mov = new ModelAndView();
        List<Route> allRoutes = routeService.findAll();

        mov.addObject("allRoutes", allRoutes);
        mov.setViewName("routeslist");

        return mov;
    }

    @GetMapping("/document/{documentId}/routes")
    private ModelAndView displayDocumentRoutes(@PathVariable("documentId") Long documentId){
        ModelAndView mov = new ModelAndView();
        Document document = documentService.findById(documentId);

        List<User> checkers = userService.findCheckers();
        List<User> approvers = userService.findApprovers();

        List<Route> routes = routeService.findRoutesOfDocument(documentId);

        mov.addObject("checkers", checkers);
        mov.addObject("approvers", approvers);
        mov.addObject("route", new Route());
        mov.addObject("document", document);
        mov.addObject("routes", routes);
        mov.setViewName("routes");

        return mov;
    }

    @PostMapping("/new/route")
    private ModelAndView createNewRoute(@ModelAttribute("route") Route route,
                                        @RequestParam("checkingDueDateString") String checkingDueDate,
                                        @RequestParam("deadlineString") String deadline,
                                        @RequestParam("documentId") Long id){

        ModelAndView mov = new ModelAndView();

        Route savedRoute = routeService.SaveOrUpdate(route, checkingDueDate, deadline, id);
        Document document = documentService.findById(savedRoute.getDocumentBeingApproved().getId());

        mov.addObject("document", document);
        mov.addObject("route", savedRoute);
        mov.setViewName("route");

        return mov;
    }

    @GetMapping("/route/{routeId}")
    private ModelAndView displayRoute(@PathVariable("routeId") Long routeId){
        ModelAndView mov = new ModelAndView();

        Route route = routeService.findById(routeId);
        Document document = documentService.findById(route.getDocumentBeingApproved().getId());

        mov.addObject("document", document);
        mov.addObject("route", route);
        mov.setViewName("route");

        return mov;
    }

    @GetMapping("/route/start/{routeId}")
    private ModelAndView startRoute(@PathVariable("routeId") Long routeId){
        ModelAndView mov = new ModelAndView();

        Route route = routeService.promote(routeId);
        Document document = documentService.findById(route.getDocumentBeingApproved().getId());

        mov.addObject("document", document);
        mov.addObject("route", route);
        mov.setViewName("route");

        return mov;
    }

    @GetMapping("/route/delete/{routeId}")
    private ModelAndView deleteRoute(@PathVariable("routeId") Long routeId){
        ModelAndView mov = new ModelAndView();

        Route route = routeService.findById(routeId);

        Document document = documentService.findById(route.getDocumentBeingApproved().getId());

        routeService.deleteById(routeId);

        List<User> checkers = userService.findCheckers();
        List<User> approvers = userService.findApprovers();

        List<Route> routes = routeService.findRoutesOfDocument(document.getId());

        mov.addObject("checkers", checkers);
        mov.addObject("approvers", approvers);
        mov.addObject("route", new Route());
        mov.addObject("document", document);
        mov.addObject("routes", routes);
        mov.setViewName("routes");

        return mov;
    }

    @PostMapping("/route/update/{routeId}")
    private ModelAndView updateRoute(@PathVariable("routeId") Long routeId, @RequestParam("checkingDueDate") String checkingDueDate, @RequestParam("responsibleForChecking") String responsibleForChecking, @RequestParam("deadline") String deadline, @RequestParam("responsibleForApproving") String responsibleForApproving, @RequestParam("comments") String comments){
        ModelAndView mov = new ModelAndView();

        Route route = routeService.update(routeId, checkingDueDate, responsibleForChecking, deadline, responsibleForApproving, comments);
        Document document = documentService.findById(route.getDocumentBeingApproved().getId());

        mov.addObject("document", document);
        mov.addObject("route", route);
        mov.setViewName("route");

        return mov;
    }


}
