package ru.job4j.cinema.repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;

import ru.job4j.cinema.model.FilmSession;

@Repository
public class Sql2oFilmSessionRepository implements FilmSessionRepository {

    Sql2o sql2o;
    
    public Sql2oFilmSessionRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }
    
    @Override
    public FilmSession findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM film_sessions WHERE id = :id");
            query.addParameter("id", id);
            return query.setColumnMappings(FilmSession.COLUMN_MAPPING)
                    .executeAndFetchFirst(FilmSession.class);
        }
    }

    @Override
    public Collection<FilmSession> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM film_sessions order by id");
            return query.setColumnMappings(FilmSession.COLUMN_MAPPING)
                    .executeAndFetch(FilmSession.class);
        }
    }

}