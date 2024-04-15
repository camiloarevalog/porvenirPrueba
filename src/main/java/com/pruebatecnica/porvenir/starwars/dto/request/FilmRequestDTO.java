package com.pruebatecnica.porvenir.starwars.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilmRequestDTO {
    
    private int Id;

    @JsonProperty("episode_id")
    private int episodeId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("release_date")
    private LocalDate releaseDate;

}
