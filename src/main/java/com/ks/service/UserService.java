package com.ks.service;

import com.ks.model.User;
import com.ks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List list() {
        return (List) userRepository.findAll();
    }

    public boolean isExists(Long id) {
        return userRepository.existsById(id);
    }

    public void save(List<User> users) {
        userRepository.saveAll(users);
    }
}
