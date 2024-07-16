package lk.ijse.deb.dao.custom;

import lk.ijse.deb.DTO.OrderDTO;
import lk.ijse.deb.dao.CrudDAO;
import lk.ijse.deb.dao.SQLUtil;
import lk.ijse.deb.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface OrderDAO extends CrudDAO<Order> {


    public String getCurrentID() throws SQLException, ClassNotFoundException ;

    public boolean save(OrderDTO order) throws SQLException, ClassNotFoundException;
}
