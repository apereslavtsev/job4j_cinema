package ru.job4j.cinema.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.service.FileService;
import ru.job4j.cinema.service.FilmSessionService;

@Controller
public class FilmSessionController {
    
    FilmSessionService filmSessionService;
    
    FileService fileService;

    public FilmSessionController(FilmSessionService filmSessionService, FileService fileService) {
        this.filmSessionService = filmSessionService;
        this.fileService = fileService;
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
            model.addAttribute("filmSessionDTO", filmSessionDTO);
            return "shoppingPage";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "errors/404";
        }
    }

}
