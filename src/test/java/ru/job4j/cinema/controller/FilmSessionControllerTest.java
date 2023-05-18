package ru.job4j.cinema.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.service.FilmSessionService;

class FilmSessionControllerTest {

    private Model model;
    
    private FilmSessionService filmSessionService;
    
    private FilmSessionController filmSessionController;
    
    @BeforeEach
    public void initServices() {
        filmSessionService = mock(FilmSessionService.class);
        filmSessionController = new FilmSessionController(filmSessionService);
        model = new ConcurrentModel();
    }

    @Test
    public void whenRequestFilmSessionListPageThenGetPageWithFilmSessions() {
        FilmSessionDto filmSessionDto1 = new FilmSessionDto(1, "тест фильм", "Холл Тест",
                LocalDateTime.of(2023, 4, 20, 9, 0), 
                LocalDateTime.of(2023, 4, 20, 10, 20), 320, 1, 5, 5, "тест 1");
        
        FilmSessionDto filmSessionDto2 = new FilmSessionDto(1, "тест фильм 2", "Холл Тест 2",
                LocalDateTime.of(2023, 4, 20, 9, 0), 
                LocalDateTime.of(2023, 4, 20, 10, 20), 320, 1, 5, 5, "тест 2");
        
        List<FilmSessionDto> expectedFilmSessions = List.of(filmSessionDto1, filmSessionDto2);
        when(filmSessionService.findAll()).thenReturn(expectedFilmSessions);

        var view = filmSessionController.getAll(model);
        var actualFilms = model.getAttribute("filmSessionsDto");

        assertThat(view).isEqualTo("filmSessions/list");
        assertThat(actualFilms).usingRecursiveComparison()
            .isEqualTo(expectedFilmSessions);
    }
    
    @Test
    public void whenGetByIdThenReturnSameAndRedirectToTicketShoppingPage() {
        FilmSessionDto expectedfilmSessionDto = new FilmSessionDto(1, "тест фильм", "Холл Тест",
                LocalDateTime.of(2023, 4, 20, 9, 0), 
                LocalDateTime.of(2023, 4, 20, 10, 20), 320, 1, 5, 5, "тест 1");
        
        when(filmSessionService.findById(any(Integer.class)))
            .thenReturn(expectedfilmSessionDto);
        
        var view = filmSessionController.getById(model, 1);
        var actualFilmSessionDto = model.getAttribute("filmSessionDTO");
        
        assertThat(view).isEqualTo("tickets/shoppingPage");
        assertThat(actualFilmSessionDto).usingRecursiveComparison()
            .isEqualTo(expectedfilmSessionDto);
    }
    
    @Test
    public void whenGetByInvalidIdThenGetErrorPageWithMessage() {
        when(filmSessionService.findById(any(Integer.class)))
            .thenThrow(new NoSuchElementException("Film session id=2 not found!"));
        
        var view = filmSessionController.getById(model, 2);
        var message = model.getAttribute("message");
        
        assertThat(view).isEqualTo("errors/404");
        assertThat(message).isEqualTo("Film session id=2 not found!");
    }
}
