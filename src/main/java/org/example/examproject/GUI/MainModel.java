package org.example.examproject.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.examproject.BE.Category;
import org.example.examproject.BE.Movie;
import org.example.examproject.BLL.MainLogic;
import org.example.examproject.DAL.*;

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

    public void createCategory(Category category) throws Exception {
        Category createdCategory = logic.createCategory(category);
        categories.add(createdCategory);
    }

}
