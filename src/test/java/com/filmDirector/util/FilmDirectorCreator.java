package com.filmDirector.util;

import com.filmDirector.domain.FilmDirector;

public class FilmDirectorCreator {

    public static FilmDirector createFilmDirectorToBeSaved() {
        return FilmDirector.builder()
                .name("Agnès Varda")
                .build();
    }

    public static FilmDirector createValidFilmDirector() {
        return FilmDirector.builder()
                .name("Agnès Varda")
                .id(1)
                .build();
    }

    public static FilmDirector createValidUpdatedFilmDirector() {
        return FilmDirector.builder()
                .name("Isabel Coixet")
                .id(1)
                .build();
    }
}
