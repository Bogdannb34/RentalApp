package com.finalproject.SmartBear.enumeration;

import static com.finalproject.SmartBear.constant.AuthorityConstant.*;

public enum Role {

    ROLE_ADMIN(ADMIN_AUTHORITIES),
    ROLE_HOST(HOST_AUTHORITIES),
    ROLE_CLIENT(CLIENT_AUTHORITIES);

    private final String[] authorities;

    Role(String... authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
