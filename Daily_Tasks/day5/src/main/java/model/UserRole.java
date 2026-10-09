package model;

public abstract class UserRole extends BaseEntity {

    protected String name;

    public UserRole(Long id, String name) {
        super(id);
        this.name = name;
    }

    public abstract String getRole();

    public String getName() {
        return name;
    }
}