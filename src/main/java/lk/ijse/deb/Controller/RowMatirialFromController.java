package lk.ijse.deb.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.deb.DTO.RowMatirialDTO;
import lk.ijse.deb.bo.BOFactory;
import lk.ijse.deb.bo.custom.RowMatirialBO;
import lk.ijse.deb.entity.RowMatirial;

import lk.ijse.deb.view.tm.RowMatirialTM;
import lk.ijse.deb.util.Regex;
import lk.ijse.deb.util.TextFields;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RowMatirialFromController {


    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colInvoiceNumber;

    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<RowMatirial> tblRowMatiriall;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtInvoiceNumber;

    @FXML
    private TextField txtLocation;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField searchId;


    RowMatirialBO rowMatirialBO= (RowMatirialBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROWMATIRIAL);

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtInvoiceNumber.setText("");
        txtDescription.setText("");
        txtLocation.setText("");
        txtQty.setText("");
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtInvoiceNumber.getText();

        try {

            boolean isDeleted = rowMatirialBO.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Row matirial deleted!").show();
                loadAllRowMatirial();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtInvoiceNumber.getText();
        String location = txtLocation.getText();
        String description = txtDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());
        if (isValied()) {
            boolean isSaved = false;
            try {
                isSaved = rowMatirialBO.save(new RowMatirialDTO(id, location, description, qty));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                clearFields();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Check your fields correctly !").show();
        }
        loadAllRowMatirial();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtInvoiceNumber.getText().trim(); // Retrieves and trims the text from txtInvoiceNumber field
        String location = txtLocation.getText().trim(); // Retrieves and trims the text from txtLocation field
        String description = txtDescription.getText().trim(); // Retrieves and trims the text from txtDescription field

        // Initial validation to ensure no fields are empty
        if (id.isEmpty() || location.isEmpty() || description.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all fields. No field can be left blank.").show();
            return; // Stop further processing
        }

        // Attempt to parse the quantity as an integer from txtQty field
        int qty = 0;
        try {
            qty = Integer.parseInt(txtQty.getText().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for quantity. Please enter a valid number.");
            new Alert(Alert.AlertType.ERROR, "Invalid input for quantity. Please enter a valid number.").show();
            return; // Stop further processing if quantity is invalid
        }

        // Now you can use id, location, description, and qty in your application
        RowMatirialDTO rowMatirial = new RowMatirialDTO(id, location, description, qty);

        try {
            boolean isUpdated = rowMatirialBO.update(rowMatirial);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Row material updated successfully!").show();
                clearFields();
                loadAllRowMatirial();
            } else {
                new Alert(Alert.AlertType.ERROR, "Update failed. No changes were made.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
//metana layer karnn

    @FXML
    void invoiceNumberSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtInvoiceNumber.getText();

        RowMatirial rowMatirial = rowMatirialBO.searchById(id);
        if (rowMatirial != null) {
            txtInvoiceNumber.setText(rowMatirial.getInvoiceNumber());
            txtLocation.setText(rowMatirial.getLocation());
            txtDescription.setText(rowMatirial.getDescription());
            txtQty.setText(String.valueOf(rowMatirial.getQty()));

        } else {
            new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
        }
    }

    public void initialize() {
        setCellValueFactory();
        loadAllRowMatirial();
    }

    private void loadAllRowMatirial() {
        ObservableList<RowMatirial> obList = FXCollections.observableArrayList();

        try {
            List<RowMatirialDTO> rowMatirialList = rowMatirialBO.getAll();
            for (RowMatirialDTO rowMatirial : rowMatirialList) {
                RowMatirialTM tm = new RowMatirialTM(
                        rowMatirial.getInvoiceNumber(),
                        rowMatirial.getLocation(),
                        rowMatirial.getDescription(),
                        rowMatirial.getQty()
                );

                obList.add(tm);
                //   System.out.println(tm);
            }

            tblRowMatiriall.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colInvoiceNumber.setCellValueFactory(new PropertyValueFactory<>("invoiceNumber"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));


    }


    public void btnDashBoardEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/employeePage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("buyer Form");
    }


    public void btnDashBoardOrderOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/adminPlaseOrder.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Order Form");
    }



    public void btnDashBoardProductOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/productPagee.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("product Form");
    }

    public void btnDashBoardBuyerOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/buyerPageAdmin.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Buyer Form");
    }

    public void DashBoardDashBoardOnClick(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashBoardPage.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    public void btnDashBoardEmpoyeeeOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/employeePage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("buyer Form");

    }

    public void btnDashBoardPaymentOrderOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/payment.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("payment Form");
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/loginpage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Form");
    }

    public void btnDashBoadSupplierOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/supplierPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("supplier Form");
    }
    public void RowMatirialInvoiceOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.IDR, txtInvoiceNumber);
    }

    public void RowMatirialLoctionOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.ADDRESS, txtLocation);
    }

    public void RowMatirialQtyOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.SALARY, txtQty);
    }

    public void RowMatirialDescriptionOnKeyRelesed(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.ADDRESS, txtDescription);
    }

    public boolean isValied() {
        if (!Regex.setTextColor(TextFields.IDR, txtInvoiceNumber)) return false;
        if (!Regex.setTextColor(TextFields.ADDRESS, txtLocation)) return false;
        if (!Regex.setTextColor(TextFields.SALARY, txtQty)) return false;
        if (!Regex.setTextColor(TextFields.ADDRESS, txtDescription)) return false;


        return true;


    }



}


