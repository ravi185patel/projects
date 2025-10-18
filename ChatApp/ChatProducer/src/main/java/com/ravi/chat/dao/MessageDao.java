package com.ravi.chat.dao;

import com.ravi.chat.entity.Message;
import com.ravi.chat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MessageDao {

    @Autowired
    private MessageRepository messageRepository;

    public Message saveMessage(Message message){
        return messageRepository.save(message);
    }

    public Optional<Message> getMessage(String id){
        return messageRepository.findById(id);
    }

}
