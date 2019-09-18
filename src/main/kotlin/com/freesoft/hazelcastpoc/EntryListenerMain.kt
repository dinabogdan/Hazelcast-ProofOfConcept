package com.freesoft.hazelcastpoc

import com.hazelcast.core.EntryEvent
import com.hazelcast.core.EntryListener
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.MapEvent
import java.util.concurrent.TimeUnit

class CustomEntryListener : EntryListener<String, String> {
    override fun entryEvicted(event: EntryEvent<String, String>?) {
        println("Evicted: $event")
    }

    override fun entryUpdated(event: EntryEvent<String, String>?) {
        println("Updated: $event")
    }

    override fun mapCleared(event: MapEvent?) {
        println("Map cleared: $event")
    }

    override fun entryAdded(event: EntryEvent<String, String>?) {
        println("Added: $event")
    }

    override fun entryRemoved(event: EntryEvent<String, String>?) {
        println("Removed: $event")
    }

    override fun mapEvicted(event: MapEvent?) {
        println("Map evicted: $event")
    }
}

fun main(args: Array<String>) {
    val hazelcast = Hazelcast.newHazelcastInstance()
    val capitals = hazelcast.getMap<String, String>("capitals")
    capitals.addEntryListener(CustomEntryListener(), true)
    capitals.put("GB", "Winchester")
    capitals.put("GB", "London")
    capitals.put("DE", "Berlin", 10, TimeUnit.SECONDS)
    capitals.remove("GB")
}