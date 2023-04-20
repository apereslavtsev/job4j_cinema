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
import ru.job4j.cinema.model.Hall;


class Sql2oHallRepositoryTest {
    
    private static Sql2oHallRepository sql2oHallRepository;
    
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
        sql2oHallRepository = new Sql2oHallRepository(sql2o);
    }

    
    @Test
    void whenFindAllThenReturnAll() {
        List<Hall> expectedResult = new ArrayList<>();
        expectedResult.add(new Hall(1, "Зал 1 первый этаж", 10, 10, "Зал 1 Тёмный 10*10"));
        expectedResult.add(new Hall(2, "Зал 2 первый этаж с кондиционером",
                5, 8, "Зал 2 малый с кондиционером"));
        expectedResult.add(new Hall(3, "Зал 3 3D", 7, 6, "Зал 3 3D"));

        var result = sql2oHallRepository.findAll();
        assertThat(result).usingRecursiveComparison()
            .isEqualTo(expectedResult);
        
    }
    
    @Test
    void whenGetByIDThenReturnSame() {
        var expectedResult = new Hall(1, "Зал 1 первый этаж", 10, 10, "Зал 1 Тёмный 10*10");

        var result = sql2oHallRepository.findById(1);
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }
    
}
