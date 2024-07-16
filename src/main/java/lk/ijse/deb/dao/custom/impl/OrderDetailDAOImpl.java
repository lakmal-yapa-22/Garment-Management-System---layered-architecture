package lk.ijse.deb.dao.custom.impl;

import lk.ijse.deb.DTO.OrderDetailDTO;
import lk.ijse.deb.dao.SQLUtil;
import lk.ijse.deb.dao.custom.OrderDetailDAO;
import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.entity.OrderDetail;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    public OrderDetailDTO search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM OrderDetail  WHERE order_id   = ?", id);

        if (resultSet.next()) {
            String orderId = resultSet.getString(1);
            String itemCode = resultSet.getString(2);
            int qty = resultSet.getInt(3);
            double unitPrice = resultSet.getDouble(4);
            double subTotal = resultSet.getDouble(5);
            int discountRate = resultSet.getInt(6);
            double discount = resultSet.getDouble(7);
            double finalAmount = resultSet.getDouble(8);

            OrderDetailDTO orderDetail = new OrderDetailDTO(orderId, itemCode, qty, unitPrice, subTotal, discountRate, discount, finalAmount);

            return orderDetail;
        }

        return null;
    }


    public List<String> getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT DISTINCT order_id   FROM  OrderDetail ");
        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }


    public boolean save(OrderDetailDTO od) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO OrderDetail VALUES(?, ?, ?, ? ,? ,? ,? ,?)", od.getOrderId(), od.getItemCode(), od.getQty(), od.getUnitPrice(), od.getSubTotal(), od.getDiscountRate(), od.getDiscount(), od.getFinalAmount());


    }
}