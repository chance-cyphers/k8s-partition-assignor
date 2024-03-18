package com.skyfrog.partitionassignorexample

import org.apache.kafka.clients.consumer.ConsumerPartitionAssignor.Subscription
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UnbalancedPartitionAssignorTest {

    @Test
    fun `assigns all partitions to one member per topic`() {
        val topicToNumPartitions = mutableMapOf("topic-A" to 4, "topic-B" to 3)
        val subscriptions = mutableMapOf(
                "member-A" to Subscription(listOf("topic-A")),
                "member-B" to Subscription(listOf("topic-A", "topic-B")),
                "member-C" to Subscription(listOf("topic-B")),
        )

        val assignment = UnbalancedPartitionAssignor().assign(topicToNumPartitions, subscriptions)

        assertThat(assignment["member-A"]?.size).isEqualTo(0)

        assertThat(assignment["member-B"]?.size).isEqualTo(4)
        assertThat(assignment["member-B"]!![0].topic()).isEqualTo("topic-A")
        assertThat(assignment["member-B"]!![0].partition()).isEqualTo(0)
        assertThat(assignment["member-B"]!![1].topic()).isEqualTo("topic-A")
        assertThat(assignment["member-B"]!![1].partition()).isEqualTo(1)
        assertThat(assignment["member-B"]!![2].topic()).isEqualTo("topic-A")
        assertThat(assignment["member-B"]!![2].partition()).isEqualTo(2)
        assertThat(assignment["member-B"]!![3].topic()).isEqualTo("topic-A")
        assertThat(assignment["member-B"]!![3].partition()).isEqualTo(3)

        assertThat(assignment["member-C"]?.size).isEqualTo(3)
        assertThat(assignment["member-C"]!![0].topic()).isEqualTo("topic-B")
        assertThat(assignment["member-C"]!![0].partition()).isEqualTo(0)
        assertThat(assignment["member-C"]!![1].topic()).isEqualTo("topic-B")
        assertThat(assignment["member-C"]!![1].partition()).isEqualTo(1)
        assertThat(assignment["member-C"]!![2].topic()).isEqualTo("topic-B")
        assertThat(assignment["member-C"]!![2].partition()).isEqualTo(2)

    }

}