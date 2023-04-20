package ru.job4j.cinema.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;

import ru.job4j.cinema.model.File;

@Repository
public class Sql2oFileRepositoru implements FileRepository {

    Sql2o sql2o;
    
    
    public Sql2oFileRepositoru(Sql2o sql2o) {
        super();
        this.sql2o = sql2o;
    }

    @Override
    public Optional<File> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM files WHERE id = :id");
            var file = query.addParameter("id", id).executeAndFetchFirst(File.class);
            return Optional.ofNullable(file);
        }
        
    }

}
