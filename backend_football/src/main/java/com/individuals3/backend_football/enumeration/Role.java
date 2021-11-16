package com.individuals3.backend_football.enumeration;

import com.individuals3.backend_football.constant.Authority;

public enum Role {
    ROLE_USER(Authority.USER_AUTHORITIES),
    ROLE_PLAYER(Authority.PLAYER_AUTHORITIES),
    ROLE_TEAM_MANAGER(Authority.TEAM_MANAGER_AUTHORITIES),
    ROLE_REFEREE(Authority.REFEREE_AUTHORITIES),
    ROLE_ADMIN(Authority.ADMIN_AUTHORITIES);

    private String[] authorities;

    Role(String... authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
