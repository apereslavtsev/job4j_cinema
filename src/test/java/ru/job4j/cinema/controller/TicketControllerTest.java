package ru.job4j.cinema.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.TicketService;

class TicketControllerTest {
        
    private Model model;
    
    private TicketService ticketService;
    
    private TicketController ticketController;
    
    @BeforeEach
    public void initServices() {
        ticketService = mock(TicketService.class);
        ticketController = new TicketController(ticketService);
        model = new ConcurrentModel();
    }

    @Test
    void whenLogoutUserByTicketThenRedirectToLoginPage() {
        String view = ticketController.byTicket(model, 
                new Ticket(0, 0, 0, 0, User.getDefaultUser().getId()));
        assertThat(view).isEqualTo("users/login");        
    }
    
    @Test
    void whenByTicketSuccessfulThenRedirectToSuccefulPage() {
        
    }

}
