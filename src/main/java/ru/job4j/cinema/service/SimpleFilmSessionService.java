package ru.job4j.cinema.service;

import java.util.Collection;
import java.util.stream.Collectors;

import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.repository.FilmRepository;
import ru.job4j.cinema.repository.FilmSessionRepository;
import ru.job4j.cinema.repository.HallRepository;

public class SimpleFilmSessionService implements FilmSessionService {

    FilmSessionRepository filmSessionRepository;
    
    FilmRepository filmRepository;
    
    HallRepository hallRepository;
    
    public SimpleFilmSessionService(FilmSessionRepository filmSessionRepository, 
            FilmRepository filmRepository, HallRepository hallRepository) {
        this.filmSessionRepository = filmSessionRepository;
        this.filmRepository = filmRepository;
        this.hallRepository = hallRepository;
    }

    @Override
    public FilmSessionDto findById(int id) {
        FilmSession filmSession = filmSessionRepository.findById(id);
        
        return new FilmSessionDto(
                filmSession,
                filmRepository.findById(filmSession.getFilmId()).getName(), 
                hallRepository.findById(filmSession.getHallId()).getName()
                );
    }

    @Override
    public Collection<FilmSessionDto> findAll() {
        return filmSessionRepository.findAll().stream()
                .map(filmSession -> new FilmSessionDto(
                        filmSession, 
                        filmRepository.findById(filmSession.getFilmId()).getName(), 
                        hallRepository.findById(filmSession.getHallId()).getName()
                        ))
                .collect(Collectors.toList());
    }

}
