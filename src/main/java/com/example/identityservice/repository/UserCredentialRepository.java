package com.example.identityservice.repository;

import com.example.identityservice.entity.UserCredential;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential,Integer> {

    public Optional<UserCredential> findByName(String name);
}
