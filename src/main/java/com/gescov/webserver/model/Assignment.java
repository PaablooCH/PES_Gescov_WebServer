package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;

@NoArgsConstructor
@Setter
@Getter
@Document(collection = "assignments")
/*@CompoundIndexes({
        @CompoundIndex(name="assignment_pos_occupied", def = "{'posRow' : 1, 'posCol' : 1, 'classSession.id' : 1}", unique = true),
        @CompoundIndex(name="assignment_student_already_assign", def = "{'users.id' : 1, 'classSession.id' : 1}", unique = true)
})*/
public class Assignment {

    @Id
    private String id;

    @Min(1)
    private int posCol;

    @Min(1)
    private int posRow;

    @DBRef(db = "classSession")
    private ClassSession classSession;

    @DBRef(db = "users")
    private User student;

    public Assignment(@JsonProperty("id") String id,
                      @JsonProperty("posCol") int posCol,
                      @JsonProperty("posRow") int posRow,
                      @JsonProperty("classSession") ClassSession classSession,
                      @JsonProperty("student") User student) {
        this.id = id;
        this.posCol = posCol;
        this.posRow = posRow;
        this.classSession = classSession;
        this.student = student;
    }

}