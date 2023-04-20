package ru.job4j.cinema.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2o;

import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.model.Ticket;

class Sql2oTicketRepositoryTest {

    private static Sql2oTicketRepository sql2oTicketRepository;
    
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
        sql2oTicketRepository = new Sql2oTicketRepository(sql2o);
    }


    
    @Test
    void whenSave2AndFindAllThenReturnAll() {
        var ticket = new Ticket(1, 1, 2, 2, 1);
        sql2oTicketRepository.save(ticket);
        
        var result = sql2oTicketRepository.findAll();
        //assertThat(result).usingRecursiveComparison()
        //    .isEqualTo(expectedResult);
        
    }
    
    @Test
    void whenGetByIDThenReturnSame() {
        var expectedResult = new Hall(1, "Зал 1 первый этаж", 10, 10, "Зал 1 Тёмный 10*10");

        var result = sql2oTicketRepository.findById(1);
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }
    

}
