package com.freesoft.hazelcastpoc

import com.hazelcast.core.Hazelcast
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.TimeUnit

@Component
class DistributedLockCommandLineRunner : CommandLineRunner {
    override fun run(vararg args: String?) {
        val hazelcast = Hazelcast.newHazelcastInstance()

        val lock = hazelcast.cpSubsystem.getLock("theTime")

        while (true) {
            if (lock.tryLock(30, TimeUnit.SECONDS)) {
                try {
                    while (true) {
                        println("${Date()}")
                        Thread.sleep(1000)
                    }
                } finally {
                    lock.unlock()
                }
            }
        }
    }
}