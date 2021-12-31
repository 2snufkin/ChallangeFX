module tim.javafx.challange {
    requires javafx.controls;
    requires javafx.fxml;


    opens tim.javafx.challange to javafx.fxml;
    exports tim.javafx.challange;
}