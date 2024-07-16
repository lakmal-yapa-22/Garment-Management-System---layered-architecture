package lk.ijse.deb.dao.custom;

import lk.ijse.deb.dao.CrudDAO;
import lk.ijse.deb.dao.SQLUtil;
import lk.ijse.deb.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentDAO extends CrudDAO<Payment> {
    public String searchStatus(String id) throws SQLException, ClassNotFoundException;

    public boolean save(Payment payment) throws SQLException, ClassNotFoundException ;



    public List<Payment> getAll() throws SQLException ;
    public String CurrentPayId() throws SQLException, ClassNotFoundException ;
}
