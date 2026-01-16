package org.example.examproject.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.examproject.BE.Movie;

public class MovieCreationController {
    private MainModel model;
    @FXML
    private AnchorPane txtTitle;
    @FXML
    private TextField txtIMDBrating;
    @FXML
    private Button btnAddMovie;
    @FXML
    private TextField txtFilelink;
    @FXML
    private TextField txtMovieTitle;

    public void setModel(MainModel model) { this.model = model; }

    @FXML
    private void onCreateMovie(ActionEvent actionEvent) {
        try {
            if(txtMovieTitle.getText() != null && txtFilelink.getText() != null && txtIMDBrating.getText() != null){

                model.createMovie(new Movie(txtMovieTitle.getText(),Float.parseFloat(txtIMDBrating.getText()),txtFilelink.getText()));
                Button btn = (Button) actionEvent.getSource();
                Stage window = (Stage) btn.getScene().getWindow();
                window.close();
            }
        } catch (Exception e) {
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
