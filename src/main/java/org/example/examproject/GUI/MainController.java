package org.example.examproject.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainController {

    private MainModel model;

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
        try {
            model.loadMovie();
        } catch (Exception e) {
            showError(e);
        }
    }

    private void loadCategory() {
        try {
            model.loadCategory();
        } catch (Exception e) {
            showError(e);
        }
    }

    @FXML
    private void onMovieAdd(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/examproject/MovieCreation-view.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
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
}
