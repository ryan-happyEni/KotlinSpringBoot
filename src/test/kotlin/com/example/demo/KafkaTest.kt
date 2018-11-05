package com.example.demo

import com.example.demo.kafka.SimpleConsumer
import com.example.demo.kafka.SimpleProducer
import com.example.demo.model.User
import com.example.demo.utils.jsonMapper
import org.apache.logging.log4j.LogManager
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KafkaTest {
    private val logger = LogManager.getLogger(javaClass)
    @Value("\${kafka.broker.address}")
    val brokers: String? = null

    @Test
    fun test() {
        val topic = "users"
        var kafka = SimpleProducer(brokers?: "")
        for(i in 1..3) {
            val user = User(-1, "test6"+i, "Test6"+i)
            kafka.send(topic, jsonMapper.writeValueAsString(user))
        }
        kafka.close()
    }

    @Test
    fun testConsumer() {
        val topic = "users"

        var kafka = SimpleConsumer(brokers?: "")
        var records= kafka.subscribe(topic)

        var userList : MutableList<User> = mutableListOf<User>()

        logger.info("Received ${records.count()} records")

        records.iterator().forEach {
            val personJson = it.value()
            //logger.info("JSON data: $personJson")

            try{
                val user = jsonMapper.readValue(personJson, User::class.java)
                userList.add(user)
                logger.info("User: $user")
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}