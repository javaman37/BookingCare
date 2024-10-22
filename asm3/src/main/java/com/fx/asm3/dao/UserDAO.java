package com.fx.asm3.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fx.asm3.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

	//User findUserByEmail(String email);

}
