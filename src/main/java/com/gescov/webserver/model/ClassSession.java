package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.lang.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.swing.text.DateFormatter;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


//@Document(collection = "classSession")
public class ClassSession {

    @Id
    private ObjectId id;

    @DBRef
    private Classroom classroom;

    @DBRef
    private Subject subject;

    @DBRef
    private User student;

    @JsonFormat(pattern = "HH-mm-ss", shape = JsonFormat.Shape.STRING)
    private String hour;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private String date;

    public ClassSession() {

    }

    public ClassSession(@JsonProperty ("_id") ObjectId id, @JsonProperty("classroom") final Classroom classroom, @JsonProperty("subject") final Subject subject, @JsonProperty ("student") final User student, @JsonProperty ("hour") String hour, @JsonProperty("date") String date) {
        this.id = id;
        this.classroom = classroom;
        this.subject = subject;
        this.student = student;
        this.hour = hour;
        this.date = date;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @NonNull
    public String getHora() {
        return hour;
    }

    public void setHora(String hora) {
        hour = hora;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
