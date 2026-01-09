package org.example.examproject.DAL;

import org.example.examproject.BE.Movie;

import java.util.List;

public interface IMovie {

    List<Movie> getAllMovies() throws Exception;

    Movie createMovie(Movie movie) throws Exception;

    void updateMovie(Movie movie) throws Exception;

    void deleteMovie(Movie movie) throws Exception;
}
