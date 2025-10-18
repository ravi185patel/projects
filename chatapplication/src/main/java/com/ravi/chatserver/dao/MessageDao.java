package com.ravi.chatserver.dao;

import com.ravi.chatserver.entity.Message;
import com.ravi.chatserver.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MessageDao {

    @Autowired
    private MessageRepository messageRepository;

    public void saveMessage(Message message){
        messageRepository.save(message);
    }

    public Optional<Message> getMessage(String id){
        return messageRepository.findById(id);
    }

}
