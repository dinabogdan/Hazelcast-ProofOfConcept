package com.freesoft.hazelcastpoc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HazelcastPocApplication

fun main(args: Array<String>) {
	runApplication<HazelcastPocApplication>(*args)
}
