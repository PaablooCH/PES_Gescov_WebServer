package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Assignment {

    int posCol;

    int posRow;

    String classSession;

    String nameSt;

    public Assignment(@JsonProperty("posCol") int posCol,
                     @JsonProperty("posRow") int posRow, @JsonProperty("classSession") String classSession,
                      @JsonProperty("nameSt") String nameSt) {
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
}
