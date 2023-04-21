package ru.job4j.cinema.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2o;

import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.File;

class Sql2oFileRepositoryTest {

    private static Sql2oFileRepository sql2oFileRepository;
    
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
        sql2oFileRepository = new Sql2oFileRepository(sql2o);
    }
     
    @Test
    void whenGetByIDThenReturnSame() {
        var expectedResult = new File(1, "Индиана Джонс", "files\\IJandCrystalSkull.jpg");

        var result = sql2oFileRepository.findById(1).get();
        assertThat(result).usingRecursiveComparison()
            .isEqualTo(expectedResult);
    }
    
   @Test
   void whenGetByIDThenReturnEmpty() {
       var result = sql2oFileRepository.findById(65);
       assertThat(result).usingRecursiveComparison()
           .isEqualTo(Optional.empty());
   }
}
