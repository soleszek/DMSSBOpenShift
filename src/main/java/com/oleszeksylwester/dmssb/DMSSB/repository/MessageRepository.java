package com.oleszeksylwester.dmssb.DMSSB.repository;

import com.oleszeksylwester.dmssb.DMSSB.model.Message;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByReceiverAndIsReadIsFalseAndIsDeletedIsFalse(User receiver);
    List<Message> findAllByReceiverAndIsReadIsTrueAndIsDeletedIsFalse(User receiver);
    List<Message> findAllBySenderAndIsDeletedIsFalse(User sender);
    List<Message> findAllBySenderAndReceiverAndIsDeletedIsTrue(User sender, User receiver);
    Long countMessagesByReceiverAndIsReadIsFalse(User receiver);
}
