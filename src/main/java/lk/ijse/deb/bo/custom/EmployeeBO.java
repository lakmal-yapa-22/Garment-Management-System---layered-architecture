package lk.ijse.deb.bo.custom;

import lk.ijse.deb.DTO.EmployeeDTO;
import lk.ijse.deb.DTO.ProductDTO;
import lk.ijse.deb.bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface
EmployeeBO extends SuperBO {
    public boolean delete(String id) throws SQLException, ClassNotFoundException ;
    public boolean update(EmployeeDTO employee) throws SQLException, ClassNotFoundException;

    public boolean save(EmployeeDTO employee) throws SQLException, ClassNotFoundException ;
    public List<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException;

    ProductDTO searchId(String code) throws SQLException, ClassNotFoundException;



    ArrayList<ProductDTO> getProductCode() throws SQLException, ClassNotFoundException;

    EmployeeDTO search(String id) throws SQLException, ClassNotFoundException;
}
