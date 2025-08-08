package com.apexon.repository;

import com.apexon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User save(User user);

    Optional<User> findByUsername(String username);

    List<User> findAll();

    boolean existsByUsername(String username);
}