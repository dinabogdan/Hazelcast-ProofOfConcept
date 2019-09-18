//package com.freesoft.hazelcastpoc
//
//import com.hazelcast.core.Hazelcast
//import org.springframework.boot.CommandLineRunner
//import org.springframework.stereotype.Component
//
//@Component
//class MultiMapCommandLineRunner : CommandLineRunner {
//    override fun run(vararg args: String?) {
//        val hazelcastInstance = Hazelcast.newHazelcastInstance()
//
//        val manualCities = hazelcastInstance.getMap<String, List<String>>("manualCities")
//        var gbCities = mutableListOf<String>()
//        manualCities["GB"] = gbCities
//
//        gbCities = manualCities["GB"] as MutableList<String>
//        gbCities.add("London")
//        manualCities["GB"] = gbCities
//
//        gbCities = manualCities["GB"] as MutableList<String>
//        gbCities.add("Liverpool")
//        manualCities["GB"] = gbCities
//
//        var frCities = mutableListOf<String>()
//        manualCities["FR"] = frCities
//
//        frCities = manualCities["FR"] as MutableList<String>
//        frCities.add("Paris")
//        manualCities["FR"] = frCities
//
//        System.err.println(String.format("Manual: GB=${manualCities["GB"]}, FR=${manualCities["FR"]}"))
//
//        val multiMapCities = hazelcastInstance.getMultiMap<String, String>("multiMapCities")
//
//        multiMapCities.put("GB", "London")
//        multiMapCities.put("GB", "Liverpool")
//        multiMapCities.put("FR", "Paris")
//
//        System.err.println(String.format("MultiMap: GB=${multiMapCities["GB"]}, FR=${multiMapCities["FR"]}"))
//
//    }
//}