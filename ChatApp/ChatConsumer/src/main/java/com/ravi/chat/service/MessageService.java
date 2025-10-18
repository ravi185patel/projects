package com.ravi.chat.service;

import com.ravi.chat.dao.MessageDao;
import com.ravi.chat.entity.Message;
import com.ravi.chat.mapper.MessageMapper;
import com.ravi.chat.model.MessageDto;
import com.ravi.chat.model.MessageRequest;
import com.ravi.chat.model.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageDao messageDao;

    public void receivedMessage(MessageDto messageDto){
        Message message = MessageMapper.dtoToEntity(messageDto);
        messageDao.saveMessage(message);
    }

    public Optional<Message> retrieveMessageDto(String id){
        return messageDao.getMessage(id);
    }
    public MessageResponse retrieveMessage(String id){
        Optional<Message> message = retrieveMessageDto(id);
        if(message.isPresent()){
            MessageDto messageDto = MessageMapper.entityToDto(message.get());
            MessageResponse messageResponse = MessageMapper.dtoToResponse(messageDto);
            return messageResponse;
        }else{
            System.out.println("no message found for given id");
        }
        return null;
    }
}
