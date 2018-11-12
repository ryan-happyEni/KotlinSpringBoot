package com.example.demo.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.util.concurrent.atomic.AtomicLong

@Controller
@RequestMapping("kafka")
class KafkaController {

    val counter = AtomicLong()

    @GetMapping("/")
    fun index(model: Model): String {
        return "kafka"
    }

    @GetMapping("/topics")
    fun topics(model: Model): String {
        return "topics"
    }

}