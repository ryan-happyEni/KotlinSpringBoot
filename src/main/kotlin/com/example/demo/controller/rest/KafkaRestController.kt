package com.example.demo.controller.rest

import com.example.demo.kafka.SimpleConsumer
import com.example.demo.kafka.SimpleProducer
import com.example.demo.model.Message
import com.example.demo.service.MessageService
import com.example.demo.utils.jsonMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("rest/kafka")
class KafkaRestController(@Autowired var messageService: MessageService) {
    @Value("\${kafka.broker.address}")
    val brokers: String? = null
    @Value("\${kafka.broker.group-id}")
    val groupId: String? = null

    @PostMapping("/send/{topic}")
    fun send(@PathVariable topic : String, @RequestBody msg : Message): String {
        return messageService.send(topic, msg);
    }

    @PostMapping("/read/{topic}")
    fun read(@PathVariable topic : String): MutableList<Message> {
        return messageService.read(topic)
    }

    @PostMapping("/topics")
    fun topics(): MutableSet<String> {
        return messageService.topics()
    }
}