package org.example.examproject.BE;

import java.util.ArrayList;

public class Category {
    private int id;
    private String name;
    private ArrayList<Movie> movies;

    public Category(String name) {
        this.id = 0;
        this.name = name;
        this.movies = new ArrayList<>();
    }
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
        this.movies = new ArrayList<>();
    }

    public String getName() { return name; }

    public int getId() { return id; }

    public void addToMovie(Movie movie) {
        movies.add(movie);
    }
}
