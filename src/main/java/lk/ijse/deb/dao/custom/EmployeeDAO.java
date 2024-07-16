package lk.ijse.deb.dao.custom;

import lk.ijse.deb.dao.CrudDAO;
import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeDAO extends CrudDAO<Employee> {

}
