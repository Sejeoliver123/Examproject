package org.example.examproject.BLL;

import jdk.jshell.spi.ExecutionControlProvider;
import org.example.examproject.BE.Category;
import org.example.examproject.BE.Movie;
import org.example.examproject.DAL.*;

import java.util.List;

public class MainLogic {
    IMovie movieDataAccess;
    ICategory categoryDataAccess;
    ICatMovie catMovieDataAccess;

    public MainLogic(){
        movieDataAccess = new MovieDAO();
        categoryDataAccess = new CategoryDAO();
        catMovieDataAccess = new CatMovieDAO();


    }
    public List<Movie> getallmovies()throws Exception{
        return movieDataAccess.getAllMovies();
    }
    public List<Category> getallcategory() throws Exception{
        return categoryDataAccess.getAllCategory();
    }
    public List<Integer> getallcatmovies() throws Exception{
        return catMovieDataAccess.getAllCatMovie();
    }

    public Movie createMovie(Movie movie) throws Exception{
        return movieDataAccess.createMovie(movie);
    }
    public Category createCategory(Category category) throws Exception{
        return categoryDataAccess.createCategory(category);
    }
    public void createCatmovie(Movie movie,Category category) throws Exception{
        catMovieDataAccess.createCatMovie(movie, category);
    }

    public void updateMovie(Movie movie) throws Exception{
        movieDataAccess.updateMovie(movie);
    }
    public void updateCategory(Category category) throws Exception{
        categoryDataAccess.updateCategory(category);
    }
    public void deleteMovie(Movie movie) throws Exception{
        movieDataAccess.deleteMovie(movie);
    }
    public void deleteCategory(Category category) throws Exception{
        categoryDataAccess.deleteCategory(category);
    }
    public void deleteCatMovie(Movie movie, Category category) throws Exception{
        catMovieDataAccess.deleteCatMovie(movie, category);
    }



}
