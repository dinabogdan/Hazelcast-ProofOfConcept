package com.freesoft.hazelcastpoc

import com.hazelcast.core.Hazelcast
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class CommandLineRunnerImpl : CommandLineRunner {
    override fun run(vararg args: String?) {
        val hazelcast = Hazelcast.newHazelcastInstance()

        val capitals = hazelcast.getMap<String, String>("capitals")
        capitals.put("GB", "London")
        capitals.put("FR", "Paris")
        capitals.put("US", "Washington DC")
        capitals.put("RO", "Bucharest")
        capitals.put("IT", "Rome")

        System.err.println("Known capital cities: ${capitals.size}")
        System.err.println("Capital city of GB: ${capitals.get("GB")}")
    }
}