package ru.job4j.cinema.controller;


import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.FilmSessionService;
import ru.job4j.cinema.service.SimpleFilmService;
import ru.job4j.cinema.service.TicketService;

@Controller
@RequestMapping("/filmSessions")
public class FilmSessionController {
    
    FilmSessionService filmSessionService;
    
    TicketService ticketService;
    
    private static final Logger LOG = LoggerFactory.getLogger(SimpleFilmService.class.getName());
    
    public FilmSessionController(FilmSessionService filmSessionService, TicketService ticketService) {
        this.filmSessionService = filmSessionService;
        this.ticketService = ticketService;
    }
    
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("filmSessionsDto", filmSessionService.findAll());
        return "filmSessions";
    }
    
    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {        
        try {
            FilmSessionDto filmSessionDTO = filmSessionService.findById(id);
            model.addAttribute("filmSessionDTO", filmSessionDTO);
            return "ticket/shoppingPage";
            
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            LOG.error("Exception while getting filmSessionDTO", e);
            return "errors/404";    
        }
        
    }
    
    @PostMapping("/byTicket")
    public String update(Model model, HttpSession session) {
        
        try {
            var user = (User) session.getAttribute("user");
            
            Ticket savedTicket = new Ticket(0, 
                    (int) model.getAttribute("id"),
                    (int) model.getAttribute("rowNumber"), 
                    (int) model.getAttribute("placeNumber"), 
                    user.getId()
                    );
            
            Optional<Ticket> ticketOptional = ticketService.save(savedTicket);
            
            if (ticketOptional.isEmpty()) {
               return "ticket/unsuccessfulPurchase";  
            }

            return "ticket/successfulPurchase";
            
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            LOG.error("ticket purchase exeption", e);
            return "errors/404";
        }
    }

}
