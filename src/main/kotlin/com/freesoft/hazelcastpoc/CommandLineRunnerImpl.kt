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

        val cities = hazelcast.getSet<String>("cities")
        cities.addAll(capitals.values)
        cities.add("London")
        cities.add("Rome")
        cities.add("New York")

        val countries = hazelcast.getList<String>("countries")
        countries.addAll(capitals.keys)
        countries.add("CA")
        countries.add("DE")
        countries.add("GB")

        System.err.println("Known capital cities: ${capitals.size}")
        System.err.println("Capital city of GB: ${capitals.get("GB")}")
    }
}