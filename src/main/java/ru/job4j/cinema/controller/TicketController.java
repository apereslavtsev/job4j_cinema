package ru.job4j.cinema.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.service.TicketService;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    
    private final TicketService ticketService;
    
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
   
    @PostMapping("/byTicket")
    public String byTicket(Model model, @ModelAttribute Ticket ticket) { 
            Optional<Ticket> ticketOptional = ticketService.save(ticket);
            if (ticketOptional.isEmpty()) {
               return "tickets/unsuccessfulPurchase";  
            }
            
            model.addAttribute("message", String.format(
                    "Успешно куплен билет на место %d, ряд %d", 
                    ticket.getPlaceNumber(), ticket.getRowNumber()));
            return "tickets/successfulPurchase";
    }

}
