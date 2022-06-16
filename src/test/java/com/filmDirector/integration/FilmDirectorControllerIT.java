package com.filmDirector.integration;

import com.filmDirector.domain.FilmDirector;
import com.filmDirector.repository.FilmDirectorRepository;
import com.filmDirector.util.FilmDirectorCreator;
import com.filmDirector.utils.Utils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FilmDirectorControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;
    @MockBean
    private FilmDirectorRepository filmDirectorRepository;
    @MockBean
    private Utils utils;


    @Test
    @DisplayName("listAll returns a List of filmDirectors when successful")
    public void listAll_returns_a_List_of_filmDirectors_when_successful() {
        String expectedName = FilmDirectorCreator.createValidFilmDirector().getName();

        when(filmDirectorRepository.findAll())
                .thenReturn(List.of(FilmDirectorCreator.createValidFilmDirector()));

        List<FilmDirector> filmDirectorList = testRestTemplate.exchange("/filmDirector/", HttpMethod.GET, null, new ParameterizedTypeReference<List<FilmDirector>>() {
        }).getBody();


        Assertions.assertThat(filmDirectorList).isNotNull();

        Assertions.assertThat(filmDirectorList).isNotEmpty();

        Assertions.assertThat(filmDirectorList.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById return an filmDirector when successful")
    public void findById_return_an_filmDirector_when_successful() {
        Integer expectedId = FilmDirectorCreator.createValidFilmDirector().getId();

        when(utils.findDirectorOrThrowNotFound(anyInt(), any(FilmDirectorRepository.class)))
                .thenReturn(FilmDirectorCreator.createValidFilmDirector());

        when(filmDirectorRepository.findById(anyInt())).
                thenReturn(Optional.of(FilmDirectorCreator.createValidFilmDirector()));

        FilmDirector filmDirector = testRestTemplate.getForObject("/filmDirector/1", FilmDirector.class);

        Assertions.assertThat(filmDirector).isNotNull();

        Assertions.assertThat(filmDirector.getId()).isNotNull();

        Assertions.assertThat(filmDirector.getId()).isEqualTo(expectedId);

    }

    @Test
    @DisplayName("findByName returns an filmDirector when successful")
    public void findByName_returns_an_filmDirector_when_successful() {
        String expectedName = FilmDirectorCreator.createValidFilmDirector().getName();

        when(filmDirectorRepository.findByName(anyString()))
                .thenReturn(List.of(FilmDirectorCreator.createValidFilmDirector()));

        List<FilmDirector> filmDirectorList = testRestTemplate.exchange("/filmDirector/find?name='Agnès Varda'", HttpMethod.GET, null, new ParameterizedTypeReference<List<FilmDirector>>() {
        }).getBody();

        Assertions.assertThat(filmDirectorList).isNotNull();

        Assertions.assertThat(filmDirectorList).isNotNull();

        Assertions.assertThat(filmDirectorList).isNotEmpty();

        Assertions.assertThat(filmDirectorList.get(0).getName()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("save creates an filmDirector when successful")
    public void save_creates_an_filmDirector_when_successful() {
        Integer expectedId = FilmDirectorCreator.createValidFilmDirector().getId();

        when(filmDirectorRepository.save(FilmDirectorCreator.createFilmDirectorToBeSaved()))
                .thenReturn(FilmDirectorCreator.createValidFilmDirector());

        FilmDirector filmDirectorToBeSaved = FilmDirectorCreator.createFilmDirectorToBeSaved();

        FilmDirector filmDirector = testRestTemplate.exchange("/filmDirector", HttpMethod.POST,
                createJsonHttpEntity(filmDirectorToBeSaved), FilmDirector.class).getBody();

        Assertions.assertThat(filmDirector).isNotNull();

        Assertions.assertThat(filmDirector.getId()).isNotNull();

        Assertions.assertThat(filmDirector.getId()).isEqualTo(expectedId);

    }

    @Test
    @DisplayName("delete removes an filmDirector when successful")
    public void delete_removes_an_filmDirector_when_successful() {

        when(utils.findDirectorOrThrowNotFound(anyInt(), any(FilmDirectorRepository.class)))
                .thenReturn(FilmDirectorCreator.createValidFilmDirector());

        ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/filmDirector/1",HttpMethod.DELETE, null, Void.class);

        Assertions.assertThat(responseEntity).isNotNull();

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThat(responseEntity.getBody()).isNull();
    }

    @Test
    @DisplayName("update save updated an filmDirector when successful")
    public void update_save_updated_an_filmDirector_when_successful() {

        FilmDirector validFilmDirector = FilmDirectorCreator.createValidFilmDirector();

        when(filmDirectorRepository.save(FilmDirectorCreator.createValidUpdatedFilmDirector()))
                .thenReturn(FilmDirectorCreator.createValidUpdatedFilmDirector());

        ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/filmDirector", HttpMethod.PUT, createJsonHttpEntity(validFilmDirector), Void.class);

        Assertions.assertThat(responseEntity).isNotNull();

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThat(responseEntity.getBody()).isNull();

    }

    private HttpEntity<FilmDirector> createJsonHttpEntity(FilmDirector filmDirector) {
        return new HttpEntity<>(filmDirector, createJsonHeader());
    }

    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}