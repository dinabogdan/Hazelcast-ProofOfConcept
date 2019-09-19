package com.freesoft.hazelcastpoc;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;
import com.hazelcast.core.IMap;
import com.hazelcast.core.PartitionAware;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.Callable;

public class AverageCityPopulationCallable implements Callable<Integer>, HazelcastInstanceAware, PartitionAware, Serializable {

    private String country;
    private HazelcastInstance hazelcastInstance;

    public AverageCityPopulationCallable(String country) {
        this.country = country;
    }

    @Override
    public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @Override
    public Object getPartitionKey() {
        return this.country;
    }

    @Override
    public Integer call() throws Exception {
        System.err.println("Running task on: " + hazelcastInstance.getCluster().getLocalMember().toString());
        IMap<CityKey, City> cities = hazelcastInstance.getMap("cities");
        Predicate predicate = Predicates.equal("country", this.country);

        Collection<City> countryCities = cities.values(predicate);

        int totalPopulation = 0;
        for (City countryCity : countryCities) {
            totalPopulation += countryCity.getPopulation();
        }

        return totalPopulation / countryCities.size();
    }
}


