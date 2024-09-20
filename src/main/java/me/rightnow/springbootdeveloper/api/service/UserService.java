package me.rightnow.springbootdeveloper.api.service;

import me.rightnow.springbootdeveloper.api.entity.user.User;
import me.rightnow.springbootdeveloper.api.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }
}
