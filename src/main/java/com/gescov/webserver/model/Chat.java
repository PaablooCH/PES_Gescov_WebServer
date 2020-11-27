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
@Document(collection = "chats")
public class Chat {

    @Id
    private String id;

    @NotNull
    private String partA;

    @NotNull
    private String partB;


    public Chat (@JsonProperty("id") String id, @JsonProperty("partA") String partA, @JsonProperty("partB") String partB){
        this.id = id;
        this.partA = partA;
        this.partB = partB;
    }
}
