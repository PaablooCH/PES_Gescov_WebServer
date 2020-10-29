package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

//@Document(collection = "classrooms")
public class Classroom {

    @Id
    private ObjectId id;

    @Id
    private String name;

    private int capacity;

    private int numRows;

    private int numCols;

    @DBRef
    private School school;

    private String creator;


    public Classroom() {

    }

    public Classroom(@JsonProperty("id") ObjectId id,
                     @JsonProperty("name") String name,
                     @JsonProperty("capacity") int capacity,
                     @JsonProperty("numRows") int numRows,
                     @JsonProperty("numCols") int numCols,
                     @JsonProperty("school") final School school,
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
