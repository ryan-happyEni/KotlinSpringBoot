package com.example.demo.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name="message")
data class Message(
        @Id @GeneratedValue(strategy= GenerationType.IDENTITY) var msgSeq:Long,
        var topic:String,
        var userId:String,
        var msg:String,
        var sendDate: LocalDateTime =LocalDateTime.now()
)