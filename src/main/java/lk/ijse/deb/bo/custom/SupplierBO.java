package lk.ijse.deb.bo.custom;

import lk.ijse.deb.DTO.SupplierDTO;
import lk.ijse.deb.bo.SuperBO;
import lk.ijse.deb.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierBO extends SuperBO {
    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public boolean update(SupplierDTO supplier) throws SQLException, ClassNotFoundException ;

    public boolean save(SupplierDTO supplier) throws SQLException, ClassNotFoundException;

    public List<SupplierDTO> getAll() throws SQLException, ClassNotFoundException ;

    SupplierDTO search(String id) throws SQLException, ClassNotFoundException;
}
