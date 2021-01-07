package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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

    private Message lastMessage;

    private LocalDateTime lastUpdate;

    public ChatPreview (@JsonProperty("id") String id, @JsonProperty("chat") String chatID, @JsonProperty("userNameA") String userNameA,
                        @JsonProperty("userPictureA") String userPictureA, @JsonProperty("userNameB") String userNameB,
                        @JsonProperty("userPictureB") String userPictureB, final Message lastMessage){
        this.id = id;
        this.chatID = chatID;
        this.userNameA = userNameA;
        this.userPictureA = userPictureA;
        this.userPictureB = userPictureB;
        this.userNameB = userNameB;
        this.lastMessage = lastMessage;
        this.lastUpdate = LocalDateTime.now();
    }

    public ChatPreview(String id, String u1, String pic1, String u2, String pic2, LocalDateTime update) {
        this.chatID = id;
        this.userNameA = u1;
        this.userPictureA = pic1;
        this.userNameB = u2;
        this.userPictureB = pic2;
        this.lastUpdate = update;
    }

}