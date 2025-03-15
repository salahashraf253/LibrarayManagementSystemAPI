package com.example.libraraymangementsystemapi.entity;

import com.example.libraraymangementsystemapi.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "admins")
public class Admin extends User {

    public Admin() {
        super();
    }
    public Admin(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password, Role.ADMIN);
    }
}
