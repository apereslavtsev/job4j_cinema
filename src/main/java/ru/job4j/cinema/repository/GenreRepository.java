package ru.job4j.cinema.repository;

import java.util.Collection;

import ru.job4j.cinema.model.Genre;

public interface GenreRepository {
    
    Genre findById(int id);
    
    Collection<Genre> findAll();

}