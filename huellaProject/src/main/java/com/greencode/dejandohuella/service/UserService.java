package com.greencode.dejandohuella.service;

import com.greencode.dejandohuella.repository.UserRepository;
import com.greencode.dejandohuella.persistence.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return "Usuario ya registrado";
        }

        userRepository.save(user);
        return "Usuario registrado con éxito";
    }
}
