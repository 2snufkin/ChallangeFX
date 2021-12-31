package tim.javafx.challange;

import javafx.beans.property.SimpleStringProperty;

public class Contact {
   private final SimpleStringProperty   name;
   private final SimpleStringProperty lastname;
   private final SimpleStringProperty phone;
   private final SimpleStringProperty notes;

    public Contact(String name, String lastName, String phone, String notes) {
        this.name = new SimpleStringProperty(name);
        this.lastname = new SimpleStringProperty(lastName);
        this.phone = new SimpleStringProperty(phone);
        this.notes = new SimpleStringProperty(notes);
    }

    public String getLastname() {
        return lastname.get();
    }

    public String getName() {
        return name.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public String getNotes() {
        return notes.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public void setNotes(String notes) {
        this.notes.set(notes);
    }
}
