package ru.job4j.cinema.model;

import java.util.Map;
import java.util.Objects;

public class Hall {
    
    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "name", "name",
            "row_count", "rowCount",
            "place_count", "placeCount",
            "description", "description"
    );
    
    private int id;
    
    private String name;
    
    private int rowCount;
    
    private int placeCount;
    
    private String description;

    public Hall(int id, String name, int rowCount, int placeCount, String descriprion) {
        super();
        this.id = id;
        this.name = name;
        this.rowCount = rowCount;
        this.placeCount = placeCount;
        this.description = descriprion;
    }
    
    public Hall() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getPlaceCount() {
        return placeCount;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public void setPlaceCount(int placeCount) {
        this.placeCount = placeCount;
    }

    public void setDescription(String descriprion) {
        this.description = descriprion;
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
        Hall other = (Hall) obj;
        return id == other.id;
    }

}
