package ru.job4j.cinema.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.FilmRepository;
import ru.job4j.cinema.repository.FilmSessionRepository;
import ru.job4j.cinema.repository.HallRepository;

class SimpleFilmSessionServiceTest {
    
    FilmSessionRepository filmSessionRepository;
    
    FilmRepository filmRepository;
    
    HallRepository hallRepository;
    
    FilmSessionService filmSessionService;
    
    @BeforeEach
    void initServices() {  
        
        filmSessionRepository = mock(FilmSessionRepository.class);
        
        filmRepository = mock(FilmRepository.class);
        
        hallRepository = mock(HallRepository.class);
        
        filmSessionService = new SimpleFilmSessionService(
                filmSessionRepository, filmRepository, hallRepository);
    }

    @Test
    void whenFindByIdhenReturnFileSessionDto() {
        when(filmSessionRepository.findById(any(Integer.class)))
            .thenReturn(new FilmSession(1, 55, 156, 
                    LocalDateTime.of(2023, 4, 20, 9, 0), 
                    LocalDateTime.of(2023, 4, 20, 10, 20), 320));
        when(filmRepository.findById(any(Integer.class)))
            .thenReturn(new Film(55, "тест фильм", "тест", "2000", 1, 1, 1, 1));
        when(hallRepository.findById(any(Integer.class)))
            .thenReturn(new Hall(156, "Холл Тест", 5, 5, "Холл тест"));
        
        var expectedFilmSessionDto = new FilmSessionDto(1, "тест фильм", "Холл Тест",
                LocalDateTime.of(2023, 4, 20, 9, 0), 
                LocalDateTime.of(2023, 4, 20, 10, 20), 320);
        
        var atualFilSessionDto = filmSessionService.findById(1);
        
        assertThat(atualFilSessionDto).usingRecursiveComparison()
            .isEqualTo(expectedFilmSessionDto);
    }
    
    
    @Test
    void whenFindFilmSessionByInvalidIdhenReturnIllegalArgumentExeption() {
        when(filmSessionRepository.findById(any(Integer.class)))
            .thenReturn(null);
        
        assertThatThrownBy(() -> filmSessionService.findById(2))
            .isInstanceOf(IllegalArgumentException.class)
            .message().isEqualTo("Film session id=2 not found!");
    } 
    
    @Test
    void whenFindByInvalidFilmIdhenReturnIllegalArgumentExeption() {
        when(filmSessionRepository.findById(any(Integer.class)))
        .thenReturn(new FilmSession(1, 55, 156, 
                LocalDateTime.of(2023, 4, 20, 9, 0), 
                LocalDateTime.of(2023, 4, 20, 10, 20), 320));
        when(filmRepository.findById(any(Integer.class)))
            .thenReturn(null);
        when(hallRepository.findById(any(Integer.class)))
            .thenReturn(new Hall(156, "Холл Тест", 5, 5, "Холл тест"));
        
        assertThatThrownBy(() -> filmSessionService.findById(1))
            .isInstanceOf(IllegalArgumentException.class)
            .message().isEqualTo("Film id=55 not found!");
    }
    
    @Test
    void whenFindByInvalidHallIdhenReturnIllegalArgumentExeption() {
        when(filmSessionRepository.findById(any(Integer.class)))
        .thenReturn(new FilmSession(1, 55, 156, 
                LocalDateTime.of(2023, 4, 20, 9, 0), 
                LocalDateTime.of(2023, 4, 20, 10, 20), 320));
        when(filmRepository.findById(any(Integer.class)))
            .thenReturn(new Film(55, "тест фильм", "тест", "2000", 1, 1, 1, 1));
        when(hallRepository.findById(any(Integer.class)))
            .thenReturn(null);
        
        assertThatThrownBy(() -> filmSessionService.findById(1))
            .isInstanceOf(IllegalArgumentException.class)
            .message().isEqualTo("Hall id=156 not found!");
    }
    
    @Test
    void whenFindAllThenReturnAll() {
        var filmSessions = List.of(
                new FilmSession(1, 55, 156, 
                        LocalDateTime.of(2023, 4, 20, 9, 0), 
                        LocalDateTime.of(2023, 4, 20, 10, 20), 320),
                new FilmSession(2, 56, 156, 
                        LocalDateTime.of(2023, 4, 20, 9, 0), 
                        LocalDateTime.of(2023, 4, 20, 10, 20), 100),
                new FilmSession(3, 57, 156, 
                        LocalDateTime.of(2023, 4, 20, 9, 0), 
                        LocalDateTime.of(2023, 4, 20, 10, 20), 5)                
                ); 
        
        when(filmSessionRepository.findAll())
            .thenReturn(filmSessions);
                
        when(filmRepository.findById(55))
            .thenReturn(new Film(55, "тест фильм 55", "тест", "2000", 1, 1, 1, 1));
        when(filmRepository.findById(56))
            .thenReturn(new Film(56, "тест фильм 56", "тест", "2000", 1, 1, 1, 1));
        when(filmRepository.findById(57))
            .thenReturn(new Film(57, "тест фильм 57", "тест", "2000", 1, 1, 1, 1));

        when(hallRepository.findById(any(Integer.class)))
            .thenReturn(new Hall(156, "Холл Тест", 5, 5, "Холл тест"));
        
        var expectedFilmSessionsDto =  List.of(
                new FilmSessionDto(1, "тест фильм 55", "Холл Тест",
                        LocalDateTime.of(2023, 4, 20, 9, 0), 
                        LocalDateTime.of(2023, 4, 20, 10, 20), 320),
                new FilmSessionDto(2, "тест фильм 56", "Холл Тест",
                        LocalDateTime.of(2023, 4, 20, 9, 0), 
                        LocalDateTime.of(2023, 4, 20, 10, 20), 100),
                new FilmSessionDto(3, "тест фильм 57", "Холл Тест",
                        LocalDateTime.of(2023, 4, 20, 9, 0), 
                        LocalDateTime.of(2023, 4, 20, 10, 20), 5)
                );
        
        var actualFilmSessionsDto = filmSessionService.findAll();
        
        assertThat(expectedFilmSessionsDto).usingRecursiveComparison()
            .isEqualTo(actualFilmSessionsDto);
    }
    
}
