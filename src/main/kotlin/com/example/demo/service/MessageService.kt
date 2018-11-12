package com.example.demo.service

import com.example.demo.kafka.SimpleConsumer
import com.example.demo.kafka.SimpleProducer
import com.example.demo.model.Message
import com.example.demo.utils.jsonMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class MessageService{

    @Value("\${kafka.broker.address}")
    val brokers: String? = null
    @Value("\${kafka.broker.group-id}")
    val groupId: String? = null

    fun send(topic : String, msg : Message): String {
        var result:String = "fail"
        var kafka = SimpleProducer(brokers ?: "")
        kafka.send(topic, jsonMapper.writeValueAsString(msg))
        kafka.close()

        result = "success"
        return result;
    }

    fun read(topic : String): MutableList<Message> {
        var msgList : MutableList<Message> = mutableListOf<Message>()

        var kafka = SimpleConsumer(brokers?: "", groupId?: "")
        var records= kafka.subscribe(topic)
        records.iterator().forEach {
            val personJson = it.value()
            //logger.info("JSON data: $personJson")

            try{
                val msg = jsonMapper.readValue(personJson, Message::class.java)
                msgList.add(msg)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }

        return msgList;
    }

    fun topics(): MutableSet<String> {
        var kafka = SimpleConsumer(brokers?: "", groupId?: "")
        var records= kafka.topicList()

        return records;
    }
}