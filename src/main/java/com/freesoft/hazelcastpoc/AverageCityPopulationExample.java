package com.freesoft.hazelcastpoc;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapIndexConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class AverageCityPopulationExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Config config = new Config();
        MapConfig citiesConfig = config.getMapConfig("cities");

        citiesConfig.addMapIndexConfig(new MapIndexConfig("country", false));

        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);

        IMap<CityKey, City> cities = hazelcastInstance.getMap("cities");

        if (cities.isEmpty()) {
            cities.put(
                    new CityKey("GB", "London"),
                    new City("London", "GB", 7322403, 2001));
            cities.put(
                    new CityKey("GB", "Southampton"),
                    new City("Southampton", "GB", 226698, 2006));
            cities.put(
                    new CityKey("GB", "Plymouth"),
                    new City("Plymouth", "GB", 226698, 2006));
            cities.put(
                    new CityKey("GB", "York"),
                    new City("York", "GB", 195070, 2010));
            cities.put(
                    new CityKey("FR", "Paris"),
                    new City("Paris", "FR", 2268265, 2013));
        }

        ExecutorService executorService = hazelcastInstance.getExecutorService("exec");
        Future<Integer> averageTask = executorService.submit(new AverageCityPopulationCallable(("GB")));
        Integer averagePopultion = averageTask.get();
        System.out.println("Average GB city population: " + averagePopultion);

    }
}
