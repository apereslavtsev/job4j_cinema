package ru.job4j.cinema.dto;

import java.time.LocalDateTime;

import ru.job4j.cinema.model.FilmSession;

public class FilmSessionDto {
    
    private int id;
    
    private String film;
    
    private String hall; 
    
    private LocalDateTime startTime;
    
    private LocalDateTime endTime;
    
    private float price;

    
    public FilmSessionDto() {
        
    }
    
    public FilmSessionDto(int id, String film, String filmDescription, String hall, LocalDateTime startTime,
            LocalDateTime endTime, float price) {
        super();
        this.id = id;
        this.film = film;
        this.hall = hall;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }
    
    public FilmSessionDto(FilmSession filmSession) {
        setId(filmSession.getId());
        setStartTime(filmSession.getStartTime());
        setEndTime(filmSession.getEndTime());
        setPrice(filmSession.getPrice());        
    }

    public FilmSessionDto(FilmSession filmSession, String film, String hall) {
      this(filmSession);
      this.film = film;
      this.hall = hall;
    }
    
    public int getId() {
        return id;
    }

    public String getFilm() {
        return film;
    }

    public String getHall() {
        return hall;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public float getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    
    
}
