package com.gescov.webserver.model;

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
@Document(collection = "chatPreview")
public class ChatPreview {

    @Id
    private String id;

    @NotNull
    private String chatID;

    @NotNull
    private String userNameA;

    private String userPictureA;

    @NotNull
    private String userNameB;

    private String userPictureB;

    private String lastText;

    private String lastTextHour;


    public ChatPreview (@JsonProperty("id") String id, @JsonProperty("chat") String chatID, @JsonProperty("userNameA") String userNameA,
                        @JsonProperty("userPictureA") String userPictureA, @JsonProperty("userNameB") String userNameB,
                        @JsonProperty("userPictureB") String userPictureB, @JsonProperty("lastText") String lastText,
                        @JsonProperty("lastTextHour") String lastTextHour){
        this.id = id;
        this.chatID = chatID;
        this.userNameA = userNameA;
        this.userPictureA = userPictureA;
        this.userPictureB = userPictureB;
        this.userNameB = userNameB;
        this.lastText = lastText;
        this.lastTextHour = lastTextHour;
    }

    public ChatPreview(String id, String u1, String pic1, String u2, String pic2) {
        this.chatID = id;
        this.userNameA = u1;
        this.userPictureA = pic1;
        this.userNameB = u2;
        this.userPictureB = pic2;
    }
}