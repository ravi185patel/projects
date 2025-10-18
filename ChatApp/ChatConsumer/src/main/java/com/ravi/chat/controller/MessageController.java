package com.ravi.chat.controller;


import com.ravi.chat.model.MessageResponse;
import com.ravi.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message/")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("{id}")
    public ResponseEntity receivedMessage(@PathVariable String id){
        MessageResponse messageResponse = messageService.retrieveMessage(id);
        if(messageResponse == null){
            return ResponseEntity.ok("No data found");
        }else{
            return ResponseEntity.ok(messageResponse);
        }
    }
}
