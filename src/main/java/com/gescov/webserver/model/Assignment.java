package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Setter
@Getter
@Document(collection = "assignments")
@CompoundIndexes({
        @CompoundIndex(name="assignment_pos_occupied", def = "{'posRow' : 1, 'posCol' : 1, 'classSessionID' : 1}", unique = true),
        @CompoundIndex(name="assignment_student_already_assign", def = "{'studentID' : 1, 'classSessionID' : 1}", unique = true)
})
public class Assignment {

    @Id
    private String id;

    @Min(1)
    private int posCol;

    @Min(1)
    private int posRow;

    @NotNull(message = "Assignment's classSessionID must not be null")
    private String classSessionID;

    @NotNull(message = "Assignment's studentID must not be null")
    private String studentID;

    public Assignment(@JsonProperty("id") String id,
                      @JsonProperty("posCol") int posCol,
                      @JsonProperty("posRow") int posRow,
                      @JsonProperty("classSessionID") String classSessionID,
                      @JsonProperty("studentID") String studentID) {
        this.id = id;
        this.posCol = posCol;
        this.posRow = posRow;
        this.classSessionID = classSessionID;
        this.studentID = studentID;
    }

}