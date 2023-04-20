package ru.job4j.cinema.repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Film;

@Repository
public class Sql2oFilmRepository implements FilmRepository {

    Sql2o sql2o;
    
    public Sql2oFilmRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }
    
    @Override
    public Film findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM films WHERE id = :id");
            query.addParameter("id", id);
            return query.setColumnMappings(Film.COLUMN_MAPPING)
                    .executeAndFetchFirst(Film.class);
        }
    }

    @Override
    public Collection<Film> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM films order by id");
            return query.setColumnMappings(Film.COLUMN_MAPPING)
                    .executeAndFetch(Film.class);
        }
    }

}
