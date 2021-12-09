package com.individuals3.backend_football.constant;

public class Authority {
    public static final String[] USER_AUTHORITIES = { "user:read" };
    public static final String[] PLAYER_AUTHORITIES = { "user:read" };
    public static final String[] TEAM_MANAGER_AUTHORITIES = { "user:read", "user:update", "team:add", "team:delete", "team:modify" };
    public static final String[] REFEREE_AUTHORITIES = { "user:read"};
    public static final String[] ADMIN_AUTHORITIES = { "user:read", "user:update", "user:create", "user:delete", "team:add", "team:delete" };
}
