package com.kevocodes.pnccontrollers.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public abstract class GetAuthenticatedUserNameHelper {

    public static String getUserName(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName;

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        userName = userDetails.getUsername();

        return userName;
    }
    
}
