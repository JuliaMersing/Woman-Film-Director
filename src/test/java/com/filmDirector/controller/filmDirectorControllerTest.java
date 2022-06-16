package com.filmDirector.controller;

import com.filmDirector.domain.FilmDirector;
import com.filmDirector.service.FilmDirectorService;
import com.filmDirector.util.FilmDirectorCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class filmDirectorControllerTest {
    @InjectMocks
    private FilmDirectorController filmDirectorController;
    @Mock
    private FilmDirectorService filmDirectorService;

    @Test
    @DisplayName("list All returns a List of film directors when successful")
    public void list_All_returns_a_List_of_film_directors_when_successful(){
        Integer expectedId = FilmDirectorCreator.createValidFilmDirector().getId();

        when(filmDirectorService.save(FilmDirectorCreator.createFilmDirectorToBeSaved()))
                .thenReturn(FilmDirectorCreator.createValidFilmDirector());

        FilmDirector filmDirectorToBeSaved = FilmDirectorCreator.createFilmDirectorToBeSaved();

        FilmDirector filmDirector = filmDirectorController.save(filmDirectorToBeSaved).getBody();

        Assertions.assertThat(filmDirector).isNotNull();

        Assertions.assertThat(filmDirector.getId()).isNotNull();

        Assertions.assertThat(filmDirector.getId()).isEqualTo(expectedId);

    }

    @Test
    @DisplayName("findById returns a List of film director when successful")
    public void findById_returns_a_filmDirector_when_successful() {
      Integer expectedId = FilmDirectorCreator.createValidFilmDirector().getId();

      when(filmDirectorService.findById(anyInt())).thenReturn(FilmDirectorCreator.createValidFilmDirector());

      FilmDirector filmDirectorSaved = filmDirectorController.findById(1).getBody();

      Assertions.assertThat(filmDirectorSaved).isNotNull();
      Assertions.assertThat(filmDirectorSaved.getId()).isNotNull();
      Assertions.assertThat(filmDirectorSaved.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByName returns a List of film director when successful")
    public void findByName_returns_a_filmDirector_when_successful() {
        String expectedName = FilmDirectorCreator.createValidFilmDirector().getName();

        when(filmDirectorService.findByName(anyString())).thenReturn(List.of(FilmDirectorCreator.createValidFilmDirector()));

        List <FilmDirector> filmDirectorName = filmDirectorController.findByName("Agnès Varda").getBody();

        Assertions.assertThat(filmDirectorName).isNotNull();
        Assertions.assertThat(filmDirectorName).isNotEmpty();
        Assertions.assertThat(filmDirectorName.get(0).getName()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("save creates an filmDirector when successful")
    public void save_creates_an_filmDirector_when_successful() {
        Integer expectedId = FilmDirectorCreator.createValidFilmDirector().getId();

        when(filmDirectorService.save(FilmDirectorCreator.createFilmDirectorToBeSaved()))
                .thenReturn(FilmDirectorCreator.createValidFilmDirector());

        FilmDirector filmDirectorSaved = FilmDirectorCreator.createFilmDirectorToBeSaved();

        FilmDirector filmDirector = filmDirectorController.save(filmDirectorSaved).getBody();

        Assertions.assertThat(filmDirector).isNotNull();
        Assertions.assertThat(filmDirector.getId()).isNotNull();
        Assertions.assertThat(filmDirector.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("delete removes an filmDirector when successful")
    public void delete_removes_an_filmDirector_when_successful() {

        doNothing().when(filmDirectorService).delete(anyInt());

        ResponseEntity<FilmDirector> responseEntity = filmDirectorController.delete(1);

        Assertions.assertThat(responseEntity).isNotNull();

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThat(responseEntity.getBody()).isNull();
    }

    @Test
    @DisplayName("update save updated an filmDirector when successful")
    public void update_save_updated_an_filmDirector_when_successful() {

        when(filmDirectorService.save(FilmDirectorCreator.createValidUpdatedFilmDirector()))
                .thenReturn(FilmDirectorCreator.createValidUpdatedFilmDirector());

        ResponseEntity<FilmDirector> responseEntity = filmDirectorController.update(FilmDirectorCreator.createValidFilmDirector());

        Assertions.assertThat(responseEntity).isNotNull();

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThat(responseEntity.getBody()).isNull();

    }
}
