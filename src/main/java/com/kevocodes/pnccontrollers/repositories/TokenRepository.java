package com.kevocodes.pnccontrollers.repositories;

import com.kevocodes.pnccontrollers.domain.entities.Token;
import com.kevocodes.pnccontrollers.domain.entities.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.UUID;

public interface TokenRepository extends ListCrudRepository<Token, UUID> {
    List<Token> findByUserAndActive(User user, Boolean active);
}