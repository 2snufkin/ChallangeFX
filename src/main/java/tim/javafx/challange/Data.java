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

public class Data {
    private final   String fileName = "userData.txt";
    private static final Data instance = new Data();
    private final Path path = Paths.get(fileName);
    private ObservableList<Contact> contactList;


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
            System.out.println(e.getStackTrace());
            
        }

    }

    public ObservableList<Contact> getContactList() {
        return contactList;
    }

    public boolean addContact(Contact e){
        if (e!=null){
            contactList.add(e);
            save();
            return true;
        }
        return false;
    }

    public boolean removeUser(Contact c){
        if (c!=null){
            contactList.remove(c);
            System.out.println("removed");
            return true;
        }
        return false;
    }

    public void save(){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))){
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
            System.out.println("Problem with writing ");

        }
    }


}
