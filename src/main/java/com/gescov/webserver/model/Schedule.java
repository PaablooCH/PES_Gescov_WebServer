package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@Setter
@Getter
public class Schedule {

    @NotNull(message = "Day must not be null")
    private daysWeek day;

    @NotNull(message = "Start hour must not be null")
    private LocalTime start;

    @NotNull(message = "End hour must not be null")
    private LocalTime end;

    @NotNull(message = "Subject's id must not be null")
    private String idSubject;

    public enum daysWeek {
        MONDAY ("Monday"),
        TUESDAY ("Tuesday"),
        WEDNESDAY ("Wednesday"),
        THURSDAY ("Thursday"),
        FRIDAY ("Friday");

        private final String value;

        daysWeek(String value) {
            this.value = value;
        }
    }

    public Schedule(@JsonProperty("idSubject") String idSubject, @JsonProperty("start") String start,
                    @JsonProperty("end") String end, @JsonProperty("day") daysWeek day) {
        this.idSubject = idSubject;
        this.start = LocalTime.parse(start);
        this.end = LocalTime.parse(end);
        this.day = day;
    }

}
