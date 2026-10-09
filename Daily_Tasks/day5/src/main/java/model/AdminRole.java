package model;

public class AdminRole extends UserRole {

    public AdminRole(Long id, String name) {
        super(id, name);
    }

    @Override
    public String getRole() {
        return "ADMIN";
    }
}