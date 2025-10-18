package com.ravi.chatserver.controller;


import com.ravi.chatserver.model.MessageRequest;
import com.ravi.chatserver.model.MessageResponse;
import com.ravi.chatserver.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message/")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public void receivedMessage(@RequestBody @Valid MessageRequest messageRequest){ // if we don't put @RequestBody then all field default null set
        messageService.receivedMessage(messageRequest);
    }

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
