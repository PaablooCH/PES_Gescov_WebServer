package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Setter
@Getter
@Document(collection = "classSession")
/*@CompoundIndexes({
        @CompoundIndex(name="classroom_hour_date_indx", def = "{'classroom.id' : 1, 'hour' : 1, 'date' : 1}" ,unique = true)
})*/
public class ClassSession {

    @Id
    private String id;

    @DBRef(db = "classrooms")
    private Classroom classroom;

    @DBRef(db = "subjects")
    private Subject subject;

    @DBRef(db = "users")
    private User teacher;

    @JsonFormat(pattern = "HH-mm-ss", shape = JsonFormat.Shape.STRING)
    private String hour;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private String date;


    public ClassSession(@JsonProperty ("_id") String id, @JsonProperty("classroom") final Classroom classroom,
                        @JsonProperty("subject") final Subject subject, @JsonProperty ("teacher") final User teacher,
                        @JsonProperty ("hour") String hour, @JsonProperty("date") String date) {
        this.id = id;
        this.classroom = classroom;
        this.subject = subject;
        this.teacher = teacher;
        this.hour = hour;
        this.date = date;
    }

}
