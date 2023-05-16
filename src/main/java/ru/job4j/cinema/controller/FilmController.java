package ru.job4j.cinema.controller;


import org.junit.runner.notification.RunListener.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.job4j.cinema.service.FilmService;

@ThreadSafe
@Controller
@RequestMapping("/films")
public class FilmController {
    
    FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }
    
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("filmsDto", filmService.findAll());
        return "films/list";
    }

}
