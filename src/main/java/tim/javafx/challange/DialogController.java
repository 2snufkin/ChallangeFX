package tim.javafx.challange;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DialogController {

    @FXML
    private TextField nameCtrl;

    @FXML
    private TextField lastCtrl;

    @FXML
    private TextField phoneCtrl;

    @FXML
    private TextArea noteCtrl;

    public Contact processData(){
        String name = nameCtrl.getText().trim().toLowerCase();
        String last = lastCtrl.getText().trim().toLowerCase();
        String phone = phoneCtrl.getText().trim().toLowerCase();
        String notes = noteCtrl.getText().trim().toLowerCase();
       return new Contact(name, last, phone, notes);
    }
}
