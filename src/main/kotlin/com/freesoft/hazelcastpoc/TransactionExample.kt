package com.freesoft.hazelcastpoc

import com.hazelcast.core.Hazelcast
import java.lang.Exception

fun main(args: Array<String>) {

    val hazelcast = Hazelcast.newHazelcastInstance()

    val tx = hazelcast.newTransactionContext()

    tx.beginTransaction()

    val transactionalMap = tx.getMap<String, String>("test")

    try {
        println(transactionalMap.get("foo"))
        Thread.sleep(30000)

        println(transactionalMap.get("foo"))
        transactionalMap.put("foo", "bar")
        Thread.sleep(30000)

        tx.commitTransaction()
        println("Committed!")
    } catch (exception: Exception) {
        tx.rollbackTransaction()
        println("Rolled back!")
    }
}