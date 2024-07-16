package lk.ijse.deb.bo.custom.impl;

import lk.ijse.deb.DTO.*;
import lk.ijse.deb.bo.custom.PlaseOrderBO;
import lk.ijse.deb.dao.DAOFactory;
import lk.ijse.deb.dao.custom.BuyerDAO;
import lk.ijse.deb.dao.custom.OrderDAO;
import lk.ijse.deb.dao.custom.OrderDetailDAO;
import lk.ijse.deb.dao.custom.ProductDAO;
import lk.ijse.deb.dao.custom.impl.BuyerDAOImpl;
import lk.ijse.deb.dao.custom.impl.OrderDAOImpl;
import lk.ijse.deb.dao.custom.impl.OrderDetailDAOImpl;
import lk.ijse.deb.dao.custom.impl.ProductDAOImpl;
import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.entity.Buyer;
import lk.ijse.deb.entity.Order;
import lk.ijse.deb.entity.OrderDetail;
import lk.ijse.deb.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PlaseOrderBOImpl implements PlaseOrderBO {
    BuyerDAO buyerDAO = (BuyerDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BUYER);
    ProductDAO productDAO = (ProductDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PRODUCT);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDDER);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);

    public BuyerDTO search(String id) throws SQLException, ClassNotFoundException {
        Buyer buyer = buyerDAO.search(id);
        return new BuyerDTO(buyer.getId(), buyer.getTel(), buyer.getEmail(), buyer.getAddress(), buyer.getCompany());
    }

    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return buyerDAO.getId();
    }

    public List<String> getColor() throws SQLException, ClassNotFoundException {
        return productDAO.getColor();
    }

    public List<String> getSize() throws SQLException, ClassNotFoundException {
        return productDAO.getSize();
    }

    public List<String> getDescription() throws SQLException, ClassNotFoundException {
        return productDAO.getDescription();
    }

    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return orderDAO.getCurrentID();
    }

    public ProductDTO searchByCombo(String description, String color, String size) throws SQLException, ClassNotFoundException {

        ProductDTO productDTO = productDAO.searchComboBox(description, color, size);

        Product p = new Product(productDTO.getId(), productDTO.getQty(), productDTO.getType(), productDTO.getColor(), productDTO.getSize());
        System.out.println(productDTO.getId());
        return productDTO;
    }


    public boolean placeOrder(OrderDTO orderDTO, List<OrderDetailDTO> odList) throws SQLException {
        Connection connection = null;
         connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved = orderDAO.save(orderDTO);


                if (!isOrderSaved) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
                boolean isQtyUpdated = updateProduct(odList);

                if (isQtyUpdated) {
                    //  System.out.println(isQtyUpdated);
                    boolean isOrderDetailSaved =saveOrderDetai(odList);
                    //   System.out.println(isOrderDetailSaved+"4");
                    if (isOrderDetailSaved) {
                        connection.commit();
                        return true;
                    }
                }


        connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public boolean saveOrderDetai(List<OrderDetailDTO> odList) throws SQLException, ClassNotFoundException {
        for (OrderDetailDTO od : odList) {
            boolean isSaved = orderDetailDAO.save(od);
            //  System.out.println(isSaved+"isSave");
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }

    public boolean updateProduct(List<OrderDetailDTO> odList) throws SQLException, ClassNotFoundException {
        for (OrderDetailDTO od : odList) {

            boolean isUpdateQty = productDAO.updateQty(od.getItemCode(), od.getQty());

            if(!isUpdateQty) {
                return false;
            }
        }
        return true;
    }


}