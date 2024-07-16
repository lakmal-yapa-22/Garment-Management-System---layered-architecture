package lk.ijse.deb.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.deb.DTO.*;

import lk.ijse.deb.bo.BOFactory;
import lk.ijse.deb.bo.custom.PlaseOrderBO;
import lk.ijse.deb.bo.custom.impl.PlaseOrderBOImpl;
import lk.ijse.deb.view.tm.CartTm;
import lk.ijse.deb.util.Regex;
import lk.ijse.deb.util.TextFields;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class PlaseOrderController {

    @FXML
    private JFXComboBox<String> cmbBuyerId;

    @FXML
    private JFXComboBox<String> cmbProductCode;

    @FXML
    private TableColumn<CartTm, String> colAction;

    @FXML
    private TableColumn<CartTm, String> colDescription;

    @FXML
    private TableColumn<CartTm, Integer> colDiscountRate;

    @FXML
    private TableColumn<CartTm, Double> colDisscount;

    @FXML
    private TableColumn<CartTm, Double> colFinalAmount;

    @FXML
    private TableColumn<CartTm, String> colItemCode;

    @FXML
    private TableColumn<CartTm, Integer> colQty;

    @FXML
    private TableColumn<CartTm, Double> colTotal;

    @FXML
    private TableColumn<CartTm, Double> colUnitPrice;

    @FXML
    private Label lblCompanyName;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblOrderTime;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private AnchorPane root;

    @FXML
    private Label lblEmail;

    @FXML
    private TableView<CartTm> tblOrderCart;

    @FXML
    private TextField txtQty;

    @FXML
    private Label lblColor;

    @FXML
    private Label lblSize;

    @FXML
    private Text txtWelcome;

    @FXML
    private Label lblDisscount;
    @FXML
    private JFXComboBox<String> cmbColor;

    @FXML
    private JFXComboBox<String> cmbDescription;

    @FXML
    private JFXComboBox<String> cmbSize;

    @FXML
    private Label lblProductID;

    private ObservableList<CartTm> obList = FXCollections.observableArrayList();
    private Random random = new Random();
    private int discountRate = random.nextInt(5) + 1;
    private double total;
    private double disscount;
    private double totalAmount;
    private int rate = discountRate;
PlaseOrderBO plaseOrderBO= (PlaseOrderBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLASEORDER);



    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String code = lblProductID.getText();
        String description = cmbDescription.getValue();
        int qty = Integer.parseInt(txtQty.getText());
        int qtyOnHand = Integer.parseInt(lblQtyOnHand.getText());

        if (qty > qtyOnHand) {
            clearOrderDetail();
            new Alert(Alert.AlertType.ERROR, "Not enough quantity available").show();

            return;
        }

        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        total = qty * unitPrice;
        disscount = total * discountRate / 100;
        totalAmount = total - disscount;

        JFXButton btnRemove = new JFXButton("remove");
        btnRemove.setCursor(Cursor.HAND);
        btnRemove.setOnAction(e -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();
            if (type.orElse(no) == yes) {
                int selectedIndex = tblOrderCart.getSelectionModel().getSelectedIndex();
                obList.remove(selectedIndex);
                tblOrderCart.refresh();
                calculateNetTotal();
                clearOrderDetail();
            }
        });

        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            if (code.equals(colItemCode.getCellData(i))) {
                CartTm tm = obList.get(i);
                qty += tm.getQty();
                total = qty * unitPrice;
                disscount = total * discountRate / 100;
                totalAmount = total - disscount;

                tm.setQty(qty);
                tm.setSubTotal(total);
                tm.setDiscountRate(discountRate);
                tm.setDisscount(disscount);
                tm.setTotalAmount(totalAmount);

                tblOrderCart.refresh();
                calculateNetTotal();
                return;
            }
        }

        CartTm tm = new CartTm(code, description, qty, unitPrice, total, discountRate, disscount, totalAmount, btnRemove);
        obList.add(tm);
        new Alert(Alert.AlertType.CONFIRMATION, "Congratulations! You have " + discountRate + "% discount").show();
        tblOrderCart.setItems(obList);
        calculateNetTotal();
       clearOrderDetail();
    }

    private void calculateNetTotal() {
        double netTotal = 0;
        for (CartTm item : tblOrderCart.getItems()) {
            netTotal += item.getTotalAmount();
        }
        lblNetTotal.setText(String.valueOf(netTotal));
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/loginpage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Form");
    }

    @FXML
    void btnNewBuyerOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/buyerPageAdmin.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Buyer Form");
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String orderId = lblOrderId.getText();
        String buyerId = cmbBuyerId.getValue();
        Date date = Date.valueOf(LocalDate.now());
        Time time = Time.valueOf(LocalTime.now());
        double subTotal = total;
        int discountRate = rate;
        double discounts = disscount;
        double finalAmount = totalAmount;

        var order = new OrderDTO(orderId, buyerId, date, time, subTotal, discountRate, discounts, finalAmount);

        List<OrderDetailDTO> odList = new ArrayList<>();
        for (CartTm tm : tblOrderCart.getItems()) {
            OrderDetailDTO od = new OrderDetailDTO(
                    orderId,
                    tm.getCode(),
                    tm.getQty(),
                    tm.getUnitPrice(),
                    tm.getSubTotal(),
                    tm.getDiscountRate(),
                    tm.getDisscount(),
                    tm.getTotalAmount()
            );
            odList.add(od);
        }


        boolean isPlaced =saveOrder(order,odList);
        if (isPlaced) {
            new Alert(Alert.AlertType.CONFIRMATION, "Your order is placed! Thank you for joining Star Garment").show();
            getCurrentOrderId();
            clearOrderDetail();
            tblOrderCart.getItems().clear();
            lblNetTotal.setText("");
            tblOrderCart.refresh();
        } else {
            new Alert(Alert.AlertType.WARNING, "Order placement unsuccessful! Please try again.").show();
            clearOrderDetail();
            lblNetTotal.setText("");
            tblOrderCart.refresh();
        }
    }

    private boolean saveOrder(OrderDTO orderDTO, List<OrderDetailDTO> odList) throws SQLException, ClassNotFoundException {
    return     plaseOrderBO.placeOrder(orderDTO,odList);
    }

    private void clearOrderDetail() {

        lblProductID.setText("");
        lblUnitPrice.setText("");
        lblQtyOnHand.setText("");
        txtQty.setText("");
    }


    @FXML
    void cmbBuyerOnAction(ActionEvent event) {
        String id = cmbBuyerId.getValue();
        try {
           // BuyerDTO buyer = BuyerRepo.searchById(id);
            BuyerDTO buyer = plaseOrderBO.search(id);
            if (buyer != null) {
                lblCompanyName.setText(buyer.getCompany());
                lblEmail.setText(buyer.getEmail());
            } else {
                lblCompanyName.setText("");
                lblEmail.setText("");
                System.out.println("No buyer found with ID: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void txtQtyOnAction(ActionEvent event) {
        // Implement any specific action when qty TextField action is triggered.
    }

    public void initialize() throws SQLException, ClassNotFoundException {
        setDate();
        setTime();
        getCurrentOrderId();
        getBuyerIds();
        getProductDescription();
        getProductSize();
        getProductColor();




        setCellValueFactory();
    }

    private void getProductColor() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> codeList = plaseOrderBO.getColor();
            obList.addAll(codeList);
            cmbColor.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getProductSize() throws SQLException, ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> codeList = plaseOrderBO.getSize();
        obList.addAll(codeList);
        cmbSize.setItems(obList);
    }


    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
        colDiscountRate.setCellValueFactory(new PropertyValueFactory<>("discountRate"));
        colDisscount.setCellValueFactory(new PropertyValueFactory<>("disscount"));
        colFinalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
    }

    private void  getProductDescription() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> codeList = plaseOrderBO.getDescription();
            obList.addAll(codeList);
            cmbDescription.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getBuyerIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = plaseOrderBO.getIds();
            obList.addAll(idList);
            cmbBuyerId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCurrentOrderId() {
        try {
            String currentId = plaseOrderBO.getCurrentId();
            String nextOrderId = generateNextOrderId(currentId);
            lblOrderId.setText(nextOrderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextOrderId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("ORD\\s+"); // Corrected split function
            int idNum = Integer.parseInt(split[1]);
            return "ORD " + ++idNum; // Corrected return statement
        }
        return "ORD 1"; // Corrected return statement
    }

    private void setTime() {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = now.format(formatter);
        lblOrderTime.setText(formattedTime);
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblOrderDate.setText(String.valueOf(now));
    }


    public void PlaseOrderQtyOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.SALARY, txtQty);
    }

    public boolean isValied() {
        return Regex.setTextColor(TextFields.SALARY, txtQty);
    }

    public void btnBackOnclicked(MouseEvent mouseEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/orderPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("order Form");
    }

    public void btnDashBoardPaymentOrderOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/payment.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("payment Form");
    }

    public void btnDashBoardEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/employeePage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("employee Form");
    }

    public void btnDashBoardSupplierOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/supplierPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("supplier Form");
    }

    public void DashBoardDashBoardOnClick(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashBoardPage.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    public void btnDashBoardRowMatirialOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/rowMatirialPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Row matirial Form");
    }

    public void btnDashBoardBuyerOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/buyerPageAdmin.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Buyer Form");
    }

    public void btnDashBoardProductOnAction() throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/productPagee.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("product Form");
    }

    String description;
    String color;
    String size;


    public void cmbDescriptionOnAction(ActionEvent actionEvent) {

        String description1 = cmbDescription.getValue();
        description=description1;
    }


    public void cmbcolorOnAction(ActionEvent actionEvent) {
        String color1 = cmbColor.getValue();
        color=color1;
        System.out.println(description+size+color);
        try {
            ProductDTO product = plaseOrderBO.searchByCombo(description,color,size);
          //
            if (product != null) {

                lblUnitPrice.setText(String.valueOf(product.getPrice()));
                lblQtyOnHand.setText(String.valueOf(product.getQty()));
                lblProductID.setText(product.getId());

            }
            else {
                System.out.println(product);
            }

            txtQty.requestFocus();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void cmbSizeOnAction(ActionEvent actionEvent) {
        size = cmbSize.getValue();
    }





}
