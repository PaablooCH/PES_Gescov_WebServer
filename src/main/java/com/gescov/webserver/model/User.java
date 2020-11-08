package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Document(collection = "users")
public class User {

    @Id
    private String id;

    @NotNull(message = "User's name must not be null")
    private String name;

    @NotNull(message = "User's name must not be null")
    private String email;

    @NotNull(message = "User's risk must be yes (true) or no (false)")
    private boolean risk;

    @DBRef(db="schools")
    private List<School> schools;


    public User(@JsonProperty("id") String id,
                @JsonProperty("name") String name,
                @JsonProperty("email") String email,
                @JsonProperty("risk") boolean risk,
                @JsonProperty("schools") final List<School> schools) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.risk = risk;
        this.schools = schools;
    }

}
