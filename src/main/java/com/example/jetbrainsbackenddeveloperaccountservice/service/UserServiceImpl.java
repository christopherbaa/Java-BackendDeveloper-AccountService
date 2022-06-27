package com.example.jetbrainsbackenddeveloperaccountservice.service;

import com.example.jetbrainsbackenddeveloperaccountservice.dto.UserRegistrationDto;
import com.example.jetbrainsbackenddeveloperaccountservice.exception.EmailExistsException;
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
    public UserRegistrationDto registerUser(User user) {
        if(this.userRepository.existsUserByEmailIgnoreCase(user.getEmail())) {
            throw new EmailExistsException();
        }
        this.userRepository.save(userMapper.toUserWithEncryptedPw(user));
        return userMapper.toUserRegistrationDto(this.userRepository.findUserByEmailIgnoreCase(user.getEmail()));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if(!this.userRepository.existsUserByEmailIgnoreCase(email)) {
            throw new UsernameNotFoundException(email);
        }
        return this.userRepository.findUserByEmailIgnoreCase(email);
    }

    @Override
    public UserMapper getUserMapper() {
        return this.userMapper;
    }

    @Override
    public User findUserByEmail(String email) {
        return this.userRepository.findUserByEmailIgnoreCase(email);
    }
}
