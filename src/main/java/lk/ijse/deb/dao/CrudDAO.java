package lk.ijse.deb.dao;

import lk.ijse.deb.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public boolean save(T entity) throws SQLException, ClassNotFoundException;

    public boolean update(T entity) throws SQLException, ClassNotFoundException;

    public List<T> getAll() throws SQLException, ClassNotFoundException;

    T search(String id) throws SQLException, ClassNotFoundException;
}
