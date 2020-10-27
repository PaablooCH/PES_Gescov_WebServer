package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.NotNull;

//@Document(collection = "subject")
public class Subject {
    @Id
    private ObjectId id;

    @NotNull
    private String name;

    @DBRef
    private School school;

    public Subject() {

    }

    public Subject(@JsonProperty("_id") ObjectId id, @JsonProperty("name") String name, final School school) {
        this.id = id;
        this.name = name;
        this.school = school;
    }

    public School getSchool() { return school; }

    public void setSchool(School school) { this.school = school; }

    public ObjectId getId() {return id;}

    public void setId(ObjectId id) {this.id = id;}

    public String getName() { return name; }

    public void setName(String name) {this.name = name;}
}
