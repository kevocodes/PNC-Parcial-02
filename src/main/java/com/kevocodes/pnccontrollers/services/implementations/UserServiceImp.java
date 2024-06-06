package com.kevocodes.pnccontrollers.services.implementations;

import com.kevocodes.pnccontrollers.domain.dtos.UpdateUserInfoDTO;
import com.kevocodes.pnccontrollers.domain.dtos.UserRegisterDTO;
import com.kevocodes.pnccontrollers.domain.entities.Role;
import com.kevocodes.pnccontrollers.domain.entities.Token;
import com.kevocodes.pnccontrollers.domain.entities.User;
import com.kevocodes.pnccontrollers.repositories.RoleRepository;
import com.kevocodes.pnccontrollers.repositories.TokenRepository;
import com.kevocodes.pnccontrollers.repositories.UserRepository;
import com.kevocodes.pnccontrollers.services.UserService;
import com.kevocodes.pnccontrollers.utils.JWTTools;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {

    private final JWTTools jwtTools;
    private final TokenRepository tokenRepository;
    public final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTTools jwtTools, TokenRepository tokenRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTools = jwtTools;
        this.tokenRepository = tokenRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findByIdentifier(String identifier, boolean justActives) {
        User user = userRepository.findByUsernameOrEmail(identifier, identifier).orElse(null);

        // If you want to get active and inactive user
        if (!justActives) return user;

        // If you want to get just active user
        if (user == null || !user.isActive()) return null;

        return user;
    }

    @Override
    public User findByUsernameOrEmail(String username, String email, boolean justActives) {
        User user = userRepository.findByUsernameOrEmail(username, email).orElse(null);

        // If you want to get active and inactive users
        if (!justActives) return user;

        // If you want to get just active user
        if (user == null || !user.isActive()) return null;

        return user;
    }

    @Override
    public User findById(UUID id,  boolean justActives) {
        User user = userRepository.findOneById(id);

        // If you want to get active and inactive user
        if (!justActives) return user;

        // If you want to get just active user
        if (user == null || !user.isActive()) return null;

        return user;
    }

    @Override
    public List<User> findAll(boolean justActives) {
        List<User> users =  userRepository.findAll();

        if (!justActives) return users;

        return users.stream().filter(User::isActive).toList();
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void updateUserInfo(User user, UpdateUserInfoDTO info) {
        if (info.getEmail() != null) user.setEmail(info.getEmail());

        if (info.getUsername() != null) user.setUsername(info.getUsername());

        userRepository.save(user);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void toogleActive(User user) {
        user.setActive(!user.isActive());
        userRepository.save(user);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void register(UserRegisterDTO info) {
        User user = new User();

        List<Role> roles = getRoles(List.of("USER"));

        user.setUsername(info.getUsername());
        user.setEmail(info.getEmail());
        user.setPassword(passwordEncoder.encode(info.getPassword()));
        user.setRoles(roles);

        userRepository.save(user);
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void deleteUser(User user) {
        user.setActive(false);
        userRepository.save(user);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Token registerToken(User user) throws Exception {
        cleanTokens(user);

        String tokenString = jwtTools.generateToken(user);
        Token token = new Token(tokenString, user);

        tokenRepository.save(token);

        return token;
    }

    @Override
    public Boolean isTokenValid(User user, String token) {
        try {
            cleanTokens(user);
            List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

            tokens.stream()
                    .filter(tk -> tk.getContent().equals(token))
                    .findAny()
                    .orElseThrow(() -> new Exception());

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void cleanTokens(User user) throws Exception {
        List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

        tokens.forEach(token -> {
            if(!jwtTools.verifyToken(token.getContent())) {
                token.setActive(false);
                tokenRepository.save(token);
            }
        });

    }

    @Override
    public User findUserAuthenticated() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return findByUsernameOrEmail(username, username, true);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void changeRoles(User user, List<String> roles) {
        List<Role> newRoles = getRoles(roles);
        user.setRoles(newRoles);

        userRepository.save(user);
    }

    private Role getRoleById(String roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }

    private List<Role> getRoles(List<String> roleIds) {
        return roleRepository.findAllById(roleIds);
    }
}
