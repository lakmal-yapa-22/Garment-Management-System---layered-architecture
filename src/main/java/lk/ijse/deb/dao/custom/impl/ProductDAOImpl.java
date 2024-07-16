package lk.ijse.deb.dao.custom.impl;

import lk.ijse.deb.DTO.OrderDetailDTO;
import lk.ijse.deb.DTO.ProductDTO;
import lk.ijse.deb.dao.SQLUtil;
import lk.ijse.deb.dao.custom.ProductDAO;
import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    public Product searchId(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Product WHERE product_id = ?", code);

        if (rst.next()) {
            // The result set is not empty, retrieve data
            return new Product(
                    rst.getString("product_id"),
                    rst.getInt("qty"),
                    rst.getDouble("price"),
                    rst.getString("product_type"),
                    rst.getString("color"),
                    rst.getString("size")
            );
        } else {
            // Handle the case where no results were found
            return null; // Or throw an exception or return a default Product object
        }
    }


    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Product WHERE product_id  = ?", id);

    }

    public boolean save(Product product) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Product VALUES(?, ?, ?, ?,?,?)", product.getId(), product.getQty(), product.getPrice(), product.getType(), product.getColor(), product.getSize());
    }

    public boolean update(Product product) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Product SET qty  = ?, price   = ?,product_type  = ?, color =?,size=? WHERE  product_id   = ?", product.getQty(), product.getPrice(), product.getType(), product.getColor(), product.getType(), product.getId());
    }

    public Product search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Product WHERE product_id = ?", id);
        rst.next();
        return new Product(rst.getString("product_id"), rst.getInt("qty"), rst.getDouble("price"), rst.getString("product_type"), rst.getString("color"), rst.getString("size"));
    }

    public List<Product> getAll() throws SQLException, ClassNotFoundException {
        return getAllByType();
    }

    public List<Product> getAllFrock() throws SQLException, ClassNotFoundException {
        return getAllByType("frock");
    }

    public List<Product> getAllShirt() throws SQLException, ClassNotFoundException {
        return getAllByType("shirt", "long_sleeve_shirt");
    }

    public List<Product> getAllShort() throws SQLException, ClassNotFoundException {
        return getAllByType("pant");
    }

    public List<Product> getAllTrouser() throws SQLException, ClassNotFoundException {
        return getAllByType("trouser");
    }

    public List<Product> getAllTShirt() throws SQLException, ClassNotFoundException {
        return getAllByType("t-shirt", "long_sleeve_t_shirt");
    }

    public List<Product> getAllByType(String... types) throws SQLException, ClassNotFoundException {
        List<Product> allProducts = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Product");
        if (types.length > 0) {
            queryBuilder.append(" WHERE product_type IN (");
            for (int i = 0; i < types.length; i++) {
                queryBuilder.append("'").append(types[i]).append("'");
                if (i < types.length - 1) {
                    queryBuilder.append(", ");
                }
            }
            queryBuilder.append(")");
        }

        ResultSet rst = SQLUtil.execute(queryBuilder.toString());
        while (rst.next()) {
            Product product = new Product(
                    rst.getString("product_id"),
                    rst.getInt("qty"),
                    rst.getDouble("price"),
                    rst.getString("product_type"),
                    rst.getString("color"),
                    rst.getString("size")
            );
            allProducts.add(product);
        }
        return allProducts;
    }

    public List<String> getColor() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT DISTINCT color\n" +
                "FROM product;");

        List<String> codeList = new ArrayList<>();
        while (resultSet.next()) {
            codeList.add(resultSet.getString(1));
        }
        return codeList;
    }

    public List<String> getSize() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT DISTINCT size\n" +
                "FROM product;");

        List<String> codeList = new ArrayList<>();
        while (resultSet.next()) {
            codeList.add(resultSet.getString(1));
        }
        return codeList;
    }

    public List<String> getDescription() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT DISTINCT product_type   FROM Product ");

        List<String> codeList = new ArrayList<>();
        while (resultSet.next()) {
            codeList.add(resultSet.getString(1));
        }
        return codeList;
    }

    public ProductDTO searchComboBox(String description, String color, String size) {

        ResultSet rst = null;
        try {
            rst = SQLUtil.execute("SELECT * FROM Product WHERE product_type = ? AND color = ? AND size = ?", description, color, size);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Check if the ResultSet has any rows
        try {
            if (rst.next()) {
                // ResultSet is not empty, retrieve data
                ProductDTO p = new ProductDTO(
                        rst.getString("product_id"),
                        rst.getInt("qty"),
                        rst.getDouble("price"),
                        rst.getString("product_type"),
                        rst.getString("color"),
                        rst.getString("size")
                );
                return p;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    public boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {

return SQLUtil.execute("UPDATE Product SET qty = qty - ? WHERE product_id  = ?",qty,itemCode);

    }
}


