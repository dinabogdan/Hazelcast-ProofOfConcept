package com.freesoft.hazelcastpoc;

import com.hazelcast.core.PartitionAware;

import java.io.Serializable;

public class CityKey implements PartitionAware, Serializable {

    private String country;
    private String name;

    public CityKey(String country, String name) {
        this.country = country;
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    @Override
    public Object getPartitionKey() {
        return this.country;
    }
}
