package tim.javafx.challange;

import javafx.event.EventHandler;
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
    private Label welcomeText;
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
    private FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dialogue.fxml"));

    private static Data instance = Data.getInstance();

    public void initialize() {
        colFirst.setCellValueFactory(new PropertyValueFactory<>("name"));
        colLast.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colNotes.setCellValueFactory(new PropertyValueFactory<>("notes"));
        tableView.setItems(instance.getContactList());
        makeTheTableEditable();

    }



    public void makeTheTableEditable() {
        colLast.setCellFactory(TextFieldTableCell.forTableColumn());
        colLast.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Contact, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Contact, String> contactStringCellEditEvent) {
                contactStringCellEditEvent.getTableView().getItems().get(
                        contactStringCellEditEvent.getTablePosition().getRow()
                ).setLastname(contactStringCellEditEvent.getNewValue());
            }
        });
        colFirst.setCellFactory(TextFieldTableCell.forTableColumn());
        colFirst.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Contact, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Contact, String> contactStringCellEditEvent) {
                contactStringCellEditEvent.getTableView().getItems().get(
                        contactStringCellEditEvent.getTablePosition().getRow()
                ).setName(contactStringCellEditEvent.getNewValue());
            }
        });

        colPhone.setCellFactory(TextFieldTableCell.forTableColumn());
        colPhone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Contact, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Contact, String> contactStringCellEditEvent) {
                contactStringCellEditEvent.getTableView().getItems().get(
                        contactStringCellEditEvent.getTablePosition().getRow()
                ).setPhone(contactStringCellEditEvent.getNewValue());
            }
        });

        colNotes.setCellFactory(TextFieldTableCell.forTableColumn());
        colNotes.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Contact, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Contact, String> contactStringCellEditEvent) {
                contactStringCellEditEvent.getTableView().getItems().get(
                        contactStringCellEditEvent.getTablePosition().getRow()
                ).setNotes(contactStringCellEditEvent.getNewValue());
            }
        });

    }

    public Optional<ButtonType> createDial() {
        Dialog<ButtonType> dialogue = new Dialog<>();
        dialogue.initOwner(mainWindow.getScene().getWindow());
        dialogue.setTitle("Add User");
        dialogue.setHeaderText("You can add a user");
        try {
            dialogue.getDialogPane().setContent(fxmlLoader.load());
            dialogue.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialogue.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        } catch (IOException e) {
            System.out.println("yak");
        }
        return dialogue.showAndWait();

    }

    public void addUser() {
        Optional<ButtonType> click = createDial();
        if (click.isPresent() && click.get() == ButtonType.OK) {
            DialogController dialogController = fxmlLoader.getController();
            Contact contact = dialogController.processData();
            instance.addContact(contact);

        }
    }

    public void deleteUser() {
        Contact contact = tableView.getSelectionModel().getSelectedItem();
        instance.removeUser(contact);
    }

    @FXML
    public void editUser() {
        Contact contact = tableView.getSelectionModel().getSelectedItem();


        System.out.println("edit");
    }

}