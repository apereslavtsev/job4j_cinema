package ru.job4j.cinema.service;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.FilmRepository;
import ru.job4j.cinema.repository.FilmSessionRepository;
import ru.job4j.cinema.repository.HallRepository;

@Service
public class SimpleFilmSessionService implements FilmSessionService {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleFilmService.class.getName());
    
    private final FilmSessionRepository filmSessionRepository;
    
    private final FilmRepository filmRepository;
    
    private final HallRepository hallRepository;
    
    public SimpleFilmSessionService(FilmSessionRepository filmSessionRepository, 
            FilmRepository filmRepository, HallRepository hallRepository) {
        this.filmSessionRepository = filmSessionRepository;
        this.filmRepository = filmRepository;
        this.hallRepository = hallRepository;
    }
    
    private FilmSessionDto filmSessionDtoFromFilmSession(FilmSession filmSession) {
        Film film = filmRepository.findById(filmSession.getFilmId());
        Hall hall = hallRepository.findById(filmSession.getHallId());
        if (film == null) {
            throwNotFoundByIdExeption("Film", filmSession.getFilmId());
        }
        if (hall == null) {
            throwNotFoundByIdExeption("Hall", filmSession.getHallId());            
        }
        return new FilmSessionDto(filmSession, film, hall);
    }

    private void throwNotFoundByIdExeption(String objectName, int id) {
        LOG.error(String.format(
                "%s id=%d not found!", objectName, id));
        throw new NoSuchElementException(String.format(
                "%s id=%d not found!", objectName, id));
    }

    @Override
    public FilmSessionDto findById(int id) {
        FilmSession filmSession = filmSessionRepository.findById(id);
        
        if (filmSession == null) {
            throwNotFoundByIdExeption("Film session", id);
        }
        
        return filmSessionDtoFromFilmSession(filmSession);
    }

    @Override
    public Collection<FilmSessionDto> findAll() {
        return filmSessionRepository.findAll().stream()
                .map(filmSession -> 
                    filmSessionDtoFromFilmSession(filmSession))
                .collect(Collectors.toList());
    }

}
