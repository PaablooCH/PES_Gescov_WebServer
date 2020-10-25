package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Contagion {

    @Id
    private ObjectId id;

    private LocalDate startContagion; //Primary key

    private LocalDate endContagion;

    @NotNull
    String nameInfected; //Primary key

    public String getNameCen() {
        return nameCen;
    }

    public void setNameCen(String nameCen) {
        this.nameCen = nameCen;
    }

    String nameCen;

    public Contagion(){}

    public Contagion(@JsonProperty("_id") ObjectId id,
                     @JsonProperty("nameInfected") String nameInfected, @JsonProperty("nameCen") String nameCen) {
        this.id = id;
        this.nameInfected = nameInfected;
        this.startContagion = LocalDate.now();
        this.endContagion = null;
        this.nameCen = nameCen;
    }

    public Contagion(ObjectId id, String nameInfected, LocalDate startContagion, LocalDate endContagion, String nameCen) {
        this.id = id;
        this.nameInfected = nameInfected;
        this.startContagion = startContagion;
        this.endContagion = endContagion;
        this.nameCen = nameCen;
    }

    public LocalDate getStartContagion() {
        return startContagion;
    }

    public LocalDate getEndContagion() {
        return endContagion;
    }

    public String getNameInfected() { return nameInfected; }

    public ObjectId getId() { return id; }

    public void setStartContagion(LocalDate startContagion) {
        this.startContagion = startContagion;
    }

    public void setEndContagion(@JsonProperty("endContagion") LocalDate endContagion) {
        this.endContagion = endContagion;
    }

    public void setNameInfected(String nameInfected) { this.nameInfected = nameInfected; }

    public void setId(ObjectId id) { this.id = id; }
}
