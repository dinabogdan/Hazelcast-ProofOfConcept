package com.freesoft.hazelcastpoc

import java.io.Serializable

data class City(val name: String,
                val country: String,
                val population: Int,
                val year: Int) : Serializable, Comparable<City> {
    override fun compareTo(other: City): Int {
        return population - other.population
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as City

        if (this.country != other.country) return false
        if (this.name != other.name) return false
        if (this.year != other.year) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + country.hashCode()
        result = 31 * result + year.hashCode()
        return result
    }


}