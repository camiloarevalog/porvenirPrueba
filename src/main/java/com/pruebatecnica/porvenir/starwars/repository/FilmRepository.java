package com.pruebatecnica.porvenir.starwars.repository;

import com.pruebatecnica.porvenir.starwars.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<FilmEntity,Long> {

    FilmEntity getFilmEntityById(Long id);

}
