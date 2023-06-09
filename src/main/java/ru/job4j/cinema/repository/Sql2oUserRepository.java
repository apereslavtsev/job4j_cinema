package ru.job4j.cinema.repository;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;

import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.SimpleFilmService;

@Repository
public class Sql2oUserRepository implements UserRepository {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleFilmService.class.getName());
    private final Sql2o sql2o;
    
    public Sql2oUserRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }
    
    @Override
    public Optional<User> save(User user) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("INSERT INTO users (email, full_name, password) "
                    + "VALUES (:email, :full_name, :password)", true)
                    .addParameter("email", user.getEmail())
                    .addParameter("full_name", user.getFullName())
                    .addParameter("password", user.getPassword());
            int generatedId = query.setColumnMappings(User.COLUMN_MAPPING)
                    .executeUpdate().getKey(Integer.class);
            user.setId(generatedId);
            return Optional.of(user);
        } catch (Exception e) {
            LOG.error("Save user exception", e);
        }
        return  Optional.empty();
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM users WHERE "
                    + "email = :email and password = :password");
            User usr = query
                    .addParameter("email", email)
                    .addParameter("password", password)
                    .setColumnMappings(User.COLUMN_MAPPING)
                    .executeAndFetchFirst(User.class);
            return Optional.ofNullable(usr);
        }
    }

}
