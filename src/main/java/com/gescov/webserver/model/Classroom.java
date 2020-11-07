package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Setter
@Getter
@Document(collection = "classrooms")
public class Classroom {

    @Id
    private String id;

    @NotNull(message = "Classrooms' name must not be null")
    @Indexed(unique = true)
    private String name;

    @Min(1)
    private int capacity;

    @Min(1)
    private int numRows;

    @Min(1)
    private int numCols;

    @DBRef
    private School school;

    public Classroom(@JsonProperty("id") String id,
                     @JsonProperty("name") String name,
                     @JsonProperty("capacity") int capacity,
                     @JsonProperty("numRows") int numRows,
                     @JsonProperty("numCols") int numCols,
                     @JsonProperty("school") final School school) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.numRows = numRows;
        this.numCols = numCols;
        this.school = school;
    }

}
