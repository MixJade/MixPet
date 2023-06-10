package com.ship.util;

import org.springframework.security.core.context.SecurityContextHolder;

public final class UserUtil{
    public static Object getUser() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
