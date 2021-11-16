package com.individuals3.backend_football.constant;

public class Authority {
    public static final String[] USER_AUTHORITIES = { "user:read" };
    public static final String[] PLAYER_AUTHORITIES = { "user:read" };
    public static final String[] TEAM_MANAGER_AUTHORITIES = { "user:read", "user:update" };
    public static final String[] REFEREE_AUTHORITIES = { "user:read"};
    public static final String[] ADMIN_AUTHORITIES = { "user:read", "user:update", "user:create", "user:delete" };
}
