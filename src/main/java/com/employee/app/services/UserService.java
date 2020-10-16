package com.employee.app.services;

import com.employee.app.entities.Users;
import com.employee.app.repos.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    public UserRepo userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserService(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder= bCryptPasswordEncoder;
    }

    public Optional<Users> findUserByEmail(String user_email){
        return userRepo.findByEmail(user_email);
    }

    public Users saveUser(Users user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setConfirm_password(bCryptPasswordEncoder.encode(user.getConfirm_password()));
        return userRepo.save(user);
    }

}
