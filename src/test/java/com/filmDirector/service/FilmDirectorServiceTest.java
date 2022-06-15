package com.filmDirector.service;

import com.filmDirector.domain.FilmDirector;
import com.filmDirector.exception.ResourceNotFoundException;
import com.filmDirector.repository.FilmDirectorRepository;
import com.filmDirector.util.FilmDirectorCreator;
import com.filmDirector.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class FilmDirectorServiceTest {
    @InjectMocks
    private FilmDirectorService filmDirectorService;

    @Mock
    private Utils utils;
    @Mock
    private FilmDirectorRepository filmDirectorRepository;

    @Test
    @DisplayName("listAll returns a List of film director when successful")
    public void listAll_returns_a_List_of_filmDirector_when_successful() {
        String expectedName = FilmDirectorCreator.createValidFilmDirector().getName();

        List<FilmDirector> filmDirectorList = List.of(FilmDirectorCreator.createValidFilmDirector());
        when(filmDirectorService.listAll()).
                thenReturn(filmDirectorList);

        Assertions.assertThat(filmDirectorList).isNotNull();
        Assertions.assertThat(filmDirectorList).isNotEmpty();
        Assertions.assertThat(filmDirectorList.get(0).getName()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("findById returns a List of film director when successful")
    public void findById_returns_a_filmDirector_when_successful() {
        String expectedName = FilmDirectorCreator.createValidFilmDirector().getName();

        when(filmDirectorRepository.findByName(anyString())).
                thenReturn(List.of(FilmDirectorCreator.createValidUpdatedFilmDirector()));

        List<FilmDirector> filmDirectorList = filmDirectorService.findByName("Agnès Varda");

        Assertions.assertThat(filmDirectorList).isNotNull();
        Assertions.assertThat(filmDirectorList).isNotEmpty();
        Assertions.assertThat(filmDirectorList.get(0).getName()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("findByName returns a List of film director when successful")
    public void findByName_returns_a_filmDirector_when_successful() {
        Integer expectedId = FilmDirectorCreator.createValidFilmDirector().getId();

        when(utils.findDirectorOrThrowNotFound(anyInt(), any(FilmDirectorRepository.class)))
                .thenReturn(FilmDirectorCreator.createValidFilmDirector());

        when(filmDirectorRepository.findById(anyInt()))
                .thenReturn(Optional.of(FilmDirectorCreator.createValidFilmDirector()));

        FilmDirector filmDirector = filmDirectorService.findById(1);

        Assertions.assertThat(filmDirector).isNotNull();

        Assertions.assertThat(filmDirector.getId()).isNotNull();

        Assertions.assertThat(filmDirector.getId()).isEqualTo(expectedId);

    }

    @Test
    @DisplayName("save creates an filmDirector when successful")
    public void save_creates_an_filmDirector_when_successful() {
        Integer expectedId = FilmDirectorCreator.createValidFilmDirector().getId();

        when(filmDirectorRepository.save(FilmDirectorCreator.createValidFilmDirector()))
                .thenReturn(FilmDirectorCreator.createValidFilmDirector());

        FilmDirector filmDirectorToBeSaved = FilmDirectorCreator.createValidFilmDirector();

        FilmDirector filmDirector = filmDirectorService.save(filmDirectorToBeSaved);

        Assertions.assertThat(filmDirector).isNotNull();

        Assertions.assertThat(filmDirector.getId()).isNotNull();

        Assertions.assertThat(filmDirector.getId()).isEqualTo(expectedId);
    }

    @Test
    @DisplayName("delete removes an film director when successful")
    public void delete_removes_an_filmDirector_when_successful() {
        doNothing().when(filmDirectorRepository).delete(any(FilmDirector.class));

        Assertions.assertThatCode(() -> filmDirectorService.delete(1))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("delete throws ResourceNotFoundException when the filmDirector does not exist")
    public void delete_throws_ResourceNotFoundException_when_the_filmDirector_does_not_exist() {
        when(utils.findDirectorOrThrowNotFound(anyInt(), any(FilmDirectorRepository.class)))
                .thenThrow(new ResourceNotFoundException("Film Director not found"));

        Assertions.assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(()-> filmDirectorService.delete(1));
    }

    @Test
    @DisplayName("update save updated an filmDirector when successful")
    public void update_save_an_updated_filmDirector_when_successful() {
        FilmDirector filmDirectorUpdated = FilmDirectorCreator.createValidUpdatedFilmDirector();

        String expectedName = filmDirectorUpdated.getName();

        when(filmDirectorRepository.save(FilmDirectorCreator.createValidUpdatedFilmDirector()))
                .thenReturn(FilmDirectorCreator.createValidUpdatedFilmDirector());

        FilmDirector filmDirector = filmDirectorService.save(filmDirectorUpdated);

        Assertions.assertThat(filmDirector).isNotNull();

        Assertions.assertThat(filmDirector.getId()).isNotNull();

        Assertions.assertThat(filmDirector.getName()).isEqualTo(expectedName);

    }
}
