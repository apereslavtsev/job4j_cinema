package ru.job4j.cinema.model;

import java.util.Map;
import java.util.Objects;

public class Film {
    
    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "name", "name",
            "description", "description",
            "year", "year",
            "genre_id", "genreId",
            "minimal_age", "minimalAge",
            "durationIn_minutes", "durationInMinutes",
            "file_id", "fileId"
    );
    
    private int id;
    
    private String name;
    
    private String description;
    
    private String year;
    
    private int genreId;
    
    private int minimalAge;
    
    private int durationInMinutes;
    
    private int fileId;
    
    public Film() {
    } 

    public Film(int id, String name, String description, String year, int genreId, int minimalAge,
            int durationInMinutes, int fileId) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.year = year;
        this.genreId = genreId;
        this.minimalAge = minimalAge;
        this.durationInMinutes = durationInMinutes;
        this.fileId = fileId;
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

    public int getGenreId() {
        return genreId;
    }

    public int getMinimalAge() {
        return minimalAge;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public int getFileId() {
        return fileId;
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

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public void setMinimalAge(int minimalAge) {
        this.minimalAge = minimalAge;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }
    

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }
        Film other = (Film) obj;
        return id == other.id;
    }
}
