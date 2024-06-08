package com.kevocodes.pnccontrollers.utils;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.concurrent.atomic.AtomicBoolean;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.kevocodes.pnccontrollers.domain.entities.Role;
import com.kevocodes.pnccontrollers.domain.entities.User;
import com.kevocodes.pnccontrollers.repositories.RoleRepository;
import com.kevocodes.pnccontrollers.repositories.UserRepository;
import java.util.Collections;
import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init(){
        List<User> userList = userRepository.findAll();
        AtomicBoolean flag = new AtomicBoolean(false);

        for(User user : userList){
            List<Role> roles = user.getRoles();
            for(Role role: roles){
                if(role.getId().equals("SUDO")){
                    flag.set(true);
                    return;
                }
            }
        }
        if(!flag.get()){
            Role role = roleRepository.findById("SUDO").orElse(null);
            User sysUser = new User();
            sysUser.setUsername("Sudobisudo");
            sysUser.setEmail("sysadmin@test.com");
            sysUser.setActive(true);
            sysUser.setPassword(passwordEncoder.encode("sudosysadmin"));
            sysUser.setRoles(Collections.singletonList(role));
    
            userRepository.save(sysUser);
        }
    }
}
