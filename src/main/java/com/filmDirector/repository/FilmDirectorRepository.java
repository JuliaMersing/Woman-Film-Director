package com.filmDirector.repository;

import com.filmDirector.domain.FilmDirector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FilmDirectorRepository extends JpaRepository<FilmDirector, Integer> {

    List<FilmDirector> findByName(String name);
}