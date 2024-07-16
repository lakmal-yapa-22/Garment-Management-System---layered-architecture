package lk.ijse.deb.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.deb.DTO.ProductDTO;
import lk.ijse.deb.bo.BOFactory;
import lk.ijse.deb.bo.custom.ProductBO;
import lk.ijse.deb.entity.Product;

import lk.ijse.deb.view.tm.ProductTm;
import lk.ijse.deb.util.Regex;
import lk.ijse.deb.util.TextFields;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductFormController {

    @FXML
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> colProductPrice;

    @FXML
    private TableColumn<?, ?> colProductQty;

    @FXML
    private TableColumn<?, ?> colProductType;
    @FXML
    private TableColumn<?, ?> colColor;

    @FXML
    private TableColumn<?, ?> colSize;
    @FXML
    private TextField txtProductSize;

    @FXML
    private TextField txtProductColor;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Product> tblProduct;

    @FXML
    private TextField txtProducPrice;

    @FXML
    private TextField txtProductId;

    @FXML
    private TextField txtProductQty;

    @FXML
    private TextField txtProductType;

    @FXML
    private Text txtWelcome;

    @FXML
    private TextField searchId;
    ProductBO productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRODUCT);
    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashBoardPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");

    }

    @FXML
    void btnDashBoardEmployeeOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/employeePage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("buyer Form");

    }

    @FXML
    void btnDashBoardOrderOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/adminPlaseOrder.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Order Form");

    }


    public void btnDashBoardRowMatirialOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/rowMatirialPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("row matirial Form");
    }

    public void btnDashBoardBuyerOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/buyerPageAdmin.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Buyer Form");
    }

    public void btnDashBoadSupplierOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/supplierPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("supplier Form");
    }
    public void btnDashBoardPaymentOrderOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/payment.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("payment Form");
    }

    public void DashBoardDashBoardOnClick(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashBoardPage.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtProductId.setText("");
        txtProductQty.setText("");
        txtProducPrice.setText("");
        txtProductType.setText("");
        txtProductColor.setText("");

        txtProductSize.setText("");
    }



    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtProductId.getText();

        boolean isDeleted = productBO.delete(id);
        if(isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "product deleted!").show();
            clearFields();
            loadAllProduct();
        }
    }
    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtProductId.getText();
        int qty= Integer.parseInt(txtProductQty.getText());
     double price= Double.parseDouble(txtProducPrice.getText());

         String type=txtProductType.getText();
         String color=txtProductColor.getText();
         String size =txtProductSize.getText();


        if (isValied()) {
            boolean isSaved = false;
            isSaved = productBO.save( new ProductDTO(id, qty,price,type,color,size));

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                clearFields();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Check your fields correctly !").show();
        }
        loadAllProduct();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtProductId.getText().trim();  // Retrieves the product ID and trims whitespace
        int qty = 0;  // Initialize quantity
        double price = 0.0;  // Initialize price
        String type = txtProductType.getText().trim();  // Retrieves the product type and trims whitespace
        String color=txtProductColor.getText().trim();
        String size =txtProductSize.getText().trim();
        // Check for empty strings in critical fields
        if (id.isEmpty() || type.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Product ID and Type cannot be empty.").show();
            return;  // Exit the method if any critical field is empty
        }

        // Try to parse quantity and price
        try {
            qty = Integer.parseInt(txtProductQty.getText().trim());
            price = Double.parseDouble(txtProducPrice.getText().trim());
        } catch (NumberFormatException e) {
            // Log the parsing error and continue with default values of 0, since they are acceptable
            System.out.println("Error parsing number: " + e.getMessage());
            new Alert(Alert.AlertType.ERROR, "Invalid numeric values entered. Please enter valid numbers.").show();
            return;  // Exit if there's a parsing error to avoid further complications
        }

        // Create the product model with the obtained values
        ProductDTO product = new ProductDTO(id, qty, price, type,color,size);

        // Attempt to update the product in the database
        boolean isUpdated = productBO.update(product);
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "Product successfully updated!").show();
            clearFields();
            loadAllProduct();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update product. No changes detected or connection issue.").show();
            clearFields();
        }
    }




    public void initialize() {
        setCellValueFactory();
        loadAllProduct();
    }

    private void loadAllProduct() {

        ObservableList<Product> obList = FXCollections.observableArrayList();

        try {
            List<ProductDTO> productList = productBO.getAllProductsByType("h");
            for (ProductDTO product : productList) {
                ProductTm tm = new ProductTm(
                        product.getId(),
                        product.getQty(),
                        product.getPrice(),
                        product.getType(),
                        product.getColor(),
                        product.getSize()
                );

                obList.add(tm);
            }

            tblProduct.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProductQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colProductType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));


    }


    public void ProductIdOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.IDP,txtProductId);
    }

    public void ProductTypeOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PType,txtProductType);
    }

    public void ProductPriceOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DOUBLE,txtProducPrice);
    }

    public void ProductQtyOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.SALARY,txtProductQty);
    }
    public boolean isValied(){
        if (!Regex.setTextColor(TextFields.IDP,txtProductId)) return false;
        if (!Regex.setTextColor(TextFields.PType,txtProductType)) return false;
        if (!Regex.setTextColor(TextFields.DOUBLE,txtProducPrice)) return false;
        if (!Regex.setTextColor(TextFields.SALARY,txtProductQty)) return false;
        if (!Regex.setTextColor(TextFields.PColor,txtProductColor)) return false;
        if (!Regex.setTextColor(TextFields.PSize,txtProductSize)) return false;




        return true;


    }



    public void ProductColorOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PColor,txtProductColor);
    }

    public void ProductSizeOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PSize,txtProductSize);
    }


    public void productIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id =txtProductId.getText();
        ProductDTO productDTO = productBO.search(id);
        if(productDTO != null){
           txtProductId.setText(productDTO.getId());
            txtProductQty.setText(String.valueOf(productDTO.getQty()));
            txtProducPrice.setText(String.valueOf(productDTO.getPrice()));
           txtProductType.setText(productDTO.getType());
           txtProductColor.setText(productDTO.getColor());
            txtProductSize.setText(productDTO.getSize());



        }
        else {
            new Alert(Alert.AlertType.ERROR,"product is not found!").show();
        }
    }

}

