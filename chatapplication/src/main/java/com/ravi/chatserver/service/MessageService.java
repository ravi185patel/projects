package com.ravi.chatserver.service;

import com.ravi.chatserver.dao.MessageDao;
import com.ravi.chatserver.entity.Message;
import com.ravi.chatserver.mapper.MessageMapper;
import com.ravi.chatserver.model.MessageDto;
import com.ravi.chatserver.model.MessageRequest;
import com.ravi.chatserver.model.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageDao messageDao;

    public void receivedMessage(MessageRequest messageRequest){
        MessageDto messageDto = MessageMapper.requestToDto(messageRequest);
        Message message = MessageMapper.dtoToEntity(messageDto);
        messageDao.saveMessage(message);
    }

    public MessageResponse retrieveMessage(String id){
        Optional<Message> message = messageDao.getMessage(id);
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
