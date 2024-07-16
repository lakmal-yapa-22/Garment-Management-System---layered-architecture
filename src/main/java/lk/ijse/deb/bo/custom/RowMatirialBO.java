package lk.ijse.deb.bo.custom;

import lk.ijse.deb.DTO.RowMatirialDTO;
import lk.ijse.deb.bo.SuperBO;
import lk.ijse.deb.entity.RowMatirial;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RowMatirialBO extends SuperBO {
    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public boolean save(RowMatirialDTO rowMatirial) throws SQLException, ClassNotFoundException ;
    public boolean update(RowMatirialDTO rowMatirial) throws SQLException, ClassNotFoundException ;


    public List<RowMatirialDTO> getAll() throws SQLException, ClassNotFoundException;

    RowMatirial searchById(String id) throws SQLException, ClassNotFoundException;
}
