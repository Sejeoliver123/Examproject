package org.example.examproject.GUI;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.examproject.BE.Category;

public class MainController {

    private MainModel model;
    @FXML
    private TableView<Category> tbvCategory;
    @FXML
    private TableColumn tbvColCategoryName;

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
