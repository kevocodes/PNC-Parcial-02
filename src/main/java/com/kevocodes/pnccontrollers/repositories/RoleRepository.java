package com.kevocodes.pnccontrollers.repositories;


import com.kevocodes.pnccontrollers.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
