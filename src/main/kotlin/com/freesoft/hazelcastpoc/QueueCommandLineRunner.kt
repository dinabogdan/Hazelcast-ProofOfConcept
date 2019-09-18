//package com.freesoft.hazelcastpoc
//
//import com.hazelcast.core.Hazelcast
//import org.springframework.boot.CommandLineRunner
//import org.springframework.stereotype.Component
//
//@Component
//class QueueCommandLineRunner : CommandLineRunner {
//    override fun run(vararg args: String?) {
//        val hazelcast = Hazelcast.newHazelcastInstance()
//
//        val arrivals = hazelcast.getQueue<String>("arrivals")
//
//        while (true) {
//            val arrival = arrivals.take()
//            println("New arrival from $arrival")
//        }
//    }
//
//}