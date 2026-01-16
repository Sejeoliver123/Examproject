package org.example.examproject.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.examproject.BE.Category;
import org.example.examproject.BE.Movie;
import org.example.examproject.BLL.MainLogic;
import org.example.examproject.DAL.*;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.List;

public class MainModel {
    private MainLogic logic;
    private ObservableList<Movie> movies;
    private ObservableList<Category> categories;

    public MainModel() {
        logic = new MainLogic();
    }

    public ObservableList<Movie> loadMovie() throws Exception {
        movies = FXCollections.observableArrayList(logic.getallmovies());
        return movies;
    }
    public ObservableList<Category> loadCategory() throws Exception {
        categories = FXCollections.observableArrayList(logic.getallcategory());
        return categories;
    }

    public void  loadCatMovie() throws Exception {
        List<Integer> relations = logic.getallcatmovies();
        for (int i = 0; i < relations.size(); i++,i++) {
            int idOne = relations.get(i);
            int idTwo = relations.get(i+1);
            Movie movie = movies.stream().filter(s -> s.getId() == idOne).findFirst().orElse(null);
            Category category = categories.stream().filter(s -> s.getId() == idTwo).findFirst().orElse(null);
            if(movie != null && category != null) {
                movie.addToCategory(category);
                category.addToMovie(movie);
            }
        }
    }

    public ObservableList<Movie> getMovies() {
        return movies;
    }
    public ObservableList<Category> getCategories() {
        return categories;
    }

    public void createCategory(Category category) throws Exception {
        Category createdCategory = logic.createCategory(category);
        categories.add(createdCategory);
    }

    public void createMovie(Movie movie) throws Exception {
        Movie createdMovie = logic.createMovie(movie);
        movies.add(createdMovie);
    }

    public void createCatMovie(Movie movie, Category category) throws Exception {
        logic.createCatmovie(movie, category);
        movie.addToCategory(category);
        category.addToMovie(movie);
    }
    public void updateMovie(Movie movie) throws Exception {
        logic.updateMovie(movie);
        if(movie != null) {
            for (int i = 0; i < movies.size(); i++) {
                if(movies.get(i).getId() == movie.getId()){
                    movies.set(i,movie);
                    break;
                }
            }
        }
    }

    public void deleteCategory(Category category) throws Exception {
        logic.deleteCategory(category);
        categories.remove(category);
    }

    public void deleteMovie(Movie movie) throws Exception {
        logic.deleteMovie(movie);
        movies.remove(movie);
    }

    public void deleteCatMovie(Movie movie, Category category) throws  Exception {
        logic.deleteCatMovie(movie,category);
        movie.removeCategory(category);
        category.removeMovie(movie);
    }
    public void watchMovie(Movie movie) throws Exception{
        File file = new File(movie.getFileLink());
        if(file.exists()) {
            Desktop.getDesktop().open(file);
            movie.setLastView(new Date());
            updateMovie(movie);
        }
    }

}
