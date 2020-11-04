package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Document(collection = "schools")
public class School {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private String state;

    @NonNull
    private String address;

    private float longitude;

    private float latitude;

    @NonNull
    private String creator;

    private List<String> administrators;


    public School(@JsonProperty("id") String id,
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
