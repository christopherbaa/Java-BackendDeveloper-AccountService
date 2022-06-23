package com.example.jetbrainsbackenddeveloperaccountservice.repository;

import com.example.jetbrainsbackenddeveloperaccountservice.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsUserByEmail(String email);
    User findUserByEmail(String email);
}
