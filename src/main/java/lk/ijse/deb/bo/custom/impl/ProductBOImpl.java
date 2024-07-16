package lk.ijse.deb.bo.custom.impl;

import lk.ijse.deb.DTO.ProductDTO;
import lk.ijse.deb.bo.custom.ProductBO;
import lk.ijse.deb.dao.DAOFactory;
import lk.ijse.deb.dao.custom.ProductDAO;
import lk.ijse.deb.dao.custom.impl.ProductDAOImpl;
import lk.ijse.deb.entity.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ProductBOImpl implements ProductBO {
    ProductDAO productDAO = (ProductDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PRODUCT);

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return productDAO.delete(id);


    }

    public boolean save(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        return productDAO.save(new Product(productDTO.getId(), productDTO.getQty(), productDTO.getPrice(), productDTO.getType(), productDTO.getColor(), productDTO.getSize()));
    }

    public boolean update(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        return productDAO.update(new Product(productDTO.getId(), productDTO.getQty(), productDTO.getPrice(), productDTO.getType(), productDTO.getColor(), productDTO.getSize()));
    }

    public Product search(String id) throws SQLException, ClassNotFoundException {
        return productDAO.search(id);
    }

    public List<ProductDTO> getAllProductsByType(String type) throws SQLException, ClassNotFoundException {
        List<Product> products;
        switch (type) {
            case "frock":
                products = productDAO.getAllFrock();
                break;
            case "short":
                products = productDAO.getAllShort();
                break;
            case "trouser":
                products = productDAO.getAllTrouser();
                break;
            case "tshirt":
                products = productDAO.getAllTShirt();
                break;
            default:
                products = productDAO.getAll(); // Default to getAll if type is not recognized
        }

        return products.stream()
                .map(product -> new ProductDTO(
                        product.getId(),
                        product.getQty(),
                        product.getPrice(),
                        product.getType(),
                        product.getColor(),
                        product.getSize()))
                .collect(Collectors.toList());
    }




}
