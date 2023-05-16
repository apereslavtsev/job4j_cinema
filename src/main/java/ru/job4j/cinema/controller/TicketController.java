package ru.job4j.cinema.controller;


import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.junit.runner.notification.RunListener.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.SimpleFilmService;
import ru.job4j.cinema.service.TicketService;

@ThreadSafe
@Controller
@RequestMapping("/tickets")
public class TicketController {
    
    TicketService ticketService;
    
    private static final Logger LOG = LoggerFactory.getLogger(SimpleFilmService.class.getName());
    
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
   
    @PostMapping("/byTicket")
    public String saveTicket(Model model, HttpSession session, @RequestParam int id,
            @RequestParam int rowNumber, @RequestParam int placeNumber) { 
        
        try {
            var user = (User) session.getAttribute("user");  
            if (user == null) {
               return "users/login"; 
            }
            
            Ticket savedTicket = new Ticket(0, id, rowNumber, placeNumber, user.getId());
            Optional<Ticket> ticketOptional = ticketService.save(savedTicket);
            
            if (ticketOptional.isEmpty()) {
               return "tickets/unsuccessfulPurchase";  
            }
            
            model.addAttribute("message", String.format(
                    "Успешно куплен билет на место %d, ряд %d", placeNumber, rowNumber));
            return "tickets/successfulPurchase";
            
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            LOG.error("ticket purchase exeption", e);
            return "errors/404";
        }
    }

}
