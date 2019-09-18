package com.freesoft.hazelcastpoc

import com.hazelcast.config.Config
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.Member
import com.hazelcast.core.MultiExecutionCallback

fun main(args: Array<String>) {
    val config = Config()
    val hazelcast = Hazelcast.newHazelcastInstance(config)

    val executorService = hazelcast.getExecutorService("multiExecutor")

    val timeCallable = TimeCallable(hazelcast)

    val multiExecutionCallback = object : MultiExecutionCallback {
        override fun onComplete(values: MutableMap<Member, Any>?) {
            repeat(values?.count()!!) { println("Complete $it") }
        }

        override fun onResponse(member: Member?, value: Any?) {
            println("Received: $value")
        }
    }

    while(true) {
        val clusterMembers = hazelcast.cluster.members
        executorService.submitToMembers(timeCallable, clusterMembers, multiExecutionCallback)
        Thread.sleep(1000)
    }

}