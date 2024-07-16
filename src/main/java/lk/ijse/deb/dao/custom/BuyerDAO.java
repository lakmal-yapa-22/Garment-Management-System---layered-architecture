package lk.ijse.deb.dao.custom;

import lk.ijse.deb.dao.CrudDAO;
import lk.ijse.deb.dao.SQLUtil;
import lk.ijse.deb.entity.Buyer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BuyerDAO extends CrudDAO<Buyer> {
    List<String> getId() throws SQLException, ClassNotFoundException;




}
