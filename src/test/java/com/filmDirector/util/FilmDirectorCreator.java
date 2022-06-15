package com.filmDirector.util;

import com.filmDirector.domain.FilmDirector;

public class FilmDirectorCreator {

    public static FilmDirector createFilmDirectorCreatorToBeSaved() {
        return FilmDirector.builder()
                .name("Agnès Varda")
                .build();
    }

    public static FilmDirector createValidFilmDirectorCreator() {
        return FilmDirector.builder()
                .name("Agnès Varda")
                .id(1)
                .build();
    }

    public static FilmDirector createValidUpdatedFilmDirectorCreator() {
        return FilmDirector.builder()
                .name("Isabel Coixet")
                .id(1)
                .build();
    }
}
