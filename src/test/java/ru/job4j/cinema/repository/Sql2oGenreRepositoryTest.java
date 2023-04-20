package ru.job4j.cinema.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2o;

import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.Genre;

class Sql2oGenreRepositoryTest {
    
    private static Sql2oGenreRepository sql2oGenreRepository;
    
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
        sql2oGenreRepository = new Sql2oGenreRepository(sql2o);
    }

    
    @Test
    void whenFindAllThenReturnAll() {
        List<Genre> expectedResult = new ArrayList<>();
        expectedResult.add(new Genre(1, "Комедия"));
        expectedResult.add(new Genre(2, "Ужасы"));
        expectedResult.add(new Genre(3, "Приключения"));
        expectedResult.add(new Genre(4, "Мультфильм"));

        var result = sql2oGenreRepository.findAll();
        assertThat(result).usingRecursiveComparison()
            .isEqualTo(expectedResult);
    }
    
    @Test
    void whenGetByIDThenReturnSame() {
        var expectedResult = new Genre(2, "Ужасы");

        var result = sql2oGenreRepository.findById(2);
        assertThat(result).usingRecursiveComparison()
            .isEqualTo(expectedResult);
    }
}
