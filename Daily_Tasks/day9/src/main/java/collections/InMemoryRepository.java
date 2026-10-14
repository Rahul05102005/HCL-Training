package collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/** In-memory CRUD store; saving an existing ID replaces that entity. */
public final class InMemoryRepository<T, ID> implements CrudRepository<T, ID> {
    private final Map<ID, T> entities = new HashMap<>();
    private final Function<? super T, ? extends ID> idExtractor;

    public InMemoryRepository(Function<? super T, ? extends ID> idExtractor) {
        this.idExtractor = Objects.requireNonNull(idExtractor, "ID extractor cannot be null.");
    }

    @Override public T save(T entity) {
        Objects.requireNonNull(entity, "Entity cannot be null.");
        ID id = Objects.requireNonNull(idExtractor.apply(entity), "Entity ID cannot be null.");
        entities.put(id, entity);
        return entity;
    }
    @Override public Optional<T> findById(ID id) { return Optional.ofNullable(entities.get(requireId(id))); }
    @Override public List<T> findAll() { return List.copyOf(new ArrayList<>(entities.values())); }
    @Override public boolean deleteById(ID id) { return entities.remove(requireId(id)) != null; }
    @Override public boolean existsById(ID id) { return entities.containsKey(requireId(id)); }

    private static <ID> ID requireId(ID id) { return Objects.requireNonNull(id, "ID cannot be null."); }
}
