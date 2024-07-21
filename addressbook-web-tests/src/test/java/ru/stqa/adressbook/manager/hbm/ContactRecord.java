package ru.stqa.adressbook.manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressbook")

public class ContactRecord {
    @Id

    public int id;

    public String firstname;
    public String lastname;
    public String address;
    public String photo;
    public String home;
    public String mobile;
    public String work;
    public String email;
    public String email2;
    public String email3;


}
