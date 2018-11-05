package com.example.demo.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name="user")
data class User(
        @Id @GeneratedValue(strategy= GenerationType.IDENTITY) var userSeq:Long,
        var userId:String,
        var userName:String,
        var writeDate: LocalDateTime =LocalDateTime.now()
)