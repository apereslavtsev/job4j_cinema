package ru.job4j.cinema.controller;

import org.junit.runner.notification.RunListener.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.service.FilmSessionService;
import ru.job4j.cinema.service.SimpleFilmService;

@ThreadSafe
@Controller
@RequestMapping("/filmSessions")
public class FilmSessionController {
    
    FilmSessionService filmSessionService;
    
    private static final Logger LOG = LoggerFactory.getLogger(SimpleFilmService.class.getName());
    
    public FilmSessionController(FilmSessionService filmSessionService) {
        this.filmSessionService = filmSessionService;
    }
    
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("filmSessionsDto", filmSessionService.findAll());
        return "filmSessions/list";
    }
    
    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {        
        try {
            FilmSessionDto filmSessionDTO = filmSessionService.findById(id);
            model.addAttribute("filmSessionDTO", filmSessionDTO);
            return "tickets/shoppingPage";
            
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            LOG.error("Exception while getting filmSessionDTO", e);
            return "errors/404";    
        }
        
    }

}
