package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "classrooms")
public class Classroom {

    private ObjectId id;

    private String name;

    private int capacity;

    public Classroom() {

    }

    public Classroom(@JsonProperty("_id") ObjectId id,
                     @JsonProperty("name") String name,
                     @JsonProperty("capacity") int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

}
