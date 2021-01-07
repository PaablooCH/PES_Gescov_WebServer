package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class WallEntryReply {

    private String username;

    private String text;

    private LocalDateTime time;

    public WallEntryReply(@JsonProperty ("text") String text){
        this.username = "anonimo";
        this.text = text;
        this.time = LocalDateTime.now();
    }
}
