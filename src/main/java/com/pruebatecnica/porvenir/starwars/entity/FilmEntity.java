package com.pruebatecnica.porvenir.starwars.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "Film")
public class FilmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "episode_id")
    @JsonProperty("episode_id")
    private int episodeId;

    @JsonProperty("title")
    private String title;

    @Column(name = "release_date")
    @JsonProperty("release_date")
    private LocalDate releaseDate;
}
