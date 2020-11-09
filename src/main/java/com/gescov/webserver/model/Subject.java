package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Document(collection = "subjects")
@CompoundIndexes({
        @CompoundIndex(name="subject_school_indx", def = "{'name' : 1, 'schoolID' : 1}" ,unique = true)
})
public class Subject {
    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String schoolID;

    private List<String> studentsID;

    private List<String> teachersID;

    public Subject(@JsonProperty("id") String id, @JsonProperty("name") String name, @JsonProperty("school") String school) {
        this.id = id;
        this.name = name;
        this.schoolID = school;
        this.studentsID = new ArrayList<>();
        this.teachersID = new ArrayList<>();
    }

    public void addStudent(String userId){
        studentsID.add(userId);
    }

    public void addTeacher(String userId){
        teachersID.add(userId);
    }

}
