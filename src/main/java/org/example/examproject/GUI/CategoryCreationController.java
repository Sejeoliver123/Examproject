package org.example.examproject.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.examproject.BE.Category;

public class CategoryCreationController {
    private MainModel model;
    @FXML
    private TextField txtCategoryTitle;
    @FXML
    private Button btnAddCategory;

    public void setModel(MainModel model) { this.model = model; }

    @FXML
    private void onCreateCategory(ActionEvent actionEvent) {
        try {
            if(txtCategoryTitle.getText() != null){
                model.createCategory(new Category(txtCategoryTitle.getText()));
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
