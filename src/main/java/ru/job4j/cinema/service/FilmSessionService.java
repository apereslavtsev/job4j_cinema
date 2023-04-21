package ru.job4j.cinema.service;

import java.util.Collection;

import ru.job4j.cinema.dto.FilmSessionDto;

public interface FilmSessionService {
    
    FilmSessionDto findById(int id);
    
    Collection<FilmSessionDto> findAll();

}
