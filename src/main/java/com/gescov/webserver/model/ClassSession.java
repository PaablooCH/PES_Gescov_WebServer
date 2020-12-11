package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@Setter
@Getter
@Document(collection = "classSession")
@CompoundIndex(name="classroom_hour_date_indx", def = "{'classroomID' : 1, 'hour' : 1, 'date' : 1}" ,unique = true)
@CompoundIndex(name="teacher_hour_date_indx", def = "{'teacherID' : 1, 'hour' : 1, 'date' : 1}" ,unique = true)
public class ClassSession {

    @Id
    private String id;

    @NotNull
    private String classroomID;

    @NotNull
    private String subjectID;

    @NotNull
    private String teacherID;

    @JsonFormat(pattern = "HH-mm-ss", shape = JsonFormat.Shape.STRING)
    private LocalTime hour;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate date;


    public ClassSession(@JsonProperty ("id") String id, @JsonProperty("classroom") String classroom,
                        @JsonProperty("subject") String subject, @JsonProperty ("teacher") String teacher,
                        @JsonProperty ("hour") String hour, @JsonProperty("date") String date) {
        this.id = id;
        this.classroomID = classroom;
        this.subjectID = subject;
        this.teacherID = teacher;
        this.hour = LocalTime.parse(hour);
        this.date = LocalDate.parse(date);
    }

}
