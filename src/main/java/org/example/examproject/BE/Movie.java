package org.example.examproject.BE;


import java.util.ArrayList;
import java.util.Date;

public class Movie {
    private int id;
    private String name;
    private float IMDBRating;
    private String fileLink;
    private float personalRating;
    private Date lastView;
    private ArrayList<Category> categories;

    public Movie(String name, float IMDBRating, String fileLink) {
        this.id = 0;
        this.name = name;
        this.IMDBRating = IMDBRating;
        this.fileLink = fileLink;
        this.lastView = null;
        this.categories = new ArrayList<>();
    }
    public Movie(int id, String name, float IMDBRating, String fileLink) {
        this.id = id;
        this.name = name;
        this.IMDBRating = IMDBRating;
        this.fileLink = fileLink;
        this.lastView = null;
        this.categories = new ArrayList<>();
    }
}
