package me.adversing.edenstaffappbot.bot.utils.vars;

import java.util.Objects;

public final class BotVars {
    private final String token;
    private final String username;

    public BotVars(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "BotVars[" +
                "token=" + token + ", " +
                "username=" + username + "]"
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BotVars botVars = (BotVars) o;
        return Objects.equals(token, botVars.token) && Objects.equals(username, botVars.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, username);
    }

}
