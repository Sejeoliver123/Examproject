module org.example.examproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.examproject to javafx.fxml;
    exports org.example.examproject;
}