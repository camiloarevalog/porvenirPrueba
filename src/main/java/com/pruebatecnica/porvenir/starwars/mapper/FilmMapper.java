package com.pruebatecnica.porvenir.starwars.mapper;

import com.pruebatecnica.porvenir.starwars.dto.request.FilmRequestDTO;
import com.pruebatecnica.porvenir.starwars.dto.response.FilmResponseDTO;
import com.pruebatecnica.porvenir.starwars.entity.FilmEntity;
import com.pruebatecnica.porvenir.starwars.model.Film;
import org.springframework.stereotype.Component;


@Component
public class FilmMapper {

    public static Film toFilm(FilmEntity filmEntity) {
        return Film.builder()
                .episodeId(filmEntity.getEpisodeId())
                .title(filmEntity.getTitle())
                .releaseDate(filmEntity.getReleaseDate()).build();
    }

    public static FilmEntity toFilmEntity(FilmRequestDTO filmRequestDTO) {
        FilmEntity filmEntity = new FilmEntity();

        filmEntity.setTitle(filmRequestDTO.getTitle());
        filmEntity.setEpisodeId(filmRequestDTO.getEpisodeId());
        filmEntity.setReleaseDate(filmRequestDTO.getReleaseDate());

        return filmEntity;
    }

    public static FilmResponseDTO toFilmResponseDto(Film film) {
        FilmResponseDTO filmResponseDTO = new FilmResponseDTO();
        filmResponseDTO.setEpisodeId(film.getEpisodeId());
        filmResponseDTO.setTitle(film.getTitle());
        filmResponseDTO.setReleaseDate(film.getReleaseDate());
        return filmResponseDTO;

    }

}
