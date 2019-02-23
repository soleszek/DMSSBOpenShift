package com.oleszeksylwester.dmssb.DMSSB.service;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;
import com.oleszeksylwester.dmssb.DMSSB.enums.ObjectTypes;
import com.oleszeksylwester.dmssb.DMSSB.factory.NameFactory;
import com.oleszeksylwester.dmssb.DMSSB.model.Message;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageService {

    private static final Logger LOGGER = Logger.getLogger(MessageService.class.getName());

    private UserService userService;
    private MessageRepository messageRepository;
    private NameFactory nameFactory;

    @Autowired
    public MessageService(UserService userService, MessageRepository messageRepository, NameFactory nameFactory) {
        this.userService = userService;
        this.messageRepository = messageRepository;
        this.nameFactory = nameFactory;
    }

    @Transactional
    public Message SaveOrUpdate(Message message, Long userId, String username, String content){

        message.setContent(content);
        User sender = userService.findById(userId);
        message.setSender(sender);

        User receiver = userService.findByUsername(username);
        message.setReceiver(receiver);
        message.setSendingDate(LocalDate.now());
        message.setIsRead(false);

        messageRepository.save(message);

        Long messageId = message.getMessage_id();
        String name = nameFactory.createName(messageId, ObjectTypes.MESSAGE.getObjectType());
        message.setName(name);

        messageRepository.save(message);

        return message;
    }


    @Transactional
    public Message markAsRead(Long message_id){
        Message message = messageRepository.getOne(message_id);
        message.setIsRead(true);
        Message readMessage = messageRepository.save(message);

        return readMessage;
    }

    @Transactional
    public Message moveToTrash(Long message_id){
        Message message = messageRepository.getOne(message_id);
        message.setIsDeleted(true);
        Message deletedMessage = messageRepository.save(message);

        return deletedMessage;
    }

    @Transactional
    public void moveManyToTrash(List<Long> messagesToTrash){
        messagesToTrash
                .forEach(m -> {
                    Message message = messageRepository.getOne(m);
                    message.setIsDeleted(true);
                    messageRepository.save(message);
                });
    }

    @Transactional(readOnly = true)
    public Message findById(Long id){
        return null;
    }

    @Transactional(readOnly = true)
    public List<Message> findAll(){
        return null;
    }

    @Transactional
    public void deleteById(Long message_id){
        Message message = messageRepository.getOne(message_id);
        messageRepository.delete(message);
    }
}
