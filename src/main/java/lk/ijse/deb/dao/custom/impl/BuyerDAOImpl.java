package lk.ijse.deb.dao.custom.impl;

import lk.ijse.deb.dao.SQLUtil;
import lk.ijse.deb.dao.custom.BuyerDAO;
import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.entity.Buyer;
import lk.ijse.deb.entity.Employee;
import lk.ijse.deb.entity.RowMatirial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyerDAOImpl implements BuyerDAO {
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM Buyer WHERE buyer_id    = ?",id);
    }
    @Override
    public boolean save(Buyer buyer) throws SQLException, ClassNotFoundException {
        return  SQLUtil.execute("INSERT INTO Buyer VALUES(?, ?, ?, ?,?)",buyer.getId(),buyer.getTel(),buyer.getAddress(),buyer.getEmail(),buyer.getCompany());


    }

    @Override
    public boolean update(Buyer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Buyer SET contact_number = ?, address = ?, email = ?, company_name = ? WHERE buyer_id = ?",entity.getTel(),entity.getAddress(),entity.getEmail(),entity.getCompany(),entity.getId());
    }

    @Override
    public List<Buyer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = null;
        try {
            rst = SQLUtil.execute("SELECT * FROM Buyer");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<Buyer> allBuyer = new ArrayList<>();

        while (rst.next()) {
            Buyer buyer = new Buyer(
                    rst.getString("buyer_id"),
                    rst.getString("contact_number"),
                    rst.getString("address"),
                    rst.getString("email"),
                    rst.getString("company_name")
            );
            allBuyer.add(buyer);
        }
        return allBuyer;
    }

    @Override
    public Buyer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst=  SQLUtil.execute("SELECT * FROM Buyer WHERE buyer_id = ?",id);
        rst.next();
        return new Buyer(rst.getString("buyer_id"),rst.getString("contact_number"),rst.getString("address"),rst.getString("email"),rst.getString("company_name"));

    }

    @Override
    public List<String> getId() throws SQLException, ClassNotFoundException {

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT buyer_id FROM  Buyer");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }


}
