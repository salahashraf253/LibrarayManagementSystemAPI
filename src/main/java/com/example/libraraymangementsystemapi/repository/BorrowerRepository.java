package com.example.libraraymangementsystemapi.repository;

import com.example.libraraymangementsystemapi.entity.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
    Optional<Borrower> findByEmail(String email);
    boolean existsByEmail(String email);
}