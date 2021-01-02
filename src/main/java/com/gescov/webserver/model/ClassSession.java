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

    @JsonFormat(pattern = "HH-mm", shape = JsonFormat.Shape.STRING)
    private LocalTime hour;

    @JsonFormat(pattern = "HH-mm", shape = JsonFormat.Shape.STRING)
    private LocalTime finishHour;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate date;

    public ClassSession(@JsonProperty ("id") String id, @JsonProperty("classroomID") String classroomID,
                        @JsonProperty("subjectID") String subjectID, @JsonProperty ("teacherID") String teacherID,
                        @JsonProperty ("hour") String hour, @JsonProperty ("finishHour") String finishHour, @JsonProperty("date") String date) {
        this.id = id;
        this.classroomID = classroomID;
        this.subjectID = subjectID;
        this.teacherID = teacherID;
        this.hour = LocalTime.parse(hour);
        this.finishHour = LocalTime.parse(finishHour);
        this.date = LocalDate.parse(date);
    }

}
