package com.freesoft.hazelcastpoc

import com.hazelcast.core.Hazelcast
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.TimeUnit

@Component
class ConcurrentCommandLineRunner : CommandLineRunner {
    override fun run(vararg args: String?) {
        val hazelcast = Hazelcast.newHazelcastInstance()

        val capitals = hazelcast.getMap<String, String>("capitals")

        capitals.putIfAbsent("GB", "Winchester")
        println("Capital of GB (until 1066): ${capitals["GB"]}")

        val actualCapital = capitals.putIfAbsent("GB", "London")
        println("Failed to put as already present:  ${capitals["GB"]} = $actualCapital")

        val result1 = capitals.replace("GB", "Southampton", "London")
        println("Failed to replace as incorrect old value ${capitals["GB"]}; [$result1]")

        val result2 = capitals.replace("GB", "Winchester", "London")
        println("Capital of GB (since 1066): ${capitals["GB"]}; [$result2]")

        val queue = hazelcast.getQueue<String>("queue")
        val random = Random()

        while (true) {
            queue.add("${Date()} - ${hazelcast.cluster.localMember} says hello!")

            Thread.sleep(random.nextInt(2000).toLong())
            val msg = queue.poll(5, TimeUnit.SECONDS)
            if (msg != null) {
                println("Message: $msg")
            }
        }

    }
}