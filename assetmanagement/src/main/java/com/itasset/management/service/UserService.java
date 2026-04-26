package com.itasset.management.service;

import com.itasset.management.model.User;
import com.itasset.management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // ✅ LOGIN
    public User login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                .orElse(null);
    }

    // ✅ FIND BY EMPLOYEE ID
    public User findByEmployeeId(String id) {
        return userRepository.findByEmployeeId(id)
                .orElse(null);
    }

    // ✅ COUNT USERS (ADMIN DASHBOARD)
    public long countAllUsers() {
        return userRepository.count();
    }

    // ✅ GET ALL USERS
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ SAVE USER
    public User save(User user) {
        return userRepository.save(user);
    }
}
