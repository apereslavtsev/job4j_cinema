package ru.job4j.cinema.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ru.job4j.cinema.model.User;
import ru.job4j.cinema.repository.UserRepository;

@Service
public class SimpleUserService implements UserService {
    
    private final UserRepository userRepository;

    public SimpleUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

}
