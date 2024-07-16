package lk.ijse.deb.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.deb.DTO.ProductDTO;
import lk.ijse.deb.dao.custom.impl.ProductDAOImpl;
import lk.ijse.deb.entity.Product;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ShirtController {



    @FXML
    private TableColumn<?, ?> colColor;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Product> tblT_shirt;

    @FXML
    private TableColumn<?, ?> colType;




    @FXML
    void nextPageOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Buyer/short.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("trouser Form");


    }
    public void initialize() {
        setCellValueFactory();
        loadAllProductShirt_longShirt();
    }

    private void  loadAllProductShirt_longShirt() {
        ObservableList<Product> obList = FXCollections.observableArrayList();

        try {
            ProductDAOImpl productDAO = new ProductDAOImpl();
            List<Product> productList = productDAO.getAllShirt();
            for (ProductDTO product : productList) {
                Product tm = new Product(
                    product.getId(),
                        product.getQty(),
                        product.getPrice(),
                        product.getType(),
                        product.getColor(),
                        product.getSize()
                );

                obList.add(tm);
            }

            tblT_shirt.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {

        colProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));


    }

    public void BacknextPageOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Buyer/t_shirt.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("supplier Form");
    }



    public void backOnClick(MouseEvent mouseEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Buyer/buyerDashBoard.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("dashBoard Form");
    }
}





