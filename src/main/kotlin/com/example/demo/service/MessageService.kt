package com.example.demo.service

import com.example.demo.kafka.SimpleConsumer
import com.example.demo.model.User
import com.example.demo.repository.UserRepository
import com.example.demo.utils.jsonMapper
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class MessageService{

    @Value("\${kafka.broker.address}")
    val brokers: String? = null
    val topic = "users"

    fun newMessage() : MutableList<User>{
        var userList : MutableList<User> = mutableListOf<User>()
        println("brokers ${brokers} .")
        println("topic ${topic} .")
        var kafka = SimpleConsumer(brokers?: "")
        var records= kafka.subscribe(topic)
        records.iterator().forEach {
            val personJson = it.value()
            try{
                userList.add(jsonMapper.readValue(personJson, User::class.java))
            }catch (e:Exception){
                e.printStackTrace()
            }
        }

        return userList
    }
}