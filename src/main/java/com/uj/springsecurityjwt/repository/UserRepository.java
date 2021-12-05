package com.uj.springsecurityjwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uj.springsecurityjwt.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

	Optional<Users> findUserByEmailId(String emailId);
}
