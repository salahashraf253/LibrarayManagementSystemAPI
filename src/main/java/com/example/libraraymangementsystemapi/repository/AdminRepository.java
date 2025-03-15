package com.example.libraraymangementsystemapi.repository;

import com.example.libraraymangementsystemapi.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
    boolean existsByEmail(String email);
    @Query("SELECT a.id FROM Admin a WHERE a.email =:email")
    Long findIdByEmail(String email);
}