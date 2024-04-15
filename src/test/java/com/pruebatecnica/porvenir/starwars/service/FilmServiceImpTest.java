package com.pruebatecnica.porvenir.starwars.service;

import com.pruebatecnica.porvenir.starwars.dto.request.FilmRequestDTO;
import com.pruebatecnica.porvenir.starwars.dto.response.FilmResponseDTO;
import com.pruebatecnica.porvenir.starwars.entity.FilmEntity;
import com.pruebatecnica.porvenir.starwars.repository.FilmRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmServiceImpTest {

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FilmServiceImp filmService;

    public void testGetFilmEntityByIdSwapi_Exito() {
        String id = "1";
        FilmEntity filmEntity = new FilmEntity();
        filmEntity.setId(1L);
        filmEntity.setTitle("Una Nueva Esperanza");
        ResponseEntity<FilmEntity> responseEntity = new ResponseEntity<>(filmEntity, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(FilmEntity.class))).thenReturn(responseEntity);
        when(filmRepository.save(any())).thenReturn(filmEntity);

        FilmResponseDTO result = filmService.getFilmEntityByIdSwapi(id);

        assertNotNull(result);
        assertEquals(filmEntity.getTitle(), result.getTitle());
    }


    @Test
    public void testUpdateFilm_Exito() {
        String id = "1";
        FilmRequestDTO filmRequestDTO = new FilmRequestDTO();
        filmRequestDTO.setTitle("Título Actualizado");
        FilmEntity filmEntity = new FilmEntity();
        filmEntity.setId(1L);
        Optional<FilmEntity> optionalFilmEntity = Optional.of(filmEntity);
        when(filmRepository.findById(anyLong())).thenReturn(optionalFilmEntity);
        when(filmRepository.save(any())).thenReturn(filmEntity);

        FilmResponseDTO result = filmService.updateFilm(id, filmRequestDTO);

        assertNotNull(result);
        assertEquals(filmRequestDTO.getTitle(), result.getTitle());
    }

    @Test
    public void testGetFilms() {
        List<FilmEntity> films = Collections.singletonList(new FilmEntity());
        when(filmRepository.findAll()).thenReturn(films);

        List<FilmEntity> result = filmService.getFilms();

        assertEquals(films.size(), result.size());
    }

}
