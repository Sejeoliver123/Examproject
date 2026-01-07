package org.example.examproject.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private void onMovieAdd(ActionEvent actionEvent) {
    }

    @FXML
    private void onCategoryAdd(ActionEvent actionEvent) {

    }
}
