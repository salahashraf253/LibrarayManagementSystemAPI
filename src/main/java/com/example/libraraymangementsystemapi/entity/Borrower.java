package com.example.libraraymangementsystemapi.entity;

import com.example.libraraymangementsystemapi.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "borrowers")
public class Borrower extends User {

    public Borrower(){
        super();
    }
    public Borrower(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password,Role.BORROWER);
    }
}
