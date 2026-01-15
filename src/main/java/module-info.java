module org.example.examproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.microsoft.sqlserver.jdbc;
    requires java.sql;
    requires java.naming;
    requires javafx.graphics;


    opens org.example.examproject to javafx.fxml;
    exports org.example.examproject;
    exports org.example.examproject.GUI;
    opens org.example.examproject.GUI to javafx.fxml;
}