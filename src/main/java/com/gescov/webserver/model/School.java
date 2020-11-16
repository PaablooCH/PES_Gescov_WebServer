package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @Indexed(name = "no_same_school", unique = true)
    private String name;

    private String state;

    @NotNull(message = "School's address must not be null")
    private String address;

    private float longitude = (float)0.0;

    private float latitude= (float)0.0;

    @Pattern(regexp="([0-9]{9})")
    private String phone;

    @Pattern(regexp="^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")
    private String website;

    @NotNull(message = "School's creator must not be null")
    private String creatorID;

    private List<String> administratorsID;


    public School(@JsonProperty("id") String id,
                  @JsonProperty("name") String name,
                  @JsonProperty("address") String address,
                  @JsonProperty("longitude") float longitude,
                  @JsonProperty("latitude") float latitude,
                  @JsonProperty("phone") String phone,
                  @JsonProperty("website") String website,
                  @JsonProperty("creatorID") String creatorID) {
        this.id = id;
        this.name = name;
        this.state = "open";
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.phone = phone;
        this.website = website;
        this.creatorID = creatorID;
        this.administratorsID = new ArrayList<>();
        addAdministrator(creatorID);
    }

    public void addAdministrator(String adminID) {
        administratorsID.add(adminID);
    }

}
