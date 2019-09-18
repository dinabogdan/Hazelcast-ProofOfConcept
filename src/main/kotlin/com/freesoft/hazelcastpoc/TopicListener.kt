package com.freesoft.hazelcastpoc

import com.hazelcast.core.Message
import com.hazelcast.core.MessageListener

class TopicListener : MessageListener<String> {
    override fun onMessage(p0: Message<String>?) {
        println("Received: ${p0?.messageObject}")
    }
}