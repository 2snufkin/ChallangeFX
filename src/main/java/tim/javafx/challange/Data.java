package tim.javafx.challange;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class Data {
    private static final   String FILENAME = "userData.txt";
    private static final   Logger LOGGER = Logger.getLogger(Data.class.getName());

    private static final Data instance = new Data();
    private final Path path = Paths.get(FILENAME);
    private final ObservableList<Contact> contactList;


    private Data() {
        contactList = FXCollections.observableArrayList();
    }

    public static Data getInstance() {
        return instance;
    }

    public void load() {
                String input;
        try(BufferedReader br = Files.newBufferedReader(path)) {
            while ((input = br.readLine()) != null) {
                String[] contacts = input.split("\t");
                String name = contacts[0];
                String lastname = contacts[1];
                String phone = contacts[2];
                String note = contacts[3];
                Contact todocontact = new Contact(name, lastname, phone, note);
                contactList.add(todocontact);
            }

        } 
        catch (IOException e){
            LOGGER.severe("problem with loading the data ");
            e.getStackTrace();
            
        }

    }

    public ObservableList<Contact> getContactList() {
        return contactList;
    }

    public boolean addContact(Contact e){
        if (e!=null){
            contactList.add(e);
            return true;
        }
        return false;
    }

    public boolean removeUser(Contact c){
        if (c!=null){
            contactList.remove(c);
             return true;
        }
        return false;
    }

    public void save(){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILENAME))){
            for (Contact contact : contactList) {
                String name = contact.getName();
                String last = contact.getLastname();
                String phone = contact.getPhone();
                String notes = contact.getNotes();
                bufferedWriter.write(String.format(
                        "%s\t%s\t%s\t%s",
                        name, last, phone, notes));
                bufferedWriter.newLine();
            }
        } catch(IOException e){
            LOGGER.severe("problem with saving the data " );
            e.getStackTrace();


        }
    }


}
