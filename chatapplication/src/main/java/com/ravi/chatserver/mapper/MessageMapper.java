package com.ravi.chatserver.mapper;

import com.ravi.chatserver.entity.Message;
import com.ravi.chatserver.model.MessageDto;
import com.ravi.chatserver.model.MessageRequest;
import com.ravi.chatserver.model.MessageResponse;

import java.time.LocalDateTime;

public class MessageMapper {

    public static MessageDto requestToDto(MessageRequest messageRequest) {
        MessageDto messageDto = new MessageDto();
        messageDto.setSenderId(messageRequest.getSenderId());
        messageDto.setReceiverId(messageRequest.getReceiverId());
        messageDto.setContent(messageRequest.getContent());
        messageDto.setMessageType(messageRequest.getMessageType() != null ? messageRequest.getMessageType() : "TEXT");
        messageDto.setTimestamp(LocalDateTime.now());
        return messageDto;
    }

    public static MessageResponse dtoToResponse(MessageDto messageDto) {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setId(messageDto.getId());
        messageResponse.setSenderId(messageDto.getSenderId());
        messageResponse.setReceiverId(messageDto.getReceiverId());
        messageResponse.setContent(messageDto.getContent());
        messageResponse.setMessageType(messageDto.getMessageType() != null ? messageDto.getMessageType() : "TEXT");
        messageResponse.setTimestamp(LocalDateTime.now());
        return messageResponse;
    }

    public static Message dtoToEntity(MessageDto messageDto) {
        Message message = new Message();
        message.setSenderId(messageDto.getSenderId());
        message.setReceiverId(messageDto.getReceiverId());
        message.setContent(messageDto.getContent());
        message.setMessageType(messageDto.getMessageType() != null ? messageDto.getMessageType() : "TEXT");
        message.setTimestamp(LocalDateTime.now());
        return message;
    }

    public static MessageDto entityToDto(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setSenderId(message.getSenderId());
        messageDto.setReceiverId(message.getReceiverId());
        messageDto.setContent(message.getContent());
        messageDto.setMessageType(message.getMessageType());
        messageDto.setTimestamp(message.getTimestamp());
        return messageDto;
    }

}
