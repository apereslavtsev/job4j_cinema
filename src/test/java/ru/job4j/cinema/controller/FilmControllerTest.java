package ru.job4j.cinema.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.service.FilmService;

class FilmControllerTest {
    
    private Model model;
    
    private FilmService filmService;
    
    private FilmController filmController;
    
    @BeforeEach
    public void initServices() {
        filmService = mock(FilmService.class);
        filmController = new FilmController(filmService);
        model = new ConcurrentModel();
    }

    @Test
    public void whenRequestFilmListPageThenGetPageWithFilms() {
        FilmDto film1 = new FilmDto(1, "fllm1", "film1 descr", "2000", 12, 20, "horror", 1);
        FilmDto film2 = new FilmDto(2, "fllm2", "film2 descr", "2002", 12, 40, "comedy", 1);
        FilmDto film3 = new FilmDto(3, "fllm3", "film3 descr", "2003", 12, 90, "travel", 1);
        
        var expectedFilms = List.of(film1, film2, film3);
        when(filmService.findAll()).thenReturn(expectedFilms);

        var view = filmController.getAll(model);
        var actualFilms = model.getAttribute("filmsDto");

        assertThat(view).isEqualTo("films/list");
        assertThat(actualFilms).usingRecursiveComparison()
            .isEqualTo(expectedFilms);
    }

}
