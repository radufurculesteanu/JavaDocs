package ro.teamnet.zth.api.em;

import java.util.List;

/**
 * Created by Radu.Furculesteanu on 7/13/2017.
 */
public interface EntityManager {
    <T> T findById(Class<T> entityClass, Long id);
    Long getNextIdVal(String tableName, String columnIdName);
    <T> Object insert(T entity);
    <T> List<T> findAll(Class<T> entityClass);
}
