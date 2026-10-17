package day12;

import com.sportx.core.domain.BaseEntity;

public final class EntityRepository<T extends BaseEntity> {
    private final java.util.Map<Long, T> entities = new java.util.LinkedHashMap<>();

    public void add(T entity) {
        if (entity == null || entity.getId() == null) throw new IllegalArgumentException("Entity and identifier are required");
        if (entities.putIfAbsent(entity.getId(), entity) != null) throw new IllegalArgumentException("Identifier already exists: " + entity.getId());
    }

    public java.util.Optional<T> find(long id) { return java.util.Optional.ofNullable(entities.get(id)); }
    public java.util.List<T> findAll() { return java.util.List.copyOf(entities.values()); }
}
