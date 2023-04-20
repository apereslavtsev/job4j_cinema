package ru.job4j.cinema.model;

import java.time.LocalDateTime;
import java.util.Map;

public class FilmSession {
    
    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "film_id", "filmId",
            "halls_id", "hallId",
            "start_time", "startTime",
            "end_time", "endTime",
            "price", "price"
    );
    
    private int id;
    
    private int filmId;
    
    private int hallId;
    
    private LocalDateTime startTime;
    
    private LocalDateTime endTime;
    
    private float price;

    public FilmSession(int id, int filmId, int hallId, LocalDateTime startTime, LocalDateTime endTime, float price) {
        super();
        this.id = id;
        this.filmId = filmId;
        this.hallId = hallId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }
    
    public FilmSession() {
        
    }

    public int getId() {
        return id;
    }

    public int getFilmId() {
        return filmId;
    }

    public int getHallId() {
        return hallId;
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

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
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

    @Override
    public String toString() {
        return "FilmSession [id=" + id + ", filmId=" + filmId + ", hallId=" + hallId + ", startTime=" + startTime
                + ", endTime=" + endTime + ", price=" + price + "]";
    }

}
