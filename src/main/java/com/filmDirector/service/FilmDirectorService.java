package com.filmDirector.service;

import com.filmDirector.domain.FilmDirector;
import com.filmDirector.exception.ResourceNotFoundException;
import com.filmDirector.repository.FilmDirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmDirectorService {

    public final FilmDirectorRepository filmDirectorRepository;

    public List <FilmDirector> listAll(){
        return filmDirectorRepository.findAll();
    }

    private FilmDirector findById(int id){
        return filmDirectorRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Film director not found"));
    }

    public FilmDirector getFilmDirectorById(int id){
        return findById(id);
    }

    public List <FilmDirector> findByName(String name){
        return filmDirectorRepository.findByName(name);
    }

    public FilmDirector save (FilmDirector filmDirector){
        return filmDirectorRepository.save(filmDirector);
    }

    public void delete (int id){
        filmDirectorRepository.delete(findById(id));
    }

    public void update(FilmDirector filmDirector){
        filmDirectorRepository.save(filmDirector);
    }

}
