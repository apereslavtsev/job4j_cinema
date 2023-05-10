package ru.job4j.cinema.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2o;

import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.FilmSession;

class Sql2oFilmSessionRepositoryTest {

    private static Sql2oFilmSessionRepository sql2oFilmSessionRepository;
    
    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (InputStream inputStream = Sql2oHallRepository.class.getClassLoader()
                .getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        Sql2o sql2o = configuration.databaseClient(datasource);
        sql2oFilmSessionRepository = new Sql2oFilmSessionRepository(sql2o);
    }

    
    @Test
    void whenFindAllThenReturnAll() {
        List<FilmSession> expectedResult = new ArrayList<>();
        expectedResult.add(new FilmSession(1, 2, 3, 
                LocalDateTime.of(2023, 4, 20, 9, 0), LocalDateTime.of(2023, 4, 20, 10, 20), 320));
        expectedResult.add(new FilmSession(2, 2, 3, 
                LocalDateTime.of(2023, 4, 20, 10, 40), LocalDateTime.of(2023, 4, 20, 12, 0), 320));
        expectedResult.add(new FilmSession(3, 3, 1, 
                LocalDateTime.of(2023, 4, 20, 9, 0), LocalDateTime.of(2023, 4, 20, 10, 20), 200));
        expectedResult.add(new FilmSession(4, 3, 1, 
                LocalDateTime.of(2023, 4, 20, 10, 40), LocalDateTime.of(2023, 4, 20, 12, 0), 200));
        expectedResult.add(new FilmSession(5, 4, 2, 
                LocalDateTime.of(2023, 4, 20, 10, 40), LocalDateTime.of(2023, 4, 20, 11, 0), 50));
        expectedResult.add(new FilmSession(6, 1, 1, 
                LocalDateTime.of(2023, 4, 20, 14, 0), LocalDateTime.of(2023, 4, 20, 15, 30), 150));

        var result = sql2oFilmSessionRepository.findAll();
       
        assertThat(result).usingRecursiveComparison()
            .isEqualTo(expectedResult);
    }
    
    @Test
    void whenGetByIDThenReturnSame() {
        var expectedResult = new FilmSession(6, 1, 1, LocalDateTime.of(2023, 4, 20, 14, 0), 
                LocalDateTime.of(2023, 4, 20, 15, 30), 150);

        var result = sql2oFilmSessionRepository.findById(6);
        assertThat(result).usingRecursiveComparison()
            .isEqualTo(expectedResult);
    }
    
    
    @Test
    void whenGetByInvalidIDThenReturnNull() {
        var result = sql2oFilmSessionRepository.findById(101);
        assertThat(result).isNull();
    }

}
