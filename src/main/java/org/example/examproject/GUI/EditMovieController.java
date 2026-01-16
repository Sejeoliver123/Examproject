package org.example.examproject.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.examproject.BE.Movie;

public class EditMovieController {
    MainModel model;
    Movie editingMovie;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPersonalRating;
    @FXML
    private TextField txtIMDBRating;
    @FXML
    private TextField txtMovieLink;

    public void setModelAndMovie(MainModel model, Movie movie) {
        this.model = model;
        this.editingMovie = movie;
        txtName.setText(movie.getName());
        txtIMDBRating.setText(String.valueOf(movie.getIMDBRating()));
        if(movie.getPersonalRating() != 0.0) {
            txtPersonalRating.setText(String.valueOf(movie.getPersonalRating()));
        }
        txtMovieLink.setText(movie.getFileLink());
    }

    @FXML
    private void onUpdateMovie(ActionEvent actionEvent) {
        if(txtName.getText() != null && !txtName.getText().equals(editingMovie.getName())) {
            editingMovie.setName(txtName.getText());
        }
        if(txtPersonalRating.getText() != null ) {
            try{
                if(Float.parseFloat(txtPersonalRating.getText().trim()) == editingMovie.getPersonalRating()){
                    editingMovie.setPersonalRating(Float.parseFloat(txtPersonalRating.getText().trim()));
                }
            } catch (Exception e) {

            }
        }
        try{
            if(txtIMDBRating.getText() != null && Float.parseFloat(txtIMDBRating.getText().trim()) == editingMovie.getIMDBRating()) {
                editingMovie.setIMDBRating(Float.parseFloat(txtIMDBRating.getText().trim()));
            }
        } catch (Exception e) {

        }
        if(txtMovieLink.getText() != null && !txtMovieLink.getText().equals(editingMovie.getFileLink())) {
            editingMovie.setFileLink(txtMovieLink.getText());
        }
        try {
             model.updateMovie(editingMovie);
            Button btn = (Button) actionEvent.getSource();
            Stage window = (Stage) btn.getScene().getWindow();
            window.close();
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
