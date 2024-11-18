package com.example.javacourse.requests;
import java.util.List;
import java.util.ArrayList;

public class FindRequest {
    private String query;
    private List<String> experienceFilters;

    // Геттеры и сеттеры
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<String> getExperienceFilters() {
        return experienceFilters;
    }

    public void setExperienceFilters(List<String> experienceFilters) {
        this.experienceFilters = experienceFilters;
    }
}
