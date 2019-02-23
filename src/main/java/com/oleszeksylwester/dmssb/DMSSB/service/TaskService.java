package com.oleszeksylwester.dmssb.DMSSB.service;

import com.oleszeksylwester.dmssb.DMSSB.enums.RouteStates;
import com.oleszeksylwester.dmssb.DMSSB.enums.TaskStates;
import com.oleszeksylwester.dmssb.DMSSB.factory.NameFactory;
import com.oleszeksylwester.dmssb.DMSSB.model.Route;
import com.oleszeksylwester.dmssb.DMSSB.model.Task;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.repository.RouteRepository;
import com.oleszeksylwester.dmssb.DMSSB.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final NameFactory nameFactory;

    @Autowired
    public TaskService(TaskRepository taskRepository, NameFactory nameFactory) {
        this.taskRepository = taskRepository;
        this.nameFactory = nameFactory;
    }

    public void createTask(Route route){

        String routeState = route.getState();

        if(routeState.equals(RouteStates.NOT_STARTED.getState())){

            Task task = new Task.Builder()
                    .owner(route.getOwner())
                    .assignedTo(route.getResponsibleForChecking())
                    .processedDocument(route.getDocumentBeingApproved())
                    .state(TaskStates.ACTIVE.getState())
                    .dueDate(route.getCheckingDueDate())
                    .completionDate(null)
                    .comments("Please check")
                    .parentRoute(route)
                    .build();

            taskRepository.save(task);

            Long taskId = task.getId();
            String name = nameFactory.createName(taskId, "task");
            task.setName(name);
            taskRepository.save(task);

        } else if(routeState.equals(RouteStates.CHECKING.getState())){

            Task task = new Task.Builder()
                    .owner(route.getOwner())
                    .assignedTo(route.getResponsibleForApproving())
                    .processedDocument(route.getDocumentBeingApproved())
                    .state(TaskStates.ACTIVE.getState())
                    .dueDate(route.getCheckingDueDate())
                    .completionDate(null)
                    .comments("Please approve")
                    .parentRoute(route)
                    .build();

            taskRepository.save(task);

            Long taskId = task.getId();
            String name = nameFactory.createName(taskId, "task");
            task.setName(name);
            taskRepository.save(task);
        }
    }

    @Transactional
    public void SaveOrUpdate(Task task){
        taskRepository.save(task);
    }

    @Transactional(readOnly = true)
    public Task findById(Long id){
        return taskRepository.findById(id).orElseThrow(()-> new RuntimeException("There is no task with this id"));
    }

    @Transactional(readOnly = true)
    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Task> findUserTasks(String username){
        List<Task> allTasks = taskRepository.findAll();

        return allTasks.stream()
                .filter(t -> t.getAssignedTo().getUsername().equals(username))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(Long id){
        taskRepository.deleteById(id);
    }

    @Transactional
    public void delete(Task task){
        taskRepository.delete(task);
    }
}
