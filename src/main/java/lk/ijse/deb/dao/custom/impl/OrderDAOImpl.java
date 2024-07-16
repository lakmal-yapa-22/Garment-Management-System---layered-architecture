package lk.ijse.deb.dao.custom.impl;

import lk.ijse.deb.DTO.OrderDTO;
import lk.ijse.deb.dao.SQLUtil;
import lk.ijse.deb.dao.custom.OrderDAO;
import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.entity.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Order> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public String getCurrentID() throws SQLException, ClassNotFoundException {


        ResultSet resultSet = SQLUtil.execute("SELECT order_id FROM Orders ORDER BY order_id DESC LIMIT 1");
        if (resultSet.next()) {
            String orderId = resultSet.getString(1);
            return orderId;
        }
        return null;
    }

    public boolean save(OrderDTO order) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO  Orders VALUES(?, ?, ?, ?)", order.getOrderId(), order.getBuyerId(), order.getDate(), order.getTime());
    }
}




