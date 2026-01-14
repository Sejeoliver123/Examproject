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
    public Movie(String name, float IMDBRating, String fileLink, float personalRating) {
        this.id = 0;
        this.name = name;
        this.IMDBRating = IMDBRating;
        this.fileLink = fileLink;
        this.personalRating = personalRating;
        this.lastView = null;
        this.categories = new ArrayList<>();
    }
    public Movie(String name, float IMDBRating, String fileLink, Date lastView) {
        this.id = 0;
        this.name = name;
        this.IMDBRating = IMDBRating;
        this.fileLink = fileLink;
        this.lastView = lastView;
        this.categories = new ArrayList<>();
    }public Movie(String name, float IMDBRating, String fileLink, float personalRating, Date lastView) {
        this.id = 0;
        this.name = name;
        this.IMDBRating = IMDBRating;
        this.fileLink = fileLink;
        this.personalRating = personalRating;
        this.lastView = lastView;
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
    public Movie(int id, String name, float IMDBRating, String fileLink, float personalRating) {
        this.id = id;
        this.name = name;
        this.IMDBRating = IMDBRating;
        this.fileLink = fileLink;
        this.personalRating = personalRating;
        this.lastView = null;
        this.categories = new ArrayList<>();
    }
    public Movie(int id, String name, float IMDBRating, String fileLink, Date lastView) {
        this.id = id;
        this.name = name;
        this.IMDBRating = IMDBRating;
        this.fileLink = fileLink;
        this.lastView = lastView;
        this.categories = new ArrayList<>();
    }
    public Movie(int id, String name, float IMDBRating, String fileLink, float personalRating, Date lastView) {
        this.id = id;
        this.name = name;
        this.IMDBRating = IMDBRating;
        this.fileLink = fileLink;
        this.personalRating = personalRating;
        this.lastView = lastView;
        this.categories = new ArrayList<>();
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public float getIMDBRating() { return IMDBRating; }

    public String getFileLink() { return  fileLink; }

    public float getPersonalRating() { return personalRating; }
    public void setPersonalRating(float personalRating) { this.personalRating = personalRating; }

    public Date getLastView() { return lastView; }
    public void  setLastView(Date lastView) { this.lastView = lastView; }

    public void addToCategory(Category category) {
        categories.add(category);
    }
}
