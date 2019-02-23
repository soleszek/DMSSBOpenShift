package com.oleszeksylwester.dmssb.DMSSB.controller;

import com.oleszeksylwester.dmssb.DMSSB.model.Message;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.repository.MessageRepository;
import com.oleszeksylwester.dmssb.DMSSB.service.MessageService;
import com.oleszeksylwester.dmssb.DMSSB.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class MessageController {

    private static final Logger LOGGER = Logger.getLogger(MessageController.class.getName());

    private MessageRepository messageRepository;
    private MessageService messageService;
    private UserService userService;

    @Autowired
    public MessageController(MessageRepository messageRepository, MessageService messageService, UserService userService) {
        this.messageRepository = messageRepository;
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping("/messages/unread")
    private ModelAndView unreadMessages(){
        ModelAndView mov = new ModelAndView();

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.findByUsername(username);
        List<Message> messages = messageRepository.findAllByReceiverAndIsReadIsFalseAndIsDeletedIsFalse(user);

        mov.addObject("user", user);
        mov.addObject("message", new Message());
        mov.addObject("messages", messages);
        mov.setViewName("/messages-unread");
        return mov;
    }

    @GetMapping("/messages/received")
    private ModelAndView allMessages(){
        ModelAndView mov = new ModelAndView();

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.findByUsername(username);
        List<Message> messages = messageRepository.findAllByReceiverAndIsReadIsTrueAndIsDeletedIsFalse(user);

        Long newMessagesCount = messageRepository.countMessagesByReceiverAndIsReadIsFalse(user);

        mov.addObject("newMessagesCount", newMessagesCount);
        mov.addObject("user", user);
        mov.addObject("message", new Message());
        mov.addObject("messages", messages);
        mov.setViewName("/messages-received");
        return mov;
    }

    @GetMapping("/messages/sent")
    private ModelAndView sentMessages(){
        ModelAndView mov = new ModelAndView();

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.findByUsername(username);
        List<Message> messages = messageRepository.findAllBySenderAndIsDeletedIsFalse(user);
        Long newMessagesCount = messageRepository.countMessagesByReceiverAndIsReadIsFalse(user);

        mov.addObject("newMessagesCount", newMessagesCount);
        mov.addObject("user", user);
        mov.addObject("message", new Message());
        mov.addObject("messages", messages);
        mov.setViewName("/messages-sent");
        return mov;
    }

    @GetMapping("/messages/deleted")
    private ModelAndView deletedMessages(){
        ModelAndView mov = new ModelAndView();

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.findByUsername(username);
        List<Message> messages = messageRepository.findAllBySenderAndReceiverAndIsDeletedIsTrue(user, user);
        Long newMessagesCount = messageRepository.countMessagesByReceiverAndIsReadIsFalse(user);

        mov.addObject("newMessagesCount", newMessagesCount);
        mov.addObject("user", user);
        mov.addObject("message", new Message());
        mov.addObject("messages", messages);
        mov.setViewName("/messages-deleted");
        return mov;
    }

    @PostMapping("new/message/{userId}")
    private ModelAndView sendMessage(@ModelAttribute("message") Message message, @RequestParam("content") String content, @RequestParam("username") String username, @PathVariable("userId") Long userId){
        ModelAndView mov = new ModelAndView();

        Message oneMessage = messageService.SaveOrUpdate(message, userId, username, content);

        String currentUser;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            currentUser = ((UserDetails)principal).getUsername();
        } else {
            currentUser = principal.toString();
        }
        User user = userService.findByUsername(currentUser);
        Long newMessagesCount = messageRepository.countMessagesByReceiverAndIsReadIsFalse(user);

        mov.addObject("newMessagesCount", newMessagesCount);
        mov.addObject("oneMessage", oneMessage);
        mov.setViewName("message");
        return mov;
    }

    @GetMapping("/messages-unread/{message_id}")
    private ModelAndView messagesUnread(@PathVariable("message_id") Long message_id){
        ModelAndView mov = new ModelAndView();

        Message oneMessage = messageService.markAsRead(message_id);

        mov.addObject("oneMessage", oneMessage);
        mov.setViewName("message");
        return mov;
    }

    @GetMapping("/message/{message_id}")
    private ModelAndView message(@PathVariable("message_id") Long message_id){
        ModelAndView mov = new ModelAndView();

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.findByUsername(username);

        Message oneMessage = messageRepository.getOne(message_id);
        Long newMessagesCount = messageRepository.countMessagesByReceiverAndIsReadIsFalse(user);

        mov.addObject("newMessagesCount", newMessagesCount);
        mov.addObject("oneMessage", oneMessage);
        mov.setViewName("message");
        return mov;
    }

    @GetMapping("/trash/message/{message_id}")
    private ModelAndView deleteMessage(@PathVariable("message_id") Long message_id){
        ModelAndView mov = new ModelAndView();

        messageService.moveToTrash(message_id);

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.findByUsername(username);
        List<Message> messages = messageRepository.findAllByReceiverAndIsReadIsTrueAndIsDeletedIsFalse(user);

        Long newMessagesCount = messageRepository.countMessagesByReceiverAndIsReadIsFalse(user);

        mov.addObject("newMessagesCount", newMessagesCount);
        mov.addObject("user", user);
        mov.addObject("message", new Message());
        mov.addObject("messages", messages);
        mov.setViewName("/messages-received");
        return mov;
    }

    @PostMapping("/trash/messages")
    private ModelAndView deleteMessages(@RequestParam(required=false, name="messagesChecked") List<Long> messagesChecked){
        ModelAndView mov = new ModelAndView();

        messageService.moveManyToTrash(messagesChecked);

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.findByUsername(username);
        List<Message> messages = messageRepository.findAllByReceiverAndIsReadIsTrueAndIsDeletedIsFalse(user);

        Long newMessagesCount = messageRepository.countMessagesByReceiverAndIsReadIsFalse(user);

        mov.addObject("newMessagesCount", newMessagesCount);
        mov.addObject("user", user);
        mov.addObject("message", new Message());
        mov.addObject("messages", messages);
        mov.setViewName("/messages-received");
        return mov;
    }
}
