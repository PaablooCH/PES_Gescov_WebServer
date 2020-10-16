package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Contagion {

    private Date startContagion;

    private Date endContagion;

    public Contagion(){
        this.startContagion = new Date(); //Date() : Creates date object representing current date and time.
    }

    public Date getStartContagion() {
        return startContagion;
    }

    public Date getEndContagion() {
        return endContagion;
    }

    public void setStartContagion(Date startContagion) {
        this.startContagion = startContagion;
    }

    public void setEndContagion(@JsonProperty("startContagion") Date endContagion) {
        this.endContagion = endContagion;
    }
}
