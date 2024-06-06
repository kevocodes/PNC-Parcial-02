package com.kevocodes.pnccontrollers.repositories;

import com.kevocodes.pnccontrollers.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsernameOrEmail(String username, String email);
    User findOneById(UUID id);
}
