package lk.ijse.deb.dao.custom;

import lk.ijse.deb.DTO.OrderDetailDTO;
import lk.ijse.deb.dao.CrudDAO;
import lk.ijse.deb.dao.SQLUtil;
import lk.ijse.deb.entity.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OrderDetailDAO extends CrudDAO<OrderDetail> {


    public OrderDetailDTO search(String id) throws SQLException, ClassNotFoundException ;

    public List<String> getOrderId() throws SQLException, ClassNotFoundException;
    public boolean save(OrderDetailDTO od) throws SQLException, ClassNotFoundException ;
}
