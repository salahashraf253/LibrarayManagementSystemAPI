package com.example.libraraymangementsystemapi.service;

import com.example.libraraymangementsystemapi.dto.request.RegistrationRequest;
import com.example.libraraymangementsystemapi.dto.response.RegistrationResponse;
import com.example.libraraymangementsystemapi.entity.Admin;
import com.example.libraraymangementsystemapi.entity.Borrower;
import com.example.libraraymangementsystemapi.enums.Role;
import com.example.libraraymangementsystemapi.repository.AdminRepository;
import com.example.libraraymangementsystemapi.repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegistrationResponse register(RegistrationRequest request) {
        if (adminRepository.existsByEmail(request.getEmail()) || borrowerRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (request.getRole() == Role.ADMIN) {
            Admin admin = new Admin();
            admin.setFirstName(request.getFirstName());
            admin.setLastName(request.getLastName());
            admin.setEmail(request.getEmail());
            admin.setPassword(passwordEncoder.encode(request.getPassword()));
            admin.setRole(request.getRole());

            Admin savedAdmin = adminRepository.save(admin);

            return new RegistrationResponse(
                    savedAdmin.getId(),
                    savedAdmin.getFirstName(),
                    savedAdmin.getLastName(),
                    savedAdmin.getEmail(),
                    savedAdmin.getRole()
            );
        } else if (request.getRole() == Role.BORROWER) {
            Borrower borrower = new Borrower();
            borrower.setFirstName(request.getFirstName());
            borrower.setLastName(request.getLastName());
            borrower.setEmail(request.getEmail());
            borrower.setPassword(passwordEncoder.encode(request.getPassword()));
            borrower.setRole(request.getRole());

            Borrower savedBorrower = borrowerRepository.save(borrower);

            return new RegistrationResponse(
                    savedBorrower.getId(),
                    savedBorrower.getFirstName(),
                    savedBorrower.getLastName(),
                    savedBorrower.getEmail(),
                    savedBorrower.getRole()
            );
        } else {
            throw new RuntimeException("Invalid role");
        }
    }
}