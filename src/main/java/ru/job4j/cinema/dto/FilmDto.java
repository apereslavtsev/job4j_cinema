package ru.job4j.cinema.dto;

import ru.job4j.cinema.model.Film;

public class FilmDto {
    
    private int id;
    
    private String name;
    
    private String description;
    
    private String year;
    
    private int minimalAge;
    
    private int durationInMinutes;
    
    private String genre;
    
    private int fileTd;
    
    public FilmDto() {
        
    }
    
    public FilmDto(int id, String name, String description, String year, 
            int minimalAge, int durationInMinutes, String genre, int fileId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.year = year;
        this.minimalAge = minimalAge;
        this.durationInMinutes = durationInMinutes;
        this.genre = genre;
        this.fileTd = fileId;
    }

    public FilmDto(Film film) {
        setId(film.getId());
        setName(film.getName());
        setDescription(film.getDescription());
        setYear(film.getYear());
        setMinimalAge(film.getMinimalAge());
        setDurationInMinutes(film.getDurationInMinutes());
        setFileTd(film.getFileId());
    }
    
    public FilmDto(Film film, String genre) {
        this(film);
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getYear() {
        return year;
    }

    public int getMinimalAge() {
        return minimalAge;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public String getGenre() {
        return genre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setMinimalAge(int minimalAge) {
        this.minimalAge = minimalAge;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public int getFileTd() {
        return fileTd;
    }

    public void setFileTd(int fileTd) {
        this.fileTd = fileTd;
    }

    
}
