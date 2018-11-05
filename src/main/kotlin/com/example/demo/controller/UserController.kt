package com.example.demo.controller

import com.example.demo.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
@RequestMapping("user")
class UserController(@Autowired var userService: UserService) {

    val counter = AtomicLong()

    @GetMapping("/info/{user_id}")
    fun greeting(@PathVariable user_id : String) = userService.find(user_id)

}