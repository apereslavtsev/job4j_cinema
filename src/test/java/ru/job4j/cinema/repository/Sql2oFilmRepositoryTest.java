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
import ru.job4j.cinema.model.Film;

class Sql2oFilmRepositoryTest {

    private static Sql2oFilmRepository sql2oFilmRepository;
    
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
        sql2oFilmRepository = new Sql2oFilmRepository(sql2o);
    }

    
    @Test
    void whenFindAllThenReturnAll() {
        List<Film> expectedResult = new ArrayList<>();
        expectedResult.add(new Film(1, "Индиана Джонс", "Индиана Джонс", "1974", 3, 14, 120, 1));
        expectedResult.add(new Film(2, "Аватар", "Аватар", "2009", 3, 12, 140, 2));
        expectedResult.add(new Film(3, "Пила", "Ужас, пила", "2001", 2, 18, 90, 3));
        expectedResult.add(new Film(4, "Ну погоди", "Ну заяц, погоди", "1964", 4, 2, 20, 4));

        var result = sql2oFilmRepository.findAll();
        assertThat(result).usingRecursiveComparison()
            .isEqualTo(expectedResult);
    }
    
    @Test
    void whenGetByIDThenReturnSame() {
        var expectedResult = new Film(3, "Пила", "Ужас, пила", "2001", 2, 18, 90, 3);

        var result = sql2oFilmRepository.findById(3);
        assertThat(result).usingRecursiveComparison()
            .isEqualTo(expectedResult);
    }
    
    @Test
    void whenGetByInvalidIDThenReturnNull() {
        var result = sql2oFilmRepository.findById(101);
        assertThat(result).isNull();
    }

}
