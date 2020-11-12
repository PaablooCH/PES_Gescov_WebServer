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

    private boolean risk;

    private List<String> schoolsID;


    public User(@JsonProperty("id") String id,
                @JsonProperty("name") String name,
                @JsonProperty("email") String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.risk = false;
        this.schoolsID = new ArrayList<>();
    }

    public void addSchool(String schoolID) {
        schoolsID.add(schoolID);
    }

}
