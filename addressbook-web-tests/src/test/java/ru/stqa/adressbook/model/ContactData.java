package ru.stqa.adressbook.model;

public record ContactData(String id,String firstname,String lastname,String address, String photo) {

    public ContactData() {
        this("", "", "", "","");
    }

    public ContactData withId (String id) {
        return new ContactData(id, this.firstname, this.lastname,
                 this.address, this.photo);
    }

    public ContactData withFirstname(String firstname) {
        return new ContactData(this.id, firstname,this.lastname,
                 this.address, this.photo);
    }

    public ContactData withLastname(String lastname) {
        return new ContactData(this.id, this.firstname, lastname,
                this.address, this.photo);
    }

    public ContactData withAdress(String adress) {
        return new ContactData(this.id,this.firstname,  lastname,
               adress, this.photo);
    }
    public ContactData withPhoto(String photo) {
        return new ContactData(this.id,this.firstname, lastname,
                this.address, photo);
    }
}