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
    public ResponseEntity <List<FilmDirector>> listAll(){
        return ResponseEntity.ok(filmDirectorService.listAll());
    }

    @GetMapping(path="/{id}")
    public ResponseEntity <FilmDirector> findById(@PathVariable int id){
        return ResponseEntity.ok(filmDirectorService.findById(id));
    }

    @GetMapping(path="/find")
    public ResponseEntity <List<FilmDirector>> findByName(@RequestParam (value = "name") String name){
        return ResponseEntity.ok(filmDirectorService.findByName(name));
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FilmDirector> save (@RequestBody @Valid FilmDirector filmDirector){
        return ResponseEntity.ok(filmDirectorService.save(filmDirector));
    }

    @DeleteMapping(path= "{id}")
    public ResponseEntity<FilmDirector> delete(@PathVariable int id){
        filmDirectorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FilmDirector> update(@RequestBody FilmDirector filmDirector){
        filmDirectorService.update(filmDirector);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
