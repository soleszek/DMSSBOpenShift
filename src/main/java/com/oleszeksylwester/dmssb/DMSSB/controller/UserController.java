package com.oleszeksylwester.dmssb.DMSSB.controller;

import com.oleszeksylwester.dmssb.DMSSB.model.Task;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.repository.DocumentRepository;
import com.oleszeksylwester.dmssb.DMSSB.repository.MessageRepository;
import com.oleszeksylwester.dmssb.DMSSB.repository.RouteRepository;
import com.oleszeksylwester.dmssb.DMSSB.service.TaskService;
import com.oleszeksylwester.dmssb.DMSSB.service.UserService;
import com.oleszeksylwester.dmssb.DMSSB.utils.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    TaskService taskService;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    @Qualifier("userValidator")
    private UserValidator userValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    @RequestMapping("/")
    public String index(){
        return "Greetings from Spring Boot";
    }

    @GetMapping("/dashboard")
    private ModelAndView showDashboard() {
        ModelAndView mov = new ModelAndView();

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.findByUsername(username);

        Long documentCount = documentRepository.count();
        Long routeCount = routeRepository.count();
        Long newMessagesCount = messageRepository.countMessagesByReceiverAndIsReadIsFalse(user);

        List<Task> tasks = taskService.findUserTasks(username);
        int userTasksCount = tasks.size();

        mov.addObject("newMessagesCount", newMessagesCount);
        mov.addObject("documentCount", documentCount);
        mov.addObject("routeCount", routeCount);
        mov.addObject("tasks", tasks);
        mov.addObject("userTasksCount", userTasksCount);

        mov.setViewName("dashboard");

        return mov;
    }

    @GetMapping("/login")
    private String showLogin() {
        return "login";
    }

    @GetMapping("/adminpanel")
    private String showAdminPanel() {
        return "adminpanel";
    }

    @GetMapping("/registration")
    private String showRegistration(Model model) {
        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping(value = "/registerUser")
    private ModelAndView registerUser(@ModelAttribute("user") @Validated User user, BindingResult bindingResult, @RequestParam String role) {

        ModelAndView mov = new ModelAndView();

        User existUser = userService.findByUsername(user.getUsername());

        if (existUser != null) {
            bindingResult.rejectValue("username", "error.user", "User with this login already exist");
            mov.setViewName("registration");

            return mov;
        }

        if (bindingResult.hasErrors()) {
            mov.setViewName("registration");

            return mov;

        } else {

            userService.saveOrUpdate(user, role);

            mov.addObject("user", user);
            mov.setViewName("user");

            return mov;
        }

    }

    @GetMapping(value = "/userdetails")
    private ModelAndView displayUserDetails(){
        ModelAndView mov = new ModelAndView();

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userService.findByUsername(username);

        mov.addObject("user", user);
        mov.setViewName("user");

        return mov;
    }

    @GetMapping(value = "/all/users")
    private ModelAndView displayAllUsers(){
        ModelAndView mov = new ModelAndView();

        List<User> users = userService.findAll();

        mov.addObject("users", users);
        mov.setViewName("allusers");

        return mov;
    }

    @GetMapping(value = "/userdetails/{userId}")
    private ModelAndView displayUserDetailsById(@PathVariable("userId") Long userId){
        ModelAndView mov = new ModelAndView();

        User user = userService.findById(userId);

        mov.addObject("user", user);
        mov.setViewName("user");

        return mov;
    }

    @GetMapping(value = "/delete/user/{userId}")
    private ModelAndView deleteUser(@PathVariable("userId") Long userId){
        ModelAndView mov = new ModelAndView();

        userService.deleteById(userId);

        List<User> users = userService.findAll();

        mov.addObject("users", users);
        mov.setViewName("allusers");

        return mov;
    }

    @PostMapping(value = "/update/user/{userId}")
    private ModelAndView updateUser(@PathVariable("userId") Long userId, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("role") String roleName){
        ModelAndView mov = new ModelAndView();

        User user = userService.update(userId, firstName, lastName, roleName);

        mov.addObject(user);
        mov.setViewName("user");

        return mov;
    }

    @GetMapping(value = "/status/user/{userId}")
    private ModelAndView changeUserStatus(@PathVariable("userId") Long userId){
        ModelAndView mov = new ModelAndView();

        User user = userService.changeUserStatus(userId);

        mov.addObject(user);
        mov.setViewName("user");

        return mov;
    }
}
