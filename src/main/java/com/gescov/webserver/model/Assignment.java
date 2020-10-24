package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Assignment {

    @Id
    private ObjectId id;

    private int posCol;

    private int posRow;

    private String classSession;

    private String nameSt;

    public Assignment() {
    }

    public Assignment(@JsonProperty("id") ObjectId id, @JsonProperty("posCol") int posCol,
                      @JsonProperty("posRow") int posRow, @JsonProperty("classSession") String classSession,
                      @JsonProperty("nameSt") String nameSt) {
        this.id = id;
        this.posCol = posCol;
        this.posRow = posRow;
        this.classSession = classSession;
        this.nameSt = nameSt;
    }

    public Assignment(int posCol, int posRow, String classSession, String nameSt) {
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

    public String getClassSession() {
        return classSession;
    }

    public String getNameSt() {
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

    public void setClassSession(String classSession) {
        this.classSession = classSession;
    }

    public void setNameSt(String nameSt) {
        this.nameSt = nameSt;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
