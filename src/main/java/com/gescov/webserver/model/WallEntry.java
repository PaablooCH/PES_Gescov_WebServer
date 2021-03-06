package com.gescov.webserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Document(collection = "wall")
public class WallEntry {

    @Id
    private String id;

    private String schoolID;

    private String title;

    private String text;

    private LocalDateTime time;

    private List<WallEntryReply> replies;

    public WallEntry(@JsonProperty ("id") String id, @JsonProperty("schoolID") String schoolID,@JsonProperty("title") String title,
                     @JsonProperty("text")String text){
        this.id = id;
        this.schoolID = schoolID;
        this.title = title;
        this.text = text;
        this.time = LocalDateTime.now();
        this.replies = new ArrayList<>();
    }

    public void addReply(WallEntryReply reply){ replies.add(reply);}

}
