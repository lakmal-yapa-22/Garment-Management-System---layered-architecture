package lk.ijse.deb.bo.custom;

import lk.ijse.deb.DTO.OrderDetailDTO;
import lk.ijse.deb.DTO.PaymentDTO;
import lk.ijse.deb.bo.SuperBO;
import lk.ijse.deb.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentBO extends SuperBO {

    public OrderDetailDTO searchOrderDetail(String id) throws SQLException, ClassNotFoundException ;

    public String searchByOrderId(String id) throws SQLException, ClassNotFoundException ;

    public boolean save(PaymentDTO payment) throws SQLException, ClassNotFoundException;
    public List<PaymentDTO> getAll() throws SQLException ;
    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public String getCurrentId() throws SQLException, ClassNotFoundException ;
}
