package com.example.demo.kafka

import com.example.demo.model.User
import com.example.demo.utils.jsonMapper
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.logging.log4j.LogManager
import java.time.Duration
import java.util.*

class SimpleConsumer(brokers: String){
    private val logger = LogManager.getLogger(javaClass)
    private val consumer = createConsumer(brokers)

    private fun createConsumer(brokers: String): Consumer<String, String> {
        val props = Properties()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = brokers
        props[ConsumerConfig.GROUP_ID_CONFIG] = "person-processor"
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.canonicalName
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.canonicalName
        props[ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG] = "true"
        props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"

        return KafkaConsumer<String, String>(props)
    }

    fun subscribe(topic: String) : ConsumerRecords<String, String> {
        consumer.subscribe(listOf(topic))

        logger.info("Consuming and processing data")

        //while (true) {
            val records = consumer.poll(Duration.ofSeconds(1))
        //}
        consumer.close()
        return records
    }
}