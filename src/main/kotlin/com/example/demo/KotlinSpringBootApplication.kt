package com.example.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@SpringBootApplication
class KotlinSpringBootApplication

fun main(args: Array<String>) {
    SpringApplication.run(KotlinSpringBootApplication::class.java, *args)
}
