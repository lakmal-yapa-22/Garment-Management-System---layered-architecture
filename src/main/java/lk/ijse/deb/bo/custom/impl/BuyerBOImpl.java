package lk.ijse.deb.bo.custom.impl;

import lk.ijse.deb.DTO.BuyerDTO;
import lk.ijse.deb.DTO.EmployeeDTO;
import lk.ijse.deb.bo.custom.BuyerBO;
import lk.ijse.deb.dao.DAOFactory;
import lk.ijse.deb.dao.custom.BuyerDAO;
import lk.ijse.deb.dao.custom.impl.BuyerDAOImpl;
import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.entity.Buyer;
import lk.ijse.deb.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyerBOImpl implements BuyerBO {
    BuyerDAO buyerDAO = (BuyerDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BUYER);

    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        return buyerDAO.delete(id);


    }

    public boolean save(BuyerDTO buyer) throws SQLException, ClassNotFoundException {
        return  buyerDAO.save(new Buyer(buyer.getId(), buyer.getTel(), buyer.getAddress(),  buyer.getEmail(), buyer.getCompany()));
    }

    @Override
    public boolean update(BuyerDTO buyer) throws SQLException, ClassNotFoundException {
        return buyerDAO.update(new Buyer(buyer.getId(), buyer.getTel(), buyer.getAddress(), buyer.getEmail(), buyer.getCompany()));
    }

    @Override
    public List<BuyerDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Buyer> buyers = buyerDAO.getAll();
        List<BuyerDTO> buyerList = new ArrayList<>();

        for (Buyer buyer : buyers){
            BuyerDTO buyerDTO = new BuyerDTO(buyer.getId(), buyer.getTel(), buyer.getAddress(), buyer.getEmail(), buyer.getCompany());
            buyerList.add(buyerDTO);
        }
        return buyerList;
    }

    @Override
    public BuyerDTO search(String id) throws SQLException, ClassNotFoundException {
        Buyer buyer=  buyerDAO.search(id);
        return new BuyerDTO(buyer.getId(), buyer.getTel(), buyer.getEmail(), buyer.getAddress(), buyer.getCompany());
    }


}
