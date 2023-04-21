package ru.job4j.cinema.service;

import java.util.Collection;

import ru.job4j.cinema.model.Genre;

public interface GenreService {
    
    Genre findById(int id);
    
    Collection<Genre> findAll();

}