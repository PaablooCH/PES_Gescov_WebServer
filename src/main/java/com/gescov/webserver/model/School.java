package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotNull;

//@Document(collection = "schools")
public class School {

    @Id
    private ObjectId id;

    @Id
    private String name;

    private String state;

    public School() {

    }

    public School(@JsonProperty("id") ObjectId id,
                     @JsonProperty("name") String name,
                     @JsonProperty("state") String state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
