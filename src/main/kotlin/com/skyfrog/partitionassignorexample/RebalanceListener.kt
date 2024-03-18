package com.skyfrog.partitionassignorexample

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener
import org.apache.kafka.common.TopicPartition

class RebalanceListener : ConsumerRebalanceListener {
    override fun onPartitionsRevoked(partitions: MutableCollection<TopicPartition>) {
        println("partitions")
        println(partitions)
    }

    override fun onPartitionsAssigned(partitions: MutableCollection<TopicPartition>) {
        println("partitions")
        println(partitions)
    }
}