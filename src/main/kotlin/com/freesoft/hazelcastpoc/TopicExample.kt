package com.freesoft.hazelcastpoc

import com.hazelcast.core.Hazelcast
import java.util.*

fun main(args: Array<String>) {

    val hazelcast = Hazelcast.newHazelcastInstance()

    val broadcastTopic = hazelcast.getTopic<String>("broadcast")

    broadcastTopic.addMessageListener(TopicListener())

    while (true) {
        broadcastTopic.publish("${Date()} - ${hazelcast.cluster.localMember} says hello ")
        Thread.sleep(1000)
    }
}