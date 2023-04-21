package ru.job4j.cinema.service;

import java.util.Collection;
import java.util.stream.Collectors;

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
    
    public SimpleFilmService(FilmRepository filmRepository, 
            GenreRepository genreRepository) {
        
        this.filmRepository = filmRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public FilmDto findById(int id) {
        Film film = filmRepository.findById(id);        
        return filmDtoFromFilm(film);
    }
    
    private FilmDto filmDtoFromFilm(Film film) {
        if (film == null) {
            throw new IllegalArgumentException("Film not found!");
        }
        Genre genre = genreRepository.findById(film.getGenreId());
        if (genre == null) {
            throw new IllegalArgumentException("Genre id="
                    + String.valueOf(film.getGenreId()) + " not found!");
        }
        return new FilmDto(film, genre.getName());
    }

    @Override
    public Collection<FilmDto> findAll() {
        return filmRepository.findAll().stream()
        .map(film -> filmDtoFromFilm(film))
        .collect(Collectors.toList());        
    }

}
