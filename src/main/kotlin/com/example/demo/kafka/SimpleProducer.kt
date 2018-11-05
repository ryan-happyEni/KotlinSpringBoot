package com.example.demo.kafka

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.logging.log4j.LogManager
import java.util.*

class SimpleProducer(brokers: String){
    private val logger = LogManager.getLogger(javaClass)
    private val producer = createProducer(brokers)

    private fun createProducer(brokers : String) : Producer<String, String> {
        val props = Properties()
        props["bootstrap.servers"] = brokers
        props["key.serializer"] = StringSerializer::class.java.canonicalName
        props["value.serializer"] = StringSerializer::class.java.canonicalName

        return KafkaProducer<String, String>(props)
    }

    fun send(topic: String, message: String) {
        logger.info("Message : $message")
        val futureResult = producer.send(ProducerRecord(topic, message))
        logger.debug("Sent a record")
        futureResult.get()
    }

    fun close(){
        producer.close()
    }
}