package com.snva.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snva.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
