package ru.job4j.cinema.service;

import java.util.Collection;

import ru.job4j.cinema.dto.FilmDto;

public interface FilmService {
    
    FilmDto findById(int id);
    
    Collection<FilmDto> findAll();

}