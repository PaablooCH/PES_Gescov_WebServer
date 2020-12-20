package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Document(collection = "users")
public class User {

    @Id
    private String id;

    @NotNull
    private String tokenID;

    @NotNull(message = "User's name must not be null")
    private String name;

    @NotNull(message = "User's email must not be null")
    @Indexed(name = "noSameEmail", unique = true)
    private String email;

    private String pic;

    private boolean risk;

    private List<String> schoolsID;

    private boolean isStudent;

    private List<String> devices;

    public User(@JsonProperty("id") String id,
                @JsonProperty("name") String name,
                @JsonProperty("email") String email,
                @JsonProperty("pic") String pic) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pic = pic;
        this.risk = false;
        this.schoolsID = new ArrayList<>();
        this.isStudent = true;
        this.devices = new ArrayList<>();
    }

    public void addSchool(String schoolID) {
        schoolsID.add(schoolID);
    }

    public void addDeviceToken(String deviceToken) { this.devices.add(deviceToken); }

    public void deleteDeviceToken(String deviceToken) { this.devices.remove(deviceToken); }

}
