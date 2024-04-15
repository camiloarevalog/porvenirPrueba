package com.pruebatecnica.porvenir.starwars.service;

import com.pruebatecnica.porvenir.starwars.dto.request.FilmRequestDTO;
import com.pruebatecnica.porvenir.starwars.dto.response.FilmResponseDTO;
import com.pruebatecnica.porvenir.starwars.entity.FilmEntity;

import java.util.List;

public interface FilmService {

    public FilmResponseDTO getFilmEntityByIdSwapi(String id);

    public FilmEntity save(FilmEntity filmEntity);

    public FilmResponseDTO updateFilm(String id, FilmRequestDTO filmRequestDTO);

    public List<FilmResponseDTO> getFilms();

    public boolean deleteFilmById(String id);

}
