package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

//@Document(collection = "schools")
public class School {

    @Id
    private ObjectId id;

    @Id
    private String name;

    private String state;

    private String address;

    private float longitude;

    private float latitude;

    private String creator;

    private List<String> administrators;

    public School() {}

    public School(@JsonProperty("id") ObjectId id,
                  @JsonProperty("name") String name,
                  @JsonProperty("address") String address,
                  @JsonProperty("longitude") float longitude,
                  @JsonProperty("latitude") float latitude,
                  @JsonProperty("creator") String creator) {
        this.id = id;
        this.name = name;
        this.state = "open";
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.creator = creator;
        this.administrators = new ArrayList<>();
        this.administrators.add(creator);

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<String> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(List<String> administrators) {
        this.administrators = administrators;
    }
}
