package com.kevocodes.pnccontrollers.services;

import com.kevocodes.pnccontrollers.domain.dtos.UpdateUserInfoDTO;
import com.kevocodes.pnccontrollers.domain.dtos.UserRegisterDTO;
import com.kevocodes.pnccontrollers.domain.entities.Token;
import com.kevocodes.pnccontrollers.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User findByIdentifier(String identifier, boolean justActives);
    User findByUsernameOrEmail(String username, String email, boolean justActives);
    User findById(UUID id, boolean justActives);
    List<User> findAll(boolean justActives);
    void register(UserRegisterDTO info);
    boolean checkPassword(User user, String password);
    void changePassword(User user, String newPassword);
    void deleteUser(User user);
    void updateUserInfo(User user, UpdateUserInfoDTO info);
    void toogleActive(User user);

    //Token management
    Token registerToken(User user) throws Exception;
    Boolean isTokenValid(User user, String token);
    void cleanTokens(User user) throws Exception;

    //Find User authenticated
    User findUserAuthenticated();

    //Roles methods
    void changeRoles(User user, List<String> roles);
}
