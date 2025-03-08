package com.example.libraraymangementsystemapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "borrowers")
public class Borrower extends User {

}
