package com.example.demo.controller

import com.example.demo.model.User
import com.example.demo.service.MessageService
import com.example.demo.service.UserService
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
@RequestMapping("message")
class MessageController(@Autowired var messageService: MessageService) {

    val counter = AtomicLong()

    @GetMapping("/new")
    @JsonIgnoreProperties(ignoreUnknown=true)
    fun newMessage(): MutableList<User>{
        return messageService.newMessage()
    }

}