package org.example.examproject.GUI;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.examproject.BE.Category;
import org.example.examproject.BE.Movie;

public class MainController {

    private MainModel model;
    @FXML
    private TableView<Category> tbvCategory;
    @FXML
    private TableColumn tbvColCategoryName;
    @FXML
    private TableView<Movie> tbvMovie;
    @FXML
    private TableColumn tbvCollumTitle;
    @FXML
    private TableColumn tbvCollumIMDBscore;
    @FXML
    private TableColumn tbvCollumUserScore;

    public MainController() {
        model = new MainModel();
    }

    public void initialize() {
        loadMovie();
        loadCategory();
        try {
            model.loadCatMovie();
        } catch (Exception e) {
            showError(e);
        }

    }

    private void loadMovie() {
        tbvCollumTitle.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbvCollumUserScore.setCellValueFactory(new PropertyValueFactory<>("personalRating"));
        tbvCollumIMDBscore.setCellValueFactory(new PropertyValueFactory<>("IMDBRating"));
        try {
            FilteredList<Movie> filteredList = new FilteredList<>(model.loadMovie());
            SortedList<Movie> sortedMovie = new SortedList<>(filteredList);
            sortedMovie.comparatorProperty().bind(tbvMovie.comparatorProperty());
            tbvMovie.setItems(sortedMovie);
        } catch (Exception e) {
            showError(e);
        }
    }

    private void loadCategory() {
        tbvColCategoryName.setCellValueFactory(new PropertyValueFactory<>("name"));
        try {
            FilteredList<Category> filteredList = new FilteredList<>(model.loadCategory());
            SortedList<Category> sortedCategory = new SortedList<>(filteredList);
            sortedCategory.comparatorProperty().bind(tbvCategory.comparatorProperty());
            tbvCategory.setItems(sortedCategory);
        } catch (Exception e) {
            showError(e);
        }
    }

    @FXML
    private void onMovieAdd(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/examproject/MovieCreation-view.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            MovieCreationController movieCreationController = fxmlLoader.getController();
            movieCreationController.setModel(model);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            showError(e);
        }

    }

    @FXML
    private void onCategoryAdd(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/examproject/CategoryCreation-view.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            CategoryCreationController categoryCreationController = fxmlLoader.getController();
            categoryCreationController.setModel(model);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            showError(e);
        }

    }


    private void showError(Throwable t)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }

    @FXML
    private void onAddMovieToCategory(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/examproject/addMovieCategory-view.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            AddMovieCategoryController addMovieCategoryController = fxmlLoader.getController();
            addMovieCategoryController.setModelAndSetOpCombobox(model);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            showError(e);
        }
    }

    @FXML
    private void onRemoveMovieFromCategory(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/examproject/removeMovieCategory-view.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            RemoveMovieCategoryController removeMovieCategoryController = fxmlLoader.getController();
            removeMovieCategoryController.setModelAndSetOpCombobox(model);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            showError(e);
        }
    }

    @FXML
    private void onCategoryremove(ActionEvent actionEvent) {
        Category category = tbvCategory.getSelectionModel().getSelectedItem();
        if(category != null) {
            try{
                model.deleteCategory(category);
            } catch (Exception e) {
                showError(e);
            }
        }
    }

    @FXML
    private void onEditMovie(ActionEvent actionEvent) {
        Movie movie = tbvMovie.getSelectionModel().getSelectedItem();
        if(movie != null){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/examproject/editMovie-view.fxml"));
            try {
                Scene scene = new Scene(fxmlLoader.load());
                EditMovieController editMovieController = fxmlLoader.getController();
                editMovieController.setModelAndMovie(model,movie);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                showError(e);
            }
        }
    }

    @FXML
    private void onMovieRemove(ActionEvent actionEvent) {
        Movie movie = tbvMovie.getSelectionModel().getSelectedItem();
        if(movie != null) {
            try {
                model.deleteMovie(movie);
            } catch (Exception e) {
                showError(e);
            }
        }
    }

    @FXML
    private void onWatchMovie(ActionEvent actionEvent) {
        try {
            if (tbvMovie.getSelectionModel().getSelectedItem() != null){
                model.watchMovie(tbvMovie.getSelectionModel().getSelectedItem());
            }

        } catch (Exception e) {
            showError(e);
        }
    }
}
