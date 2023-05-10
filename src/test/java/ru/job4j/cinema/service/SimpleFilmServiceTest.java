package ru.job4j.cinema.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

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
    void whenFindByIdhenReturnFilmDto() {
        when(filmRepository.findById(any(Integer.class)))
            .thenReturn(new Film(55, "тест", "тест", "2000", 1, 1, 1, 1));
        when(genreRepository.findById(any(Integer.class)))
            .thenReturn(new Genre(1, "Приключения"));
        
        var expectedFilmDto = new FilmDto(55, "тест", "тест", 
                "2000", 1, 1, "Приключения", 1);
        
        var atualFilmDto = filmService.findById(55);
        
        assertThat(atualFilmDto).usingRecursiveComparison()
            .isEqualTo(expectedFilmDto);
    }
    
    @Test
    void whenFindFilmByInvalidIdhenReturnIllegalArgumentExeption() {
        when(filmRepository.findById(any(Integer.class)))
            .thenReturn(null);
        
        assertThatThrownBy(() -> filmService.findById(2))
            .isInstanceOf(IllegalArgumentException.class)
            .message().isEqualTo("Film id=2 not found!");
    }    
    
    @Test
    void whenFindByInvalidGenreIdhenReturnIllegalArgumentExeption() {
        when(filmRepository.findById(any(Integer.class)))
            .thenReturn(new Film(55, "тест", "тест", "2000", 1, 1, 1, 1));
        
        when(genreRepository.findById(any(Integer.class)))
            .thenReturn(null);
        
        assertThatThrownBy(() -> filmService.findById(55))
            .isInstanceOf(IllegalArgumentException.class)
            .message().isEqualTo("Genre id=1 not found!");
    }
    
    @Test
    void whenFindAllThenReturnAll() {
        var films = List.of(
                new Film(55, "тест", "тест", "2000", 1, 1, 1, 1),
                new Film(56, "тест2", "тест2", "2000", 2, 1, 1, 1),
                new Film(57, "тест3", "тест3", "2000", 3, 1, 1, 1)                
                ); 
                
        when(filmRepository.findAll()).thenReturn(films);
        when(genreRepository.findById(1))
            .thenReturn(new Genre(1, "Приключения"));
        when(genreRepository.findById(2))
            .thenReturn(new Genre(2, "Фантастика"));
        when(genreRepository.findById(3))
            .thenReturn(new Genre(3, "Ужас"));
        
        var expectedFilmsDto =  List.of(
                new FilmDto(55, "тест", "тест", "2000", 1, 1, "Приключения", 1),
                new FilmDto(56, "тест2", "тест2", "2000", 1, 1, "Фантастика", 1),
                new FilmDto(57, "тест3", "тест3", "2000", 1, 1, "Ужас", 1)
                );
        
        var actualFilmsDto = filmService.findAll();
        
        assertThat(actualFilmsDto).usingRecursiveComparison()
            .isEqualTo(expectedFilmsDto);
    }

}
