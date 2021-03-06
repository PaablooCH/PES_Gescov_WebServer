package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Document(collection = "tracingTest")
public class TracingTest {
    @Id
    private String id;

    private List<Boolean> answers;

    private String contagionID;

    private LocalDate dateTest;

    public TracingTest(@JsonProperty("id") String id,
                       @JsonProperty("contID") String contagionID,
                       @JsonProperty("answers") List<Boolean> answers) {
        this.id = id;
        this.contagionID = contagionID;
        this.answers = answers;
        this.dateTest = LocalDate.now();
    }

}
