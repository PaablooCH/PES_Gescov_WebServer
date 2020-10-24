package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.lang.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.NotNull;

//@Document(collection = "classSession")
public class ClassSession {

    @Id
    private ObjectId id;

    @DBRef
    private Classroom classroom;

    @DBRef
    private Subject subject;

    @NotNull
    private String student;

    @NotNull
    private String Hora;

    @NotNull
    private String Date;

    public ClassSession() {

    }

    public ClassSession(@JsonProperty ("_id") ObjectId id, Classroom classroom, Subject subject, @JsonProperty ("student") String student, @JsonProperty ("hour") String hour, @JsonProperty("date") String date) {
        this.id = id;
        this.classroom = classroom;
        this.subject = subject;
        this.student = student;
        Hora = hour;
        Date = date;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
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
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    @NonNull
    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
