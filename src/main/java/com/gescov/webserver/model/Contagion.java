package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Document(collection = "contagion")
public class Contagion {

    @Id
    private String id;

    private LocalDate startContagion;

    private LocalDate endContagion;

    @DBRef(db = "users")
    User infected;

    public Contagion(@JsonProperty("_id") String id,
                     @JsonProperty("infected") final User infected) {
        this.id = id;
        this.infected = infected;
        this.startContagion = LocalDate.now();
        this.endContagion = null;
    }

}
