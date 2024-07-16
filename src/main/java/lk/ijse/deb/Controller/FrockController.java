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
import lk.ijse.deb.bo.custom.impl.ProductBOImpl;
import lk.ijse.deb.entity.Product;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FrockController {



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
    private TableColumn<?, ?> colType;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Product> tblT_shirt;





    public void initialize() {
        setCellValueFactory();
        loadAllProductTrouser() ;
    }

    private void  loadAllProductTrouser() {
        ObservableList<Product> obList = FXCollections.observableArrayList();

        try {
            ProductBOImpl productBO = new ProductBOImpl();
            List<ProductDTO> productList = productBO.getAllProductsByType("frock");
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
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Buyer/trousers.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("trouser");
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
