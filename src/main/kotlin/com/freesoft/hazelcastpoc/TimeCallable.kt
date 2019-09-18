package com.freesoft.hazelcastpoc

import com.hazelcast.config.Config
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import com.hazelcast.core.HazelcastInstanceAware
import java.io.Serializable
import java.util.*
import java.util.concurrent.Callable

class TimeCallable(private var hazelcastInstance: HazelcastInstance?) : Callable<String>, HazelcastInstanceAware, Serializable {

    override fun setHazelcastInstance(hazelcastInstance: HazelcastInstance?) {
        this.hazelcastInstance = hazelcastInstance
    }

    override fun call(): String {
        return "${hazelcastInstance?.cluster?.localMember} - ${Date()}"
    }
}


fun main(args: Array<String>) {
    val config = Config()

    val hazelcast = Hazelcast.newHazelcastInstance(config)
    val executorService = hazelcast.getExecutorService("executor")

    while (true) {
        val timeFuture = executorService.submit(TimeCallable(hazelcast))
        val theTime = timeFuture.get()

        println("The time: $theTime")
        Thread.sleep(1000)
    }

}