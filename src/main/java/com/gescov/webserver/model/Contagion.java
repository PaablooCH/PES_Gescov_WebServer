package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
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

    @NotNull(message = "Contagion's infectedID must not be null")
    private String infectedID;

    public Contagion(@JsonProperty("id") String id,
                     @JsonProperty("infectedID") String infectedID) {
        this.id = id;
        this.infectedID = infectedID;
        this.startContagion = LocalDate.now();
        this.endContagion = null;
    }

}
