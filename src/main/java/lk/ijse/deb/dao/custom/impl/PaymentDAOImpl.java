package lk.ijse.deb.dao.custom.impl;

import lk.ijse.deb.DTO.PaymentDTO;
import lk.ijse.deb.dao.SQLUtil;
import lk.ijse.deb.dao.custom.PaymentDAO;
import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.entity.Employee;
import lk.ijse.deb.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    public String searchStatus(String id) throws SQLException, ClassNotFoundException {
     ResultSet resultSet=   SQLUtil.execute("SELECT paymentStatus FROM payments WHERE order_id = ?",id);

        if (resultSet.next()) {
            String paymentStatus = resultSet.getString("paymentStatus");
            //  System.out.println(paymentStatus); // You can remove this line if not needed for debugging
            return paymentStatus;
        }

        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean save(Payment payment) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO payments VALUES(?, ?, ?, ?,? ,?,?)", payment.getPayment_id(), payment.getOrder_id(), payment.getCashTenderd(), payment.getBalance(), payment.getCashTenderd(), payment.getPaymentMethod(), payment.getPaymentStatus());


        }

    @Override
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    public List<Payment> getAll() throws SQLException {
        ResultSet rst = null;
        try {
            rst = SQLUtil.execute( "SELECT * FROM payments");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<Payment> allPayment = new ArrayList<>();

        while (rst.next()) {
            Payment payment = new Payment(
                    rst.getString("payment_id"),
                    rst.getString("order_id"),
                    rst.getDouble("cashTendered"),
                    rst.getDouble("balance"),
                    rst.getString("cashier"),
                    rst.getString("paymentMethod"),
                    rst.getString("paymentStatus")
            );
            allPayment.add(payment);
        }
        return allPayment;
    }

    @Override
    public Payment search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public String CurrentPayId() throws SQLException, ClassNotFoundException {
      ResultSet resultSet=  SQLUtil.execute("SELECT payment_id   FROM  payments ORDER BY payment_id  DESC LIMIT 1");
        if(resultSet.next()) {
            String orderId = resultSet.getString(1);
            return orderId;
        }
        return null;
    }
}
