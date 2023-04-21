package ru.job4j.cinema.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import ru.job4j.cinema.model.Genre;
import ru.job4j.cinema.repository.GenreRepository;

@Service
public class SimpleGenreService implements GenreService {
    
    GenreRepository genreRepository;
    
    public SimpleGenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre findById(int id) {
        return genreRepository.findById(id);
    }

    @Override
    public Collection<Genre> findAll() {
        return genreRepository.findAll();
    }

}
