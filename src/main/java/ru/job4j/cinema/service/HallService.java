package ru.job4j.cinema.service;

import java.util.Collection;

import ru.job4j.cinema.model.Hall;

public interface HallService {
    
    Hall findById(int id);
    
    Collection<Hall> findAll();

}