package ru.job4j.cinema.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.Genre;
import ru.job4j.cinema.repository.FilmRepository;
import ru.job4j.cinema.repository.GenreRepository;

@Service
public class SimpleFilmService implements FilmService {
    
    FilmRepository filmRepository;
    
    GenreRepository genreRepository;
    
    private static final Logger LOG = LoggerFactory.getLogger(SimpleFilmService.class.getName());
    
    public SimpleFilmService(FilmRepository filmRepository, 
            GenreRepository genreRepository) {
        
        this.filmRepository = filmRepository;
        this.genreRepository = genreRepository;
    }
    
    private FilmDto filmDtoFromFilm(Film film) {
        Genre genre = genreRepository.findById(film.getGenreId());
        if (genre == null) {
            throwNotFoundByIdExeption("Genre", film.getGenreId());
        }
        return new FilmDto(film, genre);
    }
    
    private void throwNotFoundByIdExeption(String objectName, int id) {
        LOG.error(objectName + " id=" 
                + String.valueOf(id) + " not found!");
        
        throw new IllegalArgumentException(objectName + " id=" 
                + String.valueOf(id) + " not found!");
    }

    @Override
    public FilmDto findById(int id) {
        Film film = filmRepository.findById(id); 
        if (film == null) {
            throwNotFoundByIdExeption("Film", id);
        }
        return filmDtoFromFilm(film);
    }

    @Override
    public Collection<FilmDto> findAll() {
        return filmRepository.findAll().stream()
            .map(film -> filmDtoFromFilm(film))
            .collect(Collectors.toList());        
    }

}
