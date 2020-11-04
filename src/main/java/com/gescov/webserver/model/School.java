package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
//@AllArgsConstructor
@Getter
//@Setter
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(String state) {
        this.state = state;
    }
}
