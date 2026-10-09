package model;

public abstract class BaseEntity {

    protected Long id;

    public BaseEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}