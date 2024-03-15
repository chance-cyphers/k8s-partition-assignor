package com.skyfrog.partitionassignorexample

import jakarta.annotation.PostConstruct
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.IntegerDeserializer
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.stereotype.Component
import reactor.kafka.receiver.ReceiverOptions


@Component
class ConsumerMan {

    @PostConstruct
    fun asd() {

        println("hello")
//        val props: Map<String, Any> = HashMap()
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
//        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "sample-consumer")
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "sample-group")
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer::class.java)
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java)
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
//        receiverOptions = ReceiverOptions.create(props)
//        dateFormat = DateTimeFormatter.ofPattern("HH:mm:ss:SSS z dd MMM yyyy")


    }

}