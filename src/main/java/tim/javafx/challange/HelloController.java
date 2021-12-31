package tim.javafx.challange;

import javafx.event.EventHandler;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Optional;

public class HelloController {


    @FXML
    private GridPane mainWindow;

    @FXML
    private TableView<Contact> tableView;

    @FXML
    private TableColumn<Contact, String> colFirst;

    @FXML
    private TableColumn<Contact, String> colLast;
    @FXML
    private TableColumn<Contact, String> colNotes;
    @FXML
    private TableColumn<Contact, String> colPhone;

    private final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dialogue.fxml"));
    private static final  Data instance = Data.getInstance();
    private static  final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());


    public void initialize() {
        // I defined the cell factory - half here and half in the fxml for learning purposes
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colNotes.setCellValueFactory(new PropertyValueFactory<>("notes"));
        tableView.setItems(instance.getContactList());
        makeTheTableEditable();

    }

    /**
     * make the table editable so the user can click on a row and edit the details on the fly
     */

    public void makeTheTableEditable() {

        colLast.setCellFactory(TextFieldTableCell.forTableColumn());
        colLast.setOnEditCommit(contactStringCellEditEvent ->
                contactStringCellEditEvent.getTableView().getItems().get(
                        contactStringCellEditEvent.getTablePosition().getRow()).setLastname(contactStringCellEditEvent.getNewValue()));

        colFirst.setCellFactory(TextFieldTableCell.forTableColumn());
        colFirst.setOnEditCommit(contactStringCellEditEvent ->
                contactStringCellEditEvent.getTableView().getItems().get(
                        contactStringCellEditEvent.getTablePosition().getRow()).setName(contactStringCellEditEvent.getNewValue()));

        colPhone.setCellFactory(TextFieldTableCell.forTableColumn());
        colPhone.setOnEditCommit(contactStringCellEditEvent ->
                contactStringCellEditEvent.getTableView().getItems().get(
                        contactStringCellEditEvent.getTablePosition().getRow()).setPhone(contactStringCellEditEvent.getNewValue()));

        colNotes.setCellFactory(TextFieldTableCell.forTableColumn());
        colNotes.setOnEditCommit(contactStringCellEditEvent ->
                contactStringCellEditEvent.getTableView().getItems().get(
                        contactStringCellEditEvent.getTablePosition().getRow()).setNotes(contactStringCellEditEvent.getNewValue()));

    }

    /**
     * Create a dialogue box to add a content. Should be used only when the user click on add button
     * @return  Optional<ButtonType> to be used in others methods
     */

    public Optional<ButtonType> createDial() {
        Dialog<ButtonType> dialogue = new Dialog<>();
        dialogue.initOwner(mainWindow.getScene().getWindow());
        dialogue.setTitle("Add User");
        dialogue.setHeaderText("You can add a user");
        try {
            dialogue.getDialogPane().setContent(fxmlLoader.load());

        } catch (IOException e) {
            LOGGER.severe("problem with creating the dialogue. IO exception");
        }
        dialogue.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialogue.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        return dialogue.showAndWait();

    }

    /**
     * add user: 1. create the dialogue    2. if the user click on the ok button, process the data in the dialogue
     * controller
     */

    public void addUser() {
        Optional<ButtonType> click = createDial();
        if (click.isPresent() && click.get() == ButtonType.OK) {
            DialogController dialogController = fxmlLoader.getController();
          Contact contact =    dialogController.processData();
             instance.addContact(contact);
        }
    }

    /**
     * Getting the selected user and removing it
      */
    public void deleteUser() {
        Contact contact = tableView.getSelectionModel().getSelectedItem();
        instance.removeUser(contact);
    }


}