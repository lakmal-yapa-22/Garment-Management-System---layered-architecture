package lk.ijse.deb.dao.custom.impl;

import lk.ijse.deb.dao.SQLUtil;
import lk.ijse.deb.dao.custom.RowMatirialDAO;
import lk.ijse.deb.entity.Employee;
import lk.ijse.deb.entity.RowMatirial;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RowMatirialDAOImpl implements RowMatirialDAO {
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM RawMaterial  WHERE invoice_number  = ?",id);
    }

    @Override
    public boolean save(RowMatirial entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO RawMaterial VALUES(?, ?, ?, ?)",entity.getInvoiceNumber(),entity.getLocation(),entity.getDescription(),entity.getQty());
    }

    @Override
    public boolean update(RowMatirial entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE RawMaterial SET location = ?, description  = ? ,qty = ? WHERE invoice_number  = ?",entity.getLocation(),entity.getDescription(),entity.getQty(),entity.getInvoiceNumber());
    }

    @Override
    public List<RowMatirial> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = null;
        try {
            rst = SQLUtil.execute("SELECT * FROM RawMaterial");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<RowMatirial> allRowMatirial = new ArrayList<>();

        while (rst.next()) {
            RowMatirial rowMatirial = new RowMatirial(
                    rst.getString("invoice_number"),
                    rst.getString("location"),
                    rst.getString("description"),
                    rst.getInt("qty")
            );

            allRowMatirial.add(rowMatirial);
        }
        return allRowMatirial;
    }

    @Override
    public RowMatirial search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst=  SQLUtil.execute("SELECT * FROM RawMaterial WHERE  invoice_number = ?",id);
        rst.next();
        return new RowMatirial(rst.getString("invoice_number"),rst.getString("location"),rst.getString("description"),rst.getInt("qty"));
    }
}
