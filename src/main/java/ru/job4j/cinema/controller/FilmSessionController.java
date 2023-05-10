package ru.job4j.cinema.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.service.FilmSessionService;

@Controller
public class FilmSessionController {
    
    FilmSessionService filmSessionService;

    public FilmSessionController(FilmSessionService filmSessionService) {
        this.filmSessionService = filmSessionService;
    }
    
    @GetMapping("/filmSessions")
    public String getAll(Model model) {
        model.addAttribute("filmSessionsDto", filmSessionService.findAll());
        return "filmSessions";
    }
    
    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {        
        FilmSessionDto filmSessionDTO;        
        try {
            filmSessionDTO = filmSessionService.findById(id);
        } catch (Exception e) {
            model.addAttribute("message", "Кандидат с указанным идентификатором не найден");
            return "errors/404";
        }
        model.addAttribute("filmSessionDTO", filmSessionDTO);
        return "shoppingPage";
    }

}
