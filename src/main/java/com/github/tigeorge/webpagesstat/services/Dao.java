package com.github.tigeorge.webpagesstat.services;

import java.sql.SQLException;
import java.util.List;

/*
* Интерфейс CRUD операций над БД
*/
public interface Dao<T> {

    T get (long id) throws SQLException;

    List<T> getAll() throws SQLException;

    void save(T t) throws SQLException;

    void update(T t) throws SQLException;

    void delete(T t) throws SQLException;
}
