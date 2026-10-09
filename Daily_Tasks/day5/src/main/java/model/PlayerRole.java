package model;

public class PlayerRole extends UserRole {

    public PlayerRole(Long id, String name) {
        super(id, name);
    }

    @Override
    public String getRole() {
        return "PLAYER";
    }
}