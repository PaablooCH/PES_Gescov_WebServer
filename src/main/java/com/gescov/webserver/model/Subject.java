package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection = "subject")
public class Subject {

    private ObjectId id;

    private String name;

    public Subject(){

    }

    public Subject(@JsonProperty("_id") ObjectId id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public ObjectId getId() {return id;}

    public void setId(ObjectId id) {this.id = id;}

    public String getName() { return name; }

    public void setName(String name) {this.name = name;}
}
