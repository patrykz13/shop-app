package computershop.database.basicCrud;

import java.util.List;

/**
 * Created by Kamil Cieślik on 03.12.2017.
 */
public interface EntityCRUD<T extends Object> {

    List<T> getEntities();

    void saveEntity(T entity);

    T getEntity(int id);

    void deleteEntity(int id);

}
