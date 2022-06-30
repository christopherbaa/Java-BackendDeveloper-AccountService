package com.example.jetbrainsbackenddeveloperaccountservice.service;

import com.example.jetbrainsbackenddeveloperaccountservice.dto.UserPassChangedDto;
import com.example.jetbrainsbackenddeveloperaccountservice.dto.UserRegistrationDto;
import com.example.jetbrainsbackenddeveloperaccountservice.exception.EmailExistsException;
import com.example.jetbrainsbackenddeveloperaccountservice.exception.PasswordIsCompromisedException;
import com.example.jetbrainsbackenddeveloperaccountservice.exception.PasswordMatchesException;
import com.example.jetbrainsbackenddeveloperaccountservice.mapper.UserMapper;
import com.example.jetbrainsbackenddeveloperaccountservice.model.User;
import com.example.jetbrainsbackenddeveloperaccountservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(@Autowired UserRepository userRepository,
                           @Autowired UserMapper userMapper,
                           @Autowired BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserRegistrationDto registerUser(User user) {
        if(!isCompromised(user.getPassword())) {
            if(this.userRepository.existsUserByEmailIgnoreCase(user.getEmail())) {
                throw new EmailExistsException();
            }
            this.userRepository.save(userMapper.toUserWithEncryptedPw(user));
            return userMapper.toUserRegistrationDto(this.findUserByEmail(user.getEmail()));
        }
        throw new PasswordIsCompromisedException();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if(!this.userRepository.existsUserByEmailIgnoreCase(email)) {
            throw new UsernameNotFoundException(email);
        }
        return this.findUserByEmail(email);
    }

    @Override
    public UserMapper getUserMapper() {
        return this.userMapper;
    }

    @Override
    public User findUserByEmail(String email) {
        return this.userRepository.findUserByEmailIgnoreCase(email);
    }

    @Override
    public UserPassChangedDto changePassword(User user, String newPass) {
        if(!isCompromised(newPass)) {
            String oldPass = this.findUserByEmail(user.getEmail()).getPassword();
            if(!bCryptPasswordEncoder.matches(newPass, oldPass)) {
                user.setId(this.findUserByEmail(user.getEmail()).getId());
                user.setPassword(bCryptPasswordEncoder.encode(newPass));
                this.userRepository.save(user);
                return new UserPassChangedDto(user);
            }
            throw new PasswordMatchesException();
        }
        throw new PasswordIsCompromisedException();
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.userRepository.existsUserByEmailIgnoreCase(email);
    }

    private boolean isCompromised(String password) {
        ArrayList<String> compromisedPasswords = new ArrayList<>(List.of(
                "PasswordForJanuary", "PasswordForFebruary", "PasswordForMarch", "PasswordForApril",
                "PasswordForMay", "PasswordForJune", "PasswordForJuly", "PasswordForAugust",
                "PasswordForSeptember", "PasswordForOctober", "PasswordForNovember", "PasswordForDecember"
        ));

        return compromisedPasswords.contains(password);
    }
}
