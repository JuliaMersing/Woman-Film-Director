package com.filmDirector.service;

import com.filmDirector.domain.FilmDirector;
import com.filmDirector.repository.FilmDirectorRepository;
import com.filmDirector.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmDirectorService {

    public final Utils utils;
    public final FilmDirectorRepository filmDirectorRepository;

    public List <FilmDirector> listAll(){
        return filmDirectorRepository.findAll();
    }

    public FilmDirector findById(int id){
        return utils.findDirectorOrThrowNotFound(id, filmDirectorRepository);
    }

    public List <FilmDirector> findByName(String name){
        return filmDirectorRepository.findByName(name);
    }
    @Transactional

    public FilmDirector save (FilmDirector filmDirector){
        return filmDirectorRepository.save(filmDirector);
    }

    public void delete (int id){
        filmDirectorRepository.delete(utils.findDirectorOrThrowNotFound(id, filmDirectorRepository));
    }

    public void update(FilmDirector filmDirector){
        filmDirectorRepository.save(filmDirector);
    }

}
