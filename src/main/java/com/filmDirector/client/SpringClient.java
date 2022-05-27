package com.filmDirector.client;

import com.filmDirector.domain.FilmDirector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<FilmDirector> filmDirectorResponseEntity = new RestTemplate()
                .getForEntity("http://localhost:8080/filmDirector/{id}", FilmDirector.class, 1);

        log.info("Response Entity {}", filmDirectorResponseEntity);

        log.info("Response Data {}", filmDirectorResponseEntity.getBody());

        FilmDirector filmDirector = new RestTemplate()
                .getForObject("http://localhost:8080/filmDirector/{id}", FilmDirector.class, 1);

        log.info("FilmDirector {}", filmDirector);

        FilmDirector[] filmDirectorArray = new RestTemplate()
                .getForObject("http://localhost:8080/filmDirector", FilmDirector[].class);

        log.info("FilmDirector Array {}", Arrays.toString(filmDirectorArray));

        ResponseEntity<List<FilmDirector>> exchangeFilmDirectorList = new RestTemplate()
                .exchange("http://localhost:8080/filmDirector", HttpMethod.GET, null, new ParameterizedTypeReference<List<FilmDirector>>() {
                });

        log.info("FilmDirector list {}", exchangeFilmDirectorList.getBody());

        FilmDirector newFilmDirectorToPost = FilmDirector.builder().name("Major Motoko Kusanagi").build();

        FilmDirector newFilmDirectorSaved = new RestTemplate()
                .exchange("http://localhost:8080/filmDirector", HttpMethod.POST, new HttpEntity<>(newFilmDirectorToPost, createJsonHeader()), FilmDirector.class)
                .getBody();

        log.info("Major Motoko Kusanagi saved id: {}", newFilmDirectorSaved.getId());

        newFilmDirectorSaved.setName("Claymore");

        ResponseEntity<Void> exchangeUpdated = new RestTemplate()
                .exchange("http://localhost:8080/filmDirector", HttpMethod.PUT,
                        new HttpEntity<>(newFilmDirectorSaved, createJsonHeader()), Void.class);

        log.info("Major Motoko Kusanagi updated status: {}", exchangeUpdated.getStatusCode());

        ResponseEntity<Void> exchangeDeleted = new RestTemplate()
                .exchange("http://localhost:8080/filmDirector/{id}", HttpMethod.DELETE,
                        null, Void.class, newFilmDirectorSaved.getId());

        log.info("Major Motoko Kusanagi deleted status: {}", exchangeUpdated.getStatusCode());
    }

    private static HttpHeaders createJsonHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
