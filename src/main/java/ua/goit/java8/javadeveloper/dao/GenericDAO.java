package ua.goit.java8.javadeveloper.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by t.oleksiv on 09/11/2017.
 */
public interface GenericDAO<T, ID> {

    T getById(ID id) throws SQLException;

    List<T> getAll();

    void create(T t);

    void update(T t);

    void delete(T t);
}