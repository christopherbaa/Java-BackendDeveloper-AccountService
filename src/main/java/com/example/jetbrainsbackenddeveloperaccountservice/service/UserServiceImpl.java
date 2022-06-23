package com.example.jetbrainsbackenddeveloperaccountservice.service;

import com.example.jetbrainsbackenddeveloperaccountservice.mapper.UserMapper;
import com.example.jetbrainsbackenddeveloperaccountservice.model.User;
import com.example.jetbrainsbackenddeveloperaccountservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(@Autowired UserRepository userRepository,
                           @Autowired UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public boolean registerUser(User user) {
        // In Stage 1 duplicate users are allowed!
        if(!this.userRepository.existsUserByEmail(user.getEmail())) {
            this.userRepository.save(userMapper.toUserWithEncryptedPw(user));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if(!this.userRepository.existsUserByEmail(email)) {
            throw new UsernameNotFoundException(email);
        }
        return this.userRepository.findUserByEmail(email);
    }

    @Override
    public UserMapper getUserMapper() {
        return this.userMapper;
    }
}
