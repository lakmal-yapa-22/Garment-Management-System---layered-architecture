package lk.ijse.deb.bo.custom;

import lk.ijse.deb.DTO.BuyerDTO;
import lk.ijse.deb.bo.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface BuyerBO extends SuperBO {
    boolean delete(String id) throws SQLException, ClassNotFoundException;

    boolean save(BuyerDTO buyerDTO) throws SQLException, ClassNotFoundException;

    boolean update(BuyerDTO buyer) throws SQLException, ClassNotFoundException;

    List<BuyerDTO> getAll() throws SQLException, ClassNotFoundException;

    BuyerDTO search(String id) throws SQLException, ClassNotFoundException;


}
