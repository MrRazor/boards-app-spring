package cz.uhk.boardsappspring.security;

public enum Role {
    USER, ADMIN;

    public String getDatabaseName() {
        return "ROLE_" + name();
    }
}
