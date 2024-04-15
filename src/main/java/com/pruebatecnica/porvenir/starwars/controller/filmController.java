package com.pruebatecnica.porvenir.starwars.controller;

import com.pruebatecnica.porvenir.starwars.dto.request.FilmRequestDTO;
import com.pruebatecnica.porvenir.starwars.dto.response.FilmResponseDTO;
import com.pruebatecnica.porvenir.starwars.entity.FilmEntity;
import com.pruebatecnica.porvenir.starwars.service.FilmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
public class filmController {

    private final FilmService filmService;


    public filmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getFilmByIdASwapi(@PathVariable("id") String id) {

        try {
            FilmResponseDTO filmResponseDTO = filmService.getFilmEntityByIdSwapi(id);
            if (filmResponseDTO != null) {
                return new ResponseEntity<>(filmResponseDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("error en la solicitud", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<FilmResponseDTO> updateFilm(@PathVariable String id,
                                                      @RequestBody FilmRequestDTO filmRequestDto) {
        FilmResponseDTO filmResponseDTO = filmService.updateFilm(id, filmRequestDto);
        return new ResponseEntity<>(filmResponseDTO, HttpStatus.OK);

    }


    @GetMapping
    public ResponseEntity<List<FilmEntity>> getFilms() {
        return new ResponseEntity<>(filmService.getFilms(), HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteFilm(@PathVariable("id") String id) {

        boolean deleted = filmService.deleteFilmById(id);

        if (deleted) {
            return ResponseEntity.ok("Se elimino la pelicula exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
