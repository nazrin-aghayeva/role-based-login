package com.employee.app.services;

import com.employee.app.entities.Roles;
import com.employee.app.entities.Users;
import com.employee.app.repos.RoleRepo;
import com.employee.app.repos.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService {

    public UserRepo userRepo;
    public RoleRepo roleRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserService(UserRepo userRepo, RoleRepo roleRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo= roleRepo;
        this.bCryptPasswordEncoder= bCryptPasswordEncoder;
    }

    public Optional<Users> findUserByEmail(String user_email){
        return userRepo.findByEmail(user_email);
    }

    public Users saveUser(Users user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setConfirm_password(bCryptPasswordEncoder.encode(user.getConfirm_password()));
        Roles userRole= roleRepo.findByRole("USER");
        user.setRoles(new HashSet<Roles>(Arrays.asList(userRole)));
        return userRepo.save(user);
    }

}
