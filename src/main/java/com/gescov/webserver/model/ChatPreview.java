package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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

    @NotNull
    private String userNameB;

    private String lastText;

    private String lastTextHour;


    public ChatPreview (@JsonProperty("id") String id, @JsonProperty("chat") String chatID, @JsonProperty("userNameA") String userNameA,
                        @JsonProperty("userNameB") String userNameB, @JsonProperty("lastText") String lastText, @JsonProperty("lastTextHour") String lastTextHour){
        this.id = id;
        this.chatID = chatID;
        this.userNameA = userNameA;
        this.userNameB = userNameB;
        this.lastText = lastText;
        this.lastTextHour = lastTextHour;
    }

    public ChatPreview(String id, String u1, String u2) {
        this.chatID = id;
        this.userNameA = u1;
        this.userNameB = u2;
    }
}