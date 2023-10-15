module com.example.masathaiquiz {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.masathaiquiz to javafx.fxml;
    exports com.example.masathaiquiz;
}