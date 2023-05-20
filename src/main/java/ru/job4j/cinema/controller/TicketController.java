package ru.job4j.cinema.controller;

import java.util.Optional;

import org.junit.runner.notification.RunListener.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.SimpleFilmService;
import ru.job4j.cinema.service.TicketService;

@ThreadSafe
@Controller
@RequestMapping("/tickets")
public class TicketController {
    
    private static final Logger LOG = LoggerFactory.getLogger(SimpleFilmService.class.getName());
    
    private final TicketService ticketService;
    
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
   
    @PostMapping("/byTicket")
    public String byTicket(Model model, @ModelAttribute Ticket ticket) { 
        try {
            if (ticket.getUserId() == User.getDefaultUser().getId()) {
               return "users/login"; 
            }
            Optional<Ticket> ticketOptional = ticketService.save(ticket);
            if (ticketOptional.isEmpty()) {
               return "tickets/unsuccessfulPurchase";  
            }
            
            model.addAttribute("message", String.format(
                    "Успешно куплен билет на место %d, ряд %d", 
                    ticket.getPlaceNumber(), ticket.getRowNumber()));
            return "tickets/successfulPurchase";
            
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            LOG.error("ticket purchase exeption", e);
            return "errors/404";
        }
    }

}
