package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Setter
@Getter
@Document(collection = "schools")
public class School {

    @Id
    private String id;

    @NotNull(message = "School's creator must not be null")
    @Indexed(unique = true)
    private String name;

    private String state;

    @NotNull(message = "School's address must not be null")
    private String address;

    private float longitude = (float)0.0;

    private float latitude= (float)0.0;

    private String phone;

    private String website;

    @NotNull(message = "School's creator must not be null")
    private String creator;

    private List<String> administrators;


    public School(@JsonProperty("id") String id,
                  @JsonProperty("name") String name,
                  @JsonProperty("address") String address,
                  @JsonProperty("longitude") float longitude,
                  @JsonProperty("latitude") float latitude,
                  @JsonProperty("phone") String phone,
                  @JsonProperty("website") String website,
                  @JsonProperty("creator") String creator) {
        this.id = id;
        this.name = name;
        this.state = "open";
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.phone = phone;
        this.website = website;
        this.creator = creator;
        this.administrators = new ArrayList<>();
        this.administrators.add(creator);
    }

}
