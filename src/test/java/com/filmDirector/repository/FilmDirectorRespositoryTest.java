package com.filmDirector.repository;

import com.filmDirector.domain.FilmDirector;
import com.filmDirector.util.FilmDirectorCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Film Director Repository Test")
public class FilmDirectorRespositoryTest {

    @Autowired
    private FilmDirectorRepository filmDirectorRepository;

    @Test
    @DisplayName("Save create film director when successful")
    public void save_persist_film_director_when_successful(){

        FilmDirector filmDirector = FilmDirectorCreator.createFilmDirectorToBeSaved();

        FilmDirector savedFilmDirector = filmDirectorRepository.save(filmDirector);
        Assertions.assertThat(savedFilmDirector.getId()).isNotNull();
        Assertions.assertThat(savedFilmDirector.getName()).isNotNull();
        Assertions.assertThat(savedFilmDirector.getName()).isEqualTo(filmDirector.getName());
    }

    @Test
    @DisplayName("Save updates film director when successful")
    public void save_update_film_director_when_successful(){

        FilmDirector filmDirector = FilmDirectorCreator.createFilmDirectorToBeSaved();

        FilmDirector savedFilmDirector = filmDirectorRepository.save(filmDirector);

        savedFilmDirector.setName("Another incredible film director, from Argentina, Lucrecia Martel");

        FilmDirector updatedFilmDirector = filmDirectorRepository.save(savedFilmDirector);

        Assertions.assertThat(savedFilmDirector.getId()).isNotNull();
        Assertions.assertThat(savedFilmDirector.getName()).isNotNull();
        Assertions.assertThat(savedFilmDirector.getName()).isEqualTo(updatedFilmDirector.getName());
    }

    @Test
    @DisplayName("Delete removes film director when successful")
    public void remove_deleted_film_director_when_successful() {
        FilmDirector filmDirector = FilmDirectorCreator.createFilmDirectorToBeSaved();

        FilmDirector savedFilmDirector = filmDirectorRepository.save(filmDirector);

        filmDirectorRepository.delete(filmDirector);

        Optional<FilmDirector> filmDirectorDeleted = filmDirectorRepository.findById(savedFilmDirector.getId());

        Assertions.assertThat(filmDirectorDeleted.isEmpty()).isTrue();

    }

    @Test
    @DisplayName("Find by name returns empty when no filmDirector is found")
    public void find_by_name_return_empty_when_no_filmDirector_is_found(){
        String name = "Tilda Swinton";

        List<FilmDirector> filmDirectorList = filmDirectorRepository.findByName(name);

        Assertions.assertThat(filmDirectorList).isEmpty();
    }

    @Test
    @DisplayName("Trow an error message when name is empty")
    public void trow_an_error_message_when_name_is_empty(){
        FilmDirector filmDirector = new FilmDirector();

        Assertions.assertThatThrownBy(()-> filmDirectorRepository.save(filmDirector))
                .isInstanceOf(ConstraintViolationException.class);
    }
}
