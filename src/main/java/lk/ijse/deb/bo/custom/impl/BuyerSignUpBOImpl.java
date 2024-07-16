package lk.ijse.deb.bo.custom.impl;

import lk.ijse.deb.dao.SQLUtil;
import lk.ijse.deb.dao.custom.impl.BuyerSingnUpDAOImpl;

import java.sql.SQLException;

public class BuyerSignUpBOImpl {

    public boolean buyersinUp(String email, String username, String password, String type) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO AdminBuyer (email,username,password,type) VALUES (?, ?, ?, ?)",email,username,password,type);
    }
}
