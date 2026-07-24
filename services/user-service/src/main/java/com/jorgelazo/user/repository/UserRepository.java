package com.jorgelazo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jorgelazo.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
