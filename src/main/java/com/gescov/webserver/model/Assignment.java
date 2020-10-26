package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Assignment {

    @Id
    private ObjectId id;

    private int posCol;

    private int posRow;

    @DBRef
    private ClassSession classSession;

    @DBRef
    private User student;

    public Assignment() {
    }

    public Assignment(@JsonProperty("id") ObjectId id, @JsonProperty("posCol") int posCol,
                      @JsonProperty("posRow") int posRow, @JsonProperty("classSession") final ClassSession classSession,
                      @JsonProperty("student") final User student) {
        this.id = id;
        this.posCol = posCol;
        this.posRow = posRow;
        this.classSession = classSession;
        this.student = student;
    }

    public Assignment(int posCol, int posRow, ClassSession classSession, User nameSt) {
        this.posCol = posCol;
        this.posRow = posRow;
        this.classSession = classSession;
        this.student = nameSt;
    }

    public int getPosCol() {
        return posCol;
    }

    public int getPosRow() {
        return posRow;
    }

    public ClassSession getClassSession() {
        return classSession;
    }

    public User getStudent() {
        return student;
    }

    public ObjectId getId() {
        return id;
    }

    public void setPosCol(int posCol) {
        this.posCol = posCol;
    }

    public void setPosRow(int posRow) {
        this.posRow = posRow;
    }

    public void setClassSession(ClassSession classSession) {
        this.classSession = classSession;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
