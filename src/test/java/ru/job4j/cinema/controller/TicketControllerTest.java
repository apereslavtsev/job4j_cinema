package ru.job4j.cinema.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import ru.job4j.cinema.model.Ticket;
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
    void whenByTicketSuccessfulThenRedirectToSuccefulPurchasePage() {
        Ticket expectedTicket = new Ticket(1, 1, 3, 5, 1);
        when(ticketService.save(any(Ticket.class)))
            .thenReturn(Optional.of(expectedTicket));
        
        String view = ticketController.byTicket(model, expectedTicket);
        assertThat(view).isEqualTo("tickets/successfulPurchase");
        assertThat(model.getAttribute("message"))
            .isEqualTo("Успешно куплен билет на место 5, ряд 3");
    }
    
    @Test
    void whenByTicketUnsuccessfulThenRedirectToUnsuccefulPurchasePage() {
        Ticket ticket = new Ticket(1, 1, 3, 5, 1);
        when(ticketService.save(any(Ticket.class)))
            .thenReturn(Optional.empty());
        
        String view = ticketController.byTicket(model, ticket);
        assertThat(view).isEqualTo("tickets/unsuccessfulPurchase");
    }

}
