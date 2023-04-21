package ru.job4j.cinema.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.Genre;
import ru.job4j.cinema.repository.FilmRepository;
import ru.job4j.cinema.repository.GenreRepository;

class SimpleFilmServiceTest {
    
    FilmRepository filmRepository;
    
    GenreRepository genreRepository;
    
    FilmService filmService;
    
    @BeforeEach
    void initServices() {        
        filmRepository = mock(FilmRepository.class);
        
        genreRepository = mock(GenreRepository.class);
        
        filmService = new SimpleFilmService(filmRepository, genreRepository);
    }

    @Test
    void whenFindBiIdhenReturnSame() {
        when(filmRepository.findById(any(Integer.class)))
            .thenReturn(new Film(2, "Аватар", "Аватар", "2009", 3, 12, 140, 2));
        when(genreRepository.findById(any(Integer.class)))
            .thenReturn(new Genre(3, "Приключения"));
        
        var expectedFilmDto = new FilmDto(2, "Аватар", "Аватар", 
                "2009", 12, 140, "Приключения");
        
        var atualFilmDto = filmService.findById(2);
        
        assertThat(atualFilmDto).usingRecursiveComparison()
            .isEqualTo(expectedFilmDto);
    }

}
