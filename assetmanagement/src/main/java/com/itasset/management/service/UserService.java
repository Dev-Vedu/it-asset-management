package com.itasset.management.service;

import com.itasset.management.model.User;
import com.itasset.management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).orElse(null);
    }

    public User findByEmployeeId(String id) {
        return userRepository.findByEmployeeId(id).orElse(null);
    }


    public User save(User user) {
        return userRepository.save(user);
    }
}
