package org.example.examproject.DAL;

import org.example.examproject.BE.Category;
import org.example.examproject.BE.Movie;

import java.util.List;

public interface ICatMovie {

    List<Integer> getAllCatMovie() throws Exception;

    void createCatMovie(Movie movie, Category category) throws Exception;

    void deleteCatMovie(Movie movie, Category category) throws Exception;
}
