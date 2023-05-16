package ru.job4j.cinema.repository;

import static java.util.Collections.emptyList;
import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import ru.job4j.cinema.configuration.DatasourceConfiguration;
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

    @AfterEach
    public void clearVacancies() {
        var tickets = sql2oTicketRepository.findAll();
        for (var ticket : tickets) {
            sql2oTicketRepository.deleteById(ticket.getId());
        }
    }
    
    @Test
    void whenSave2AndThengetAll2() {
        var ticket1 = sql2oTicketRepository.save(new Ticket(1, 1, 2, 2, 1)).get();
        var ticket2 = sql2oTicketRepository.save(new Ticket(2, 1, 2, 1, 1)).get();
        
        var result = sql2oTicketRepository.findAll();
        assertThat(result).usingRecursiveComparison()
            .isEqualTo(List.of(ticket1, ticket2));
    }
    
    @Test
    public void whenSaveThenGetSame() {
        var ticket1 = sql2oTicketRepository.save(new Ticket(1, 1, 2, 2, 1)).get();
        
        var savedCandidate = sql2oTicketRepository.findById(ticket1.getId()).get();
        assertThat(savedCandidate).usingRecursiveComparison().isEqualTo(savedCandidate);
    }

    @Test
    public void whenDontSaveThenNothingFound() {
        assertThat(sql2oTicketRepository.findAll()).isEqualTo(emptyList());
        assertThat(sql2oTicketRepository.findById(0)).isEqualTo(empty());
    }

    @Test
    public void whenDeleteThenGetEmptyOptional() {
        var ticket = sql2oTicketRepository.save(new Ticket(1, 1, 2, 2, 1)).get();
        
        var isDeleted = sql2oTicketRepository.deleteById(ticket.getId());
        var savedVacancy = sql2oTicketRepository.findById(ticket.getId());
        assertThat(isDeleted).isTrue();
        assertThat(savedVacancy).isEqualTo(empty());
    }

    @Test
    public void whenDeleteByInvalidIdThenGetFalse() {
        assertThat(sql2oTicketRepository.deleteById(0)).isFalse();
    }
    
    @Test
    public void whenSaveDuplicateTicketThenEmptyOptional() {
        sql2oTicketRepository.save(new Ticket(1, 1, 2, 2, 1));
        
        assertThat(sql2oTicketRepository.save(new Ticket(1, 1, 2, 2, 1)))
            .isEqualTo(empty());
    } 

}
