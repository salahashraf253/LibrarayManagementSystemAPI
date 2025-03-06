package com.example.libraraymangementsystemapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "borrowers")
public class Borrower extends User {

}
