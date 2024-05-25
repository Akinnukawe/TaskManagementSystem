package com.project.TMSwithSec.repository;

import com.project.TMSwithSec.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Integer> {

    Optional<Users> findByEmail(String email);

    List<Users> findByRole(String role);
}
