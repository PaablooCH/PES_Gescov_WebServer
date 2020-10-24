package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

//@Document(collection = "classrooms")
public class Classroom {

    @Id
    private ObjectId id;

    @Id
    private String name;

    private int capacity;

    private int numRows;

    private int numCols;

    private String school;

    private String creator;


    public Classroom() {

    }

    public Classroom(@JsonProperty("id") ObjectId id,
                     @JsonProperty("name") String name,
                     @JsonProperty("capacity") int capacity,
                     @JsonProperty("numRows") int numRows,
                     @JsonProperty("numCols") int numCols,
                     @JsonProperty("school") String school,
                     @JsonProperty("creator") String creator) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.numRows = numRows;
        this.numCols = numCols;
        this.school = school;
        this.creator = creator;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @NonNull
    public String getSchool() {
        return school;
    }

    public void setSchool(@NonNull String school) {
        this.school = school;
    }

    @NonNull
    public String getCreator() {
        return creator;
    }

    public void setCreator(@NonNull String creator) {
        this.creator = creator;
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }

}
