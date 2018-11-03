package com.example.demo.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name="user")
data class User(
        @Id @GeneratedValue(strategy= GenerationType.IDENTITY) var user_seq:Long,
        var user_id:String,
        var user_name:String,
        var write_date: LocalDateTime =LocalDateTime.now()
)