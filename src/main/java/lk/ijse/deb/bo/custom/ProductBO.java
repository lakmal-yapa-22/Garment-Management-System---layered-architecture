package lk.ijse.deb.bo.custom;

import lk.ijse.deb.DTO.ProductDTO;
import lk.ijse.deb.bo.SuperBO;
import lk.ijse.deb.entity.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public interface ProductBO  extends SuperBO {
    public boolean delete(String id) throws SQLException, ClassNotFoundException ;
    public boolean save(ProductDTO productDTO) throws SQLException, ClassNotFoundException ;
    public boolean update(ProductDTO productDTO) throws SQLException, ClassNotFoundException ;
    public Product search(String id) throws SQLException, ClassNotFoundException ;
    public List<ProductDTO> getAllProductsByType(String type) throws SQLException, ClassNotFoundException ;

}
