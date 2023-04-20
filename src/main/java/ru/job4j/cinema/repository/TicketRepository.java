package ru.job4j.cinema.repository;

import java.util.Collection;
import java.util.Optional;

import ru.job4j.cinema.model.Ticket;

public interface TicketRepository {
    Ticket save(Ticket ticket);

    boolean deleteById(int id);

    Optional<Ticket> findById(int id);

    Collection<Ticket> findAll();
}
