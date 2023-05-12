package ru.job4j.cinema.dto;

import java.time.LocalDateTime;

import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Hall;

public class FilmSessionDto {

    private int id;
    
    private String film;
    
    private String filmDescription;
    
    private String hall; 
    
    private LocalDateTime startTime;
    
    private LocalDateTime endTime;
    
    private float price;
    
    private int fileId;
        
    private int placeCount;
    
    private int rowCount;
    
    public FilmSessionDto() {
        
    }
    
    public FilmSessionDto(int id, String film, String hall, LocalDateTime startTime,
            LocalDateTime endTime, float price, int fileId) {
        this.id = id;
        this.film = film;
        this.hall = hall;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.fileId = fileId;
    }
    
    public FilmSessionDto(FilmSession filmSession) {
        this.id = filmSession.getId();
        this.startTime = filmSession.getStartTime();
        this.endTime = filmSession.getEndTime();
        this.price = filmSession.getPrice();        
    }

    public FilmSessionDto(FilmSession filmSession, Film film, Hall hall) {
      this(filmSession);
      this.film = film.getName();
      this.filmDescription = film.getDescription();
      this.fileId = film.getFileId();
      this.hall = hall.getName();
      this.placeCount = hall.getPlaceCount();
      this.rowCount = hall.getRowCount();      
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
    
    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getPlaceCount() {
        return placeCount;
    }

    public void setPlaceCount(int placeCount) {
        this.placeCount = placeCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
    
    public String getFilmDescription() {
        return filmDescription;
    }

    public void setFilmDescription(String filmDescription) {
        this.filmDescription = filmDescription;
    }
    
}
