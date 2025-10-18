package com.ravi.chat.service;


import com.ravi.chat.dao.MessageDao;
import com.ravi.chat.entity.Message;
import com.ravi.chat.mapper.MessageMapper;
import com.ravi.chat.model.MessageDto;
import com.ravi.chat.model.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private KafkaMessageService kafkaMessageService;

    @Autowired
    private MessageDao messageDao;

    public void receivedMessage(MessageRequest messageRequest){
        MessageDto messageDto = MessageMapper.requestToDto(messageRequest);
        Message message = MessageMapper.dtoToEntity(messageDto);
        message = messageDao.saveMessage(message);
        kafkaMessageService.sendMessage(message.getId());
    }

}
