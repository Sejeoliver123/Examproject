package org.example.examproject.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.stage.Stage;
import org.example.examproject.BE.Category;
import org.example.examproject.BE.Movie;

public class AddMovieCategoryController {
    private MainModel model;
    @FXML
    private ComboBox<Movie> combMovie;
    @FXML
    private ComboBox<Category> combCategory;

    @FXML
    private void OnAddMovieToCategory(ActionEvent actionEvent) {
        Movie movie = combMovie.getSelectionModel().getSelectedItem();
        Category category = combCategory.getSelectionModel().getSelectedItem();
        if(movie != null && category != null) {
            if (!movie.isInCategory(category)){
                try {
                    model.createCatMovie(movie,category);
                    Button btn = (Button) actionEvent.getSource();
                    Stage window = (Stage) btn.getScene().getWindow();
                    window.close();
                } catch (Exception e) {
                    showError(e);
                }
            }
            else {
                showError(new Throwable("the movie is in the category"));
            }
        }
    }

    public void setModelAndSetOpCombobox(MainModel model) {
        this.model = model;
        combMovie.setItems(model.getMovies());
        combCategory.setItems(model.getCategories());
        combMovie.setCellFactory(listView -> new ListCell<Movie>() {
            @Override
            protected void updateItem(Movie movie, boolean empty) {
                super.updateItem(movie, empty);
                setText(empty || movie == null ? null : movie.getName());
            }
        });
        combMovie.setButtonCell(new ListCell<Movie>() {
            @Override
            protected void updateItem(Movie movie, boolean empty) {
                super.updateItem(movie, empty);
                setText(empty || movie == null ? null : movie.getName());
            }
        });
        combCategory.setCellFactory(listView -> new ListCell<Category>() {
            @Override
            protected void updateItem(Category cat, boolean empty) {
                super.updateItem(cat, empty);
                setText(empty || cat == null ? null : cat.getName());
            }
        });
        combCategory.setButtonCell(new ListCell<Category>() {
            @Override
            protected void updateItem(Category cat, boolean empty) {
                super.updateItem(cat, empty);
                setText(empty || cat == null ? null : cat.getName());
            }
        });
    }
    private void showError(Throwable t)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }
}
