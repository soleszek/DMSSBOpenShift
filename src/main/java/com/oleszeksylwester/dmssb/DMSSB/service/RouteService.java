package com.oleszeksylwester.dmssb.DMSSB.service;

import com.oleszeksylwester.dmssb.DMSSB.enums.DocumentStates;
import com.oleszeksylwester.dmssb.DMSSB.enums.ObjectTypes;
import com.oleszeksylwester.dmssb.DMSSB.enums.RouteStates;
import com.oleszeksylwester.dmssb.DMSSB.enums.TaskStates;
import com.oleszeksylwester.dmssb.DMSSB.factory.NameFactory;
import com.oleszeksylwester.dmssb.DMSSB.model.Document;
import com.oleszeksylwester.dmssb.DMSSB.model.Route;
import com.oleszeksylwester.dmssb.DMSSB.model.Task;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.repository.DocumentRepository;
import com.oleszeksylwester.dmssb.DMSSB.repository.RouteRepository;
import com.oleszeksylwester.dmssb.DMSSB.repository.TaskRepository;
import com.oleszeksylwester.dmssb.DMSSB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class RouteService {

    private static final Logger LOGGER = Logger.getLogger(RouteService.class.getName());

    private final RouteRepository routeRepository;
    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;
    private final NameFactory nameFactory;
    private final TaskService taskService;
    private final TaskRepository taskRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository, UserRepository userRepository, DocumentRepository documentRepository, NameFactory nameFactory, TaskService taskService, TaskRepository taskRepository) {
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
        this.documentRepository = documentRepository;
        this.nameFactory = nameFactory;
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    @Transactional
    public Route SaveOrUpdate(Route route, String checkingDueDateString, String deadlineString, Long id){

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User owner = userRepository.findByUsername(username);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate checkingDueDate = LocalDate.parse(checkingDueDateString, formatter);
        LocalDate deadline = LocalDate.parse(deadlineString, formatter);

        Document document = documentRepository.getOne(id);

        route.setOwner(owner);
        route.setState(RouteStates.NOT_STARTED.getState());
        route.setCreationDate(LocalDate.now());
        route.setCheckingDueDate(checkingDueDate);
        route.setDeadline(deadline);
        route.setDocumentBeingApproved(document);

        routeRepository.save(route);

        Long routeId = route.getId();
        String name = nameFactory.createName(routeId, ObjectTypes.ROUTE.getObjectType());

        route.setName(name);

        routeRepository.save(route);

        return route;
    }

    @Transactional(readOnly = true)
    public Route findById(Long id){
        return routeRepository.findById(id).orElseThrow(()-> new RuntimeException("There is no route with this id"));
    }

    @Transactional(readOnly = true)
    public List<Route> findAll(){
        return routeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Route> findRoutesOfDocument(Long documentId){
        List<Route> allRoutes = routeRepository.findAll();

        return allRoutes.stream()
                .filter(r -> r.getDocumentBeingApproved().getId().equals(documentId))
                .collect(Collectors.toList());
    }

    @Transactional
    public Route promote(Long id){

        Route route = routeRepository.getOne(id);

        if(route.getState().equals(RouteStates.NOT_STARTED.getState())){
            taskService.createTask(route);
            route.setState(RouteStates.CHECKING.getState());
            routeRepository.save(route);

        } else if (route.getState().equals(RouteStates.CHECKING.getState())){
            Long promotedDocumentId = route.getDocumentBeingApproved().getId();
            Document document = documentRepository.getOne(promotedDocumentId);
            document.setState(DocumentStates.FROZEN.getState());
            documentRepository.save(document);

            taskService.createTask(route);

            route.setState(RouteStates.APPROVING.getState());
            routeRepository.save(route);

        } else if (route.getState().equals(RouteStates.APPROVING.getState())){
            Long promotedDocumentId = route.getDocumentBeingApproved().getId();
            Document document = documentRepository.getOne(promotedDocumentId);
            document.setState(DocumentStates.RELEASED.getState());
            documentRepository.save(document);

            route.setState(RouteStates.COMPLETED.getState());
            route.setFinishDate(LocalDate.now());
            routeRepository.save(route);
        }

        return route;
    }

    @Transactional
    public Task completeTask(Long taskId){
        Task task = taskRepository.getOne(taskId);
        Long routeId = task.getParentRoute().getId();
        promote(routeId);

        task.setState(TaskStates.COMPLETED.getState());
        task.setCompletionDate(LocalDate.now());
        taskRepository.save(task);

        return task;
    }

    @Transactional
    public Route update(Long routeId, String checkingDueDateString, String responsibleForChecking, String deadlineString, String responsibleForApproving, String comments){
        Route route = findById(routeId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate checkingDueDate = LocalDate.parse(checkingDueDateString, formatter);
        LocalDate deadline = LocalDate.parse(deadlineString, formatter);

        User userRespForChecking = userRepository.findByUsername(responsibleForChecking);
        User userRespForApproving = userRepository.findByUsername(responsibleForApproving);

        route.setCheckingDueDate(checkingDueDate);
        route.setResponsibleForChecking(userRespForChecking);
        route.setDeadline(deadline);
        route.setResponsibleForApproving(userRespForApproving);
        route.setComments(comments);

        routeRepository.save(route);

        return route;
    }

    @Transactional
    public void deleteById(Long id){
        routeRepository.deleteById(id);
    }

    @Transactional
    public void delete(Route route){
        routeRepository.delete(route);
    }
}
