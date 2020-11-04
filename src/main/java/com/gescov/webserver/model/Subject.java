package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
//@AllArgsConstructor
@Document(collection = "subjects")
public class Subject {
    @Id
    private String id;

    @NotNull
    private String name;

    @DBRef
    private School school;


    public Subject(@JsonProperty("_id") String id, @JsonProperty("name") String name, final School school) {
        this.id = id;
        this.name = name;
        this.school = school;
    }

    public School getSchool() { return school; }

    public void setSchool(School school) { this.school = school; }

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getName() { return name; }

    public void setName(String name) {this.name = name;}
}
