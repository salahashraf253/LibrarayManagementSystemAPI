package com.example.libraraymangementsystemapi.service;

import com.example.libraraymangementsystemapi.entity.Admin;
import com.example.libraraymangementsystemapi.entity.Borrower;
import com.example.libraraymangementsystemapi.repository.AdminRepository;
import com.example.libraraymangementsystemapi.repository.BorrowerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private AdminRepository adminRepository;

    private BorrowerRepository borrowerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Admin> admin = adminRepository.findByEmail(email);
        if (admin.isPresent()) {
            return admin.get();
        }
        Optional<Borrower> borrower = borrowerRepository.findByEmail(email);
        if (borrower.isPresent()) {
            return borrower.get();
        }
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}