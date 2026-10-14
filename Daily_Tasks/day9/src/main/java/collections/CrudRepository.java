package collections;

import java.util.List;
import java.util.Optional;

/** T is the entity type and ID is its identifier type. */
public interface CrudRepository<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    boolean deleteById(ID id);
    boolean existsById(ID id);
}
