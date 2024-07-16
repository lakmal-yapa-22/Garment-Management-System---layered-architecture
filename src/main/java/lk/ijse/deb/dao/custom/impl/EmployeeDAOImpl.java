package lk.ijse.deb.dao.custom.impl;

import lk.ijse.deb.DTO.EmployeeDTO;
import lk.ijse.deb.dao.SQLUtil;
import lk.ijse.deb.dao.custom.EmployeeDAO;
import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM Employee WHERE employee_id = ?", id);
    }

    public boolean save(Employee employee) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO Employee VALUES(?, ?, ?, ?, ?, ? ,?)", employee.getId(), employee.getName(), employee.getContactNumber(), employee.getSalary(), employee.getAddress(), employee.getBirthday(), employee.getProductId());
    }

    public boolean update(Employee employee) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE Employee SET employee_name  = ?, contact_number = ?, salary    = ? ,address = ?,birthday = ?, product_id = ? WHERE employee_id = ?", employee.getName(), employee.getContactNumber(), employee.getSalary(), employee.getAddress(), employee.getBirthday(), employee.getProductId(), employee.getId());
    }

    public List<Employee> getAll() throws SQLException{
        ResultSet rst = null;
        try {
            rst = SQLUtil.execute("SELECT * FROM Employee");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<Employee> allEmployee = new ArrayList<>();

        while (rst.next()) {
            Employee employee = new Employee(
                    rst.getString("employee_id"),
                    rst.getString("employee_name"),
                    rst.getString("contact_number"),
                    rst.getString("salary"),
                    rst.getString("address"),
                    rst.getString("birthday"),
                    rst.getString("product_id")
            );
            allEmployee.add(employee);
        }
        return allEmployee;
    }

    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
  ResultSet rst=  SQLUtil.execute("SELECT * FROM Employee WHERE employee_id = ?",id);
  rst.next();
  return new Employee(rst.getString("employee_id"),rst.getString("employee_name"),rst.getString("contact_number"),rst.getString("salary"),rst.getString("address"),rst.getString("birthday"),rst.getString("product_id"));
    }
}
