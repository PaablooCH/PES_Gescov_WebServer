package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

public class User {

    @Id
    private ObjectId id;

    private String name;

    @DBRef
    private List<School> schools;

    public User() {

    }

    public User(@JsonProperty("id") ObjectId id,
                     @JsonProperty("name") String name,
                     @JsonProperty("schools") final List<School> schools) {
        this.id = id;
        this.name = name;
        this.schools = schools;
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

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }

}
