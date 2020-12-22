package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Document(collection = "wall")
public class WallEntry {

    @Id
    private String id;

    private String schoolID;

    private String text;

    private LocalDateTime hour;

    public WallEntry(@JsonProperty ("id") String id, @JsonProperty("schoolID") String schoolID, @JsonProperty("text")String text){
        this.id = id;
        this.schoolID = schoolID;
        this.text = text;
        this.hour = LocalDateTime.now();
    }
}
