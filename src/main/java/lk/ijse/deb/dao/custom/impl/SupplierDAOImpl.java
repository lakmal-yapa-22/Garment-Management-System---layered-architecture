package lk.ijse.deb.dao.custom.impl;

import lk.ijse.deb.dao.SQLUtil;
import lk.ijse.deb.dao.custom.SupplierDAO;
import lk.ijse.deb.entity.Employee;
import lk.ijse.deb.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO  {


    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Supplier WHERE supplier_id   = ?",id);
    }

    @Override
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO  Supplier  VALUES(?, ?, ?, ?,?)",entity.getId(),entity.getName(),entity.getContactNumber(),entity.getEmail(),entity.getAddress());
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE  Supplier  SET  supplier_name   = ?, contact_number = ?,  email  = ?, address = ?  WHERE supplier_id  = ?",entity.getName(),entity.getContactNumber(),entity.getEmail(),entity.getAddress(),entity.getId());
    }

    @Override
    public List<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = null;
        try {
            rst = SQLUtil.execute("SELECT * FROM Supplier");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<Supplier> allSupplier = new ArrayList<>();

        while (rst.next()) {
            Supplier supplier = new Supplier(
                    rst.getString("supplier_id"),
                    rst.getString("supplier_name"),
                    rst.getString("contact_number"),
                    rst.getString("email"),
                    rst.getString("address")

            );
            allSupplier.add(supplier);
        }
        return allSupplier;
    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst=  SQLUtil.execute("SELECT * FROM Supplier WHERE Supplier_id = ?",id);
        rst.next();
        return new Supplier(rst.getString("supplier_id"),rst.getString("supplier_name"),rst.getString("contact_number"),rst.getString("email"),rst.getString("address"));
    }
}
