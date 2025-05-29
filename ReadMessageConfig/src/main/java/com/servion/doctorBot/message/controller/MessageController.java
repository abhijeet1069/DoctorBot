package com.servion.doctorBot.message.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servion.doctorBot.message.service.MessageService;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController() {
        this.messageService = MessageService.getInstance("message.yml");
    }

    @GetMapping("/msg")
    public String getMessage() {
        return messageService.getMessage();
    }
}
