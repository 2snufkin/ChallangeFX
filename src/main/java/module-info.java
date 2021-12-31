module tim.javafx.challange {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens tim.javafx.challange to javafx.fxml;
    exports tim.javafx.challange;
}