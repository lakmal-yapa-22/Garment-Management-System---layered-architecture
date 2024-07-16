package lk.ijse.deb.bo.custom;

import lk.ijse.deb.DTO.BuyerDTO;
import lk.ijse.deb.DTO.OrderDTO;
import lk.ijse.deb.DTO.OrderDetailDTO;
import lk.ijse.deb.DTO.ProductDTO;
import lk.ijse.deb.bo.SuperBO;
import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.entity.Buyer;
import lk.ijse.deb.entity.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PlaseOrderBO extends SuperBO {

    public BuyerDTO search(String id) throws SQLException, ClassNotFoundException ;
    public List<String> getIds() throws SQLException, ClassNotFoundException ;

    public List<String> getColor() throws SQLException, ClassNotFoundException;

    public List<String> getSize() throws SQLException, ClassNotFoundException ;
    public List<String> getDescription() throws SQLException, ClassNotFoundException;

    public String getCurrentId() throws SQLException, ClassNotFoundException ;
    public ProductDTO searchByCombo(String description, String color, String size) throws SQLException, ClassNotFoundException;


    public boolean placeOrder(OrderDTO orderDTO, List<OrderDetailDTO> odList) throws SQLException;

    public boolean saveOrderDetai(List<OrderDetailDTO> odList) throws SQLException, ClassNotFoundException ;

    public boolean updateProduct(List<OrderDetailDTO> odList) throws SQLException, ClassNotFoundException ;
}
