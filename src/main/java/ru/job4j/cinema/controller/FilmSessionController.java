package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.service.FilmSessionService;

@Controller
@RequestMapping("/filmSessions")
public class FilmSessionController {

    private final FilmSessionService filmSessionService;
    
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
            FilmSessionDto filmSessionDTO = filmSessionService.findById(id);
            model.addAttribute("filmSessionDTO", filmSessionDTO);
            return "tickets/shoppingPage";
    }
}
