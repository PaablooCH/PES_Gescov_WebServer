package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@Document(collection = "messages")
public class Message {

    @Id
    private String id;

    @NotNull
    private String creatorID;

    @NotNull
    private String chatID;

    @NotNull
    private String text;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private String date;

    @JsonFormat(pattern = "HH-mm-ss", shape = JsonFormat.Shape.STRING)
    private String hour;

    public Message (@JsonProperty("id") String id, @JsonProperty("creator") String creator,
                    @JsonProperty("chat") String chat, @JsonProperty ("text") String text,
                    @JsonProperty ("hour") String hour, @JsonProperty("date") String date){
        this.id = id;
        this.creatorID = creator;
        this.chatID = chat;
        this.text = text;
        this.date = date;
        this.hour = hour;
    }

}
