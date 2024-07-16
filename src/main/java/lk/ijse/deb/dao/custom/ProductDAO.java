package lk.ijse.deb.dao.custom;

import lk.ijse.deb.DTO.ProductDTO;
import lk.ijse.deb.dao.CrudDAO;
import lk.ijse.deb.dao.SQLUtil;
import lk.ijse.deb.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProductDAO extends CrudDAO<Product> {

    public Product searchId(String code) throws SQLException, ClassNotFoundException ;


    public boolean delete(String id) throws SQLException, ClassNotFoundException ;

    public boolean save(Product product) throws SQLException, ClassNotFoundException ;

    public boolean update(Product product) throws SQLException, ClassNotFoundException ;

    public Product search(String id) throws SQLException, ClassNotFoundException ;

    public List<Product> getAll() throws SQLException, ClassNotFoundException;

    public List<Product> getAllFrock() throws SQLException, ClassNotFoundException ;

    public List<Product> getAllShirt() throws SQLException, ClassNotFoundException ;

    public List<Product> getAllShort() throws SQLException, ClassNotFoundException;
    public List<Product> getAllTrouser() throws SQLException, ClassNotFoundException;
    public List<Product> getAllTShirt() throws SQLException, ClassNotFoundException ;

    public List<Product> getAllByType(String... types) throws SQLException, ClassNotFoundException ;
    public List<String> getColor() throws SQLException, ClassNotFoundException;

    public List<String> getSize() throws SQLException, ClassNotFoundException ;

    public List<String> getDescription() throws SQLException, ClassNotFoundException ;

    public ProductDTO searchComboBox(String description, String color, String size) ;

    public  boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException;
}
