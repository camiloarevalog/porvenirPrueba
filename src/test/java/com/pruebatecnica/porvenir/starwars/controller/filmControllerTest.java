package com.pruebatecnica.porvenir.starwars.controller;

import com.pruebatecnica.porvenir.starwars.dto.request.FilmRequestDTO;
import com.pruebatecnica.porvenir.starwars.dto.response.FilmResponseDTO;
import com.pruebatecnica.porvenir.starwars.entity.FilmEntity;
import com.pruebatecnica.porvenir.starwars.service.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class filmControllerTest {

    @Mock
    private FilmService filmService;

    @InjectMocks
    private filmController filmController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetFilmByIdASwapi() {
        String id = "1";
        FilmResponseDTO filmResponseDTO = new FilmResponseDTO();
        when(filmService.getFilmEntityByIdSwapi(id)).thenReturn(filmResponseDTO);

        ResponseEntity<?> responseEntity = filmController.getFilmByIdASwapi(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(filmResponseDTO, responseEntity.getBody());
    }

    @Test
    void testUpdateFilm() {
        String id = "1";
        FilmRequestDTO filmRequestDto = new FilmRequestDTO();
        FilmResponseDTO filmResponseDTO = new FilmResponseDTO();
        when(filmService.updateFilm(id, filmRequestDto)).thenReturn(filmResponseDTO);

        ResponseEntity<FilmResponseDTO> responseEntity = filmController.updateFilm(id, filmRequestDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(filmResponseDTO, responseEntity.getBody());
    }


    @Test
    void testDeleteFilm() {
        String id = "1";
        when(filmService.deleteFilmById(id)).thenReturn(true);

        ResponseEntity<?> responseEntity = filmController.deleteFilm(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Se elimino la pelicula exitosamente", responseEntity.getBody());
    }

}