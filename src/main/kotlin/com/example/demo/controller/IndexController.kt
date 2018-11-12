package com.example.demo.controller

import com.example.demo.service.Hello
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@Controller
class IndexController {

    val counter = AtomicLong()

    @GetMapping("/")
    fun index(model: Model): String {
        return "index"
    }

}