package com.example.libraraymangementsystemapi.service;

import com.example.libraraymangementsystemapi.mappers.UserMapper;
import com.example.libraraymangementsystemapi.dto.request.RegistrationRequest;
import com.example.libraraymangementsystemapi.dto.response.RegistrationResponse;
import com.example.libraraymangementsystemapi.entity.Admin;
import com.example.libraraymangementsystemapi.entity.Borrower;
import com.example.libraraymangementsystemapi.enums.Role;
import com.example.libraraymangementsystemapi.exception.EmailAlreadyExistsException;
import com.example.libraraymangementsystemapi.exception.InvalidRoleException;
import com.example.libraraymangementsystemapi.repository.AdminRepository;
import com.example.libraraymangementsystemapi.repository.BorrowerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegistrationService {

    private AdminRepository adminRepository;

    private BorrowerRepository borrowerRepository;
    UserMapper userMapper;

    public RegistrationResponse register(RegistrationRequest request) {
        if (adminRepository.existsByEmail(request.getEmail()) || borrowerRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        if (request.getRole() == Role.ADMIN) {
            Admin admin = (Admin) userMapper.destinationToSource(request);
            Admin savedAdmin = adminRepository.save(admin);

            return userMapper.sourceToDestination(savedAdmin);
        } else if (request.getRole() == Role.BORROWER) {
            Borrower borrower = (Borrower) userMapper.destinationToSource(request);

            Borrower savedBorrower = borrowerRepository.save(borrower);

            return userMapper.sourceToDestination(savedBorrower);
        } else {
            throw new InvalidRoleException("Invalid role");
        }
    }
}