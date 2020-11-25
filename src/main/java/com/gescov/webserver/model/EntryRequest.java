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
@Document(collection = "entryRequests")
public class EntryRequest {

    @Id
    private String id;

    private LocalDateTime requestDate;

    private LocalDateTime answerDate;

    private RequestState status;

    @NotNull(message = "Request's userID must not be null")
    private String userID;

    @NotNull(message = "Request's schoolID must not be null")
    private String schoolID;

    public enum RequestState {
        ACCEPTED ("accepted"),
        REJECTED ("rejected"),
        PENDING ("pending");

        private final String value;

        RequestState(String value) {
            this.value = value;
        }
    }

    public EntryRequest(@JsonProperty("id") String id,
                        @JsonProperty("userID") String userID,
                        @JsonProperty("schoolID") String schoolID) {
        this.id = id;
        this.requestDate = LocalDateTime.now();
        this.answerDate = null;
        this.status = RequestState.PENDING;
        this.userID = userID;
        this.schoolID = schoolID;
    }

}
