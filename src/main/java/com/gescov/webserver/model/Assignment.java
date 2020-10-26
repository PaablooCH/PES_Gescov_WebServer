package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Assignment {

    @Id
    private ObjectId id;

    private int posCol;

    private int posRow;

    private ClassSession classSession;

    private User nameSt;

    public Assignment() {
    }

    public Assignment(@JsonProperty("id") ObjectId id, @JsonProperty("posCol") int posCol,
                      @JsonProperty("posRow") int posRow, @JsonProperty("classSession") ClassSession classSession,
                      @JsonProperty("nameSt") User nameSt) {
        this.id = id;
        this.posCol = posCol;
        this.posRow = posRow;
        this.classSession = classSession;
        this.nameSt = nameSt;
    }

    public Assignment(int posCol, int posRow, ClassSession classSession, User nameSt) {
        this.posCol = posCol;
        this.posRow = posRow;
        this.classSession = classSession;
        this.nameSt = nameSt;
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

    public User getNameSt() {
        return nameSt;
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

    public void setNameSt(User nameSt) {
        this.nameSt = nameSt;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
