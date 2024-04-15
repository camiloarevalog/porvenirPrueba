package com.pruebatecnica.porvenir.starwars.service;

import com.pruebatecnica.porvenir.starwars.dto.request.FilmRequestDTO;
import com.pruebatecnica.porvenir.starwars.dto.response.FilmResponseDTO;
import com.pruebatecnica.porvenir.starwars.entity.FilmEntity;
import com.pruebatecnica.porvenir.starwars.mapper.FilmMapper;
import com.pruebatecnica.porvenir.starwars.model.Film;
import com.pruebatecnica.porvenir.starwars.repository.FilmRepository;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmServiceImp implements FilmService {

    private final FilmRepository filmRepository;
    private final RestTemplate restTemplate;
    private final String STAR_BASE_URL = "https://swapi.dev/api/films/";


    public FilmServiceImp(FilmRepository filmRepository, RestTemplate restTemplate) {
        this.filmRepository = filmRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public FilmResponseDTO getFilmEntityByIdSwapi(String id) {
        FilmResponseDTO filmResponseDTO;
        Long idFilm = stringToLong(id);
        FilmEntity filmEntitySave = new FilmEntity();

        if (!id.matches("\\d{1,3}")) {
            throw new IllegalArgumentException("error en la solicitud");
        }
        try {
            ResponseEntity<FilmEntity> response = restTemplate.exchange(STAR_BASE_URL + idFilm + "/", HttpMethod.GET,
                    null, FilmEntity.class);

            if (response.getStatusCode() == HttpStatus.OK) {

                filmEntitySave = response.getBody();
                filmResponseDTO = saveFilm(filmEntitySave);
                return filmResponseDTO;
            } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            } else {
                throw new RuntimeException("Error al comunicarse con el API de Star Wars");

            }
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }

    public FilmResponseDTO updateFilm(String id, FilmRequestDTO filmRequestDTO) {
        Optional<FilmEntity> filmSearch = filmRepository.findById(stringToLong(id));
        if (filmSearch.isEmpty()) {
            throw new IllegalArgumentException("No se encuentra la pelicula en la BD");
        }
        FilmEntity update = filmSearch.get();
        update.setTitle(filmRequestDTO.getTitle());
        update.setEpisodeId(filmRequestDTO.getEpisodeId());
        update.setReleaseDate(filmRequestDTO.getReleaseDate());

        FilmResponseDTO updateFilmResponseDTO = saveFilm(update);
        return updateFilmResponseDTO;

    }

    @Override
    public List<FilmResponseDTO> getFilms() {
        List<FilmEntity> filmsEntityList = filmRepository.findAll();

        return filmsEntityList.stream()
                .map(FilmMapper::toFilmResponseDtoOfFilmEntity)
                .collect(Collectors.toList());

    }

    @Override
    public boolean deleteFilmById(String id) {
        Long idFilm = stringToLong(id);
        if (filmRepository.existsById(idFilm)) {
            filmRepository.deleteById(idFilm);
            return true;
        } else {
            return false;
        }

    }

    public FilmResponseDTO saveFilm(FilmEntity filmEntity) {
        FilmEntity filmEntitySave = save(filmEntity);
        Film film = FilmMapper.toFilm(filmEntitySave);
        FilmResponseDTO filmResponseDTO = FilmMapper.toFilmResponseDto(film);
        return filmResponseDTO;
    }

    @Override
    public FilmEntity save(FilmEntity filmEntity) {
        return filmRepository.save(filmEntity);
    }

    private Long stringToLong(String id) {
        return Long.parseLong(id);
    }

    public boolean validateId(String id) {
        return id.matches("\\d{1,3}");

    }
}
