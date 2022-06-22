package com.filmDirector.controller;

import com.filmDirector.domain.FilmDirector;
import com.filmDirector.service.FilmDirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("filmDirector")
@RequiredArgsConstructor
public class FilmDirectorController {

    private final FilmDirectorService filmDirectorService;

    @GetMapping
    public List<FilmDirector> listAll(){
        return filmDirectorService.listAll();
    }

    @GetMapping("/{id}")
    public FilmDirector findById(@PathVariable int id){
        return filmDirectorService.getFilmDirectorById(id);
    }

    @GetMapping("/find")
    public List<FilmDirector> findByName(@RequestParam String name){
        return filmDirectorService.findByName(name);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public FilmDirector save (@RequestBody @Valid FilmDirector filmDirector){
        return filmDirectorService.save(filmDirector);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<FilmDirector> delete(@PathVariable int id){
        filmDirectorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public void update(@RequestBody FilmDirector filmDirector){
        filmDirectorService.update(filmDirector);
    }

}
