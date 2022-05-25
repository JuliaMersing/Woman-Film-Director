package com.filmDirector.utils;

import com.filmDirector.domain.FilmDirector;
import com.filmDirector.exception.ResourceNotFoundException;
import com.filmDirector.repository.FilmDirectorRepository;
import org.springframework.stereotype.Component;

@Component
public class Utils {

    public FilmDirector findDirectorOrThrowNotFound(int id, FilmDirectorRepository filmDirectorRepository){
        return filmDirectorRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Film director not found"));
    }
}
