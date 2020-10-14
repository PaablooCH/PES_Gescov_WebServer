package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Classroom {

    private final UUID id;

    private final String name;

    private final int capacity;

    public Classroom(@JsonProperty("id") UUID id,
                     @JsonProperty("name") String name,
                     @JsonProperty("capacity") int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }


}
