package com.skyfrog.partitionassignorexample

import jakarta.annotation.PostConstruct
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener
import org.apache.kafka.common.serialization.IntegerDeserializer
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.stereotype.Component
import reactor.kafka.receiver.KafkaReceiver
import reactor.kafka.receiver.ReceiverOptions
import java.util.Properties


@Component
class ConsumerMan {

    @PostConstruct
    fun asd() {
        val props = Properties()

        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        props[ConsumerConfig.CLIENT_ID_CONFIG] = "sample-consumer"
        props[ConsumerConfig.GROUP_ID_CONFIG] = "sample-group"
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
//        props[ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG] = K8sPartitionAssignor::class.java


        val receiverOptions = ReceiverOptions.create<String, Any>(props)
                .subscription(listOf("example-topic"))

        receiverOptions.subscriber(RebalanceListener())

        KafkaReceiver.create(receiverOptions)
                .receive()
                .map {
                    println(it.value())
                }
                .subscribe()
    }

}