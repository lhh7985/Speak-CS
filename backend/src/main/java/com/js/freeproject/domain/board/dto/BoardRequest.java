package com.js.freeproject.domain.board.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.js.freeproject.domain.board.domain.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BoardRequest {

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    private List<MultipartFile> boardFiles;

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .description(description)
                .build();
    }

}
