package com.freesoft.hazelcastpoc

import com.hazelcast.core.Hazelcast
import com.hazelcast.core.IMap
import com.hazelcast.query.PagingPredicate
import com.hazelcast.query.PredicateBuilder
import com.hazelcast.query.Predicates
import com.hazelcast.query.SqlPredicate
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class IMapCommandLineRunner : CommandLineRunner {
    override fun run(vararg args: String?) {
        val hazelcastInstance = Hazelcast.newHazelcastInstance()

        val capitals: IMap<String, City> = hazelcastInstance.getMap("capitals")

        capitals.addIndex("name", false)
        capitals.addIndex("population", true)

        capitals["GB"] = City("London", "GB", 8174100)
        capitals["FR"] = City("Paris", "FR", 2268265)
        capitals["US"] = City("Washington DC", "US", 601723)
        capitals["RO"] = City("Bucharest", "RO", 2000000)

        val possibleLondons = capitals.values(SqlPredicate("name = 'London' "))

        println("Possible Londons: $possibleLondons")

        val largeCities = capitals.values(SqlPredicate("population > 1000000"))
        println("Large cities: $largeCities")

        val filteredCities = capitals.values(SqlPredicate("country NOT IN ('GB', 'FR')"))
        println("Filtered cities: $filteredCities")

        val entryObject = PredicateBuilder().entryObject

        val londonPredicate = entryObject.get("name").equal("London")
        val possibleLondons2 = capitals.values(londonPredicate)
        println("Possible Londons 2: $possibleLondons2")

        val largeCityPredicate = Predicates.greaterThan("population", 1000000)
        val largeCities2 = capitals.values(largeCityPredicate)
        println("Large cities 2: $largeCities2")

        val pagingPredicate = PagingPredicate<String, City>(largeCityPredicate, 2)

        val firstPageCapitals = capitals.values(pagingPredicate)
        println("First Page: $firstPageCapitals")
        pagingPredicate.nextPage()
        val secondPageCapitals = capitals.values(pagingPredicate)
        println("SecondPage Page: $secondPageCapitals")

    }
}