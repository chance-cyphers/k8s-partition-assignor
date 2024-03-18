package com.skyfrog.partitionassignorexample

import org.apache.kafka.clients.consumer.ConsumerPartitionAssignor.Subscription
import org.apache.kafka.clients.consumer.internals.AbstractPartitionAssignor
import org.apache.kafka.common.TopicPartition

class UnbalancedPartitionAssignor : AbstractPartitionAssignor() {
    override fun assign(
            partitionsPerTopic: MutableMap<String, Int>, // topic to num partitions
            subscriptions: MutableMap<String, Subscription> // member to topics
    ): MutableMap<String, MutableList<TopicPartition>> { // member to partitions

        val topicsToMember = HashMap<String, String>()
        subscriptions.forEach {(member, sub) ->
            sub.topics().forEach {topic ->
                topicsToMember[topic] = member
            }
        }

        val defaultMap = subscriptions.keys.map {
            it to emptyList<TopicPartition>().toMutableList()
        }.toMap().toMutableMap()

        val membersToPartitions = topicsToMember.map { (topic, member) ->
            val allPartitionsForTopic = (0..<(partitionsPerTopic[topic] ?: 0)).toList().map { partitionNum ->
                TopicPartition(topic, partitionNum)
            }.toMutableList()
            member to allPartitionsForTopic
        }.toMap().toMutableMap()

        return (defaultMap + membersToPartitions) as MutableMap<String, MutableList<TopicPartition>>
    }

    override fun name(): String {
        return this::class.java.name
    }
}