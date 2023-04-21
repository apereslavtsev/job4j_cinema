package ru.job4j.cinema.service;

import java.util.Collection;

import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.HallRepository;

public class SimpleHallService implements HallService {
    
    HallRepository hallRepository;
    
    public SimpleHallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @Override
    public Hall findById(int id) {
        return hallRepository.findById(id);
    }

    @Override
    public Collection<Hall> findAll() {
        return hallRepository.findAll();
    }
    
}