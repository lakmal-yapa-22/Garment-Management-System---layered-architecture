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
import lk.ijse.deb.DTO.SupplierDTO;
import lk.ijse.deb.bo.BOFactory;
import lk.ijse.deb.bo.custom.SupplierBO;
import lk.ijse.deb.entity.Supplier;

import lk.ijse.deb.view.tm.SupplierTm;
import lk.ijse.deb.util.Regex;
import lk.ijse.deb.util.TextFields;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SupplierFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Supplier> tblSupplier;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private Text txtWelcome;

    @FXML
    private TextField searchId;
    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);
    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {

        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashBoardPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    public void btnDashBoardEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/employeePage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
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
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("product Form");
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
        txtId.setText("");
        txtName.setText("");
        txtContactNumber.setText("");
        txtEmail.setText("");
        txtAddress.setText("");



    }




    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDeleted = supplierBO.delete(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier deleted!").show();
                clearFields();
                loadAllSupplier();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String contactNumber = txtContactNumber.getText().trim();
        String email = txtEmail.getText().trim();
        String address = txtAddress.getText().trim();

        if (isValied()) {
            boolean isSaved = false;
            isSaved = supplierBO.save( new SupplierDTO(id, name, contactNumber,email,address));

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                clearFields();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Check your fields correctly !").show();
        }
        loadAllSupplier();
    }





    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String contactNumber = txtContactNumber.getText().trim();
        String email = txtEmail.getText().trim();
        String address = txtAddress.getText().trim();

        // Check for empty fields before proceeding with the update
        if (id.isEmpty() || name.isEmpty() || contactNumber.isEmpty() || email.isEmpty() || address.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "All fields must be filled. Please check your inputs.").show();
        } else {
            SupplierDTO supplier = new SupplierDTO(id, name, contactNumber, email, address);

            boolean isUpdated = supplierBO.update(supplier);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier updated successfully!").show();
                clearFields();
                loadAllSupplier();
            } else {
                new Alert(Alert.AlertType.WARNING, "No changes were made to the supplier.").show();
            }
        }
    }

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        loadAllSupplier();
    }

    private void loadAllSupplier() throws SQLException, ClassNotFoundException {
        ObservableList<Supplier> obList = FXCollections.observableArrayList();

        List<SupplierDTO> supList = supplierBO.getAll();
        for (SupplierDTO supplier : supList) {
            SupplierTm tm = new SupplierTm(
                    supplier.getId(),
                    supplier.getName(),
                    supplier.getContactNumber(),
                    supplier.getEmail(),
                    supplier.getAddress());

            obList.add(tm);
        }

        tblSupplier.setItems(obList);
    }


    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));


    }


    public void SupEmailOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.EMAIL,txtEmail);
    }

    public void SupContactOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.CONTACT,txtContactNumber);
    }

    public void SupNameKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtName);
    }

    public void SupAddressOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.ADDRESS,txtAddress);
    }

    public void SupIdOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.IDS,txtId);
    }
    public boolean isValied(){
        if (!Regex.setTextColor(TextFields.IDS,txtId)) return false;
        if (!Regex.setTextColor(TextFields.NAME,txtName)) return false;
        if (!Regex.setTextColor(TextFields.CONTACT,txtContactNumber)) return false;
        if (!Regex.setTextColor(TextFields.EMAIL,txtEmail)) return false;
        if (!Regex.setTextColor(TextFields.ADDRESS,txtAddress)) return false;



        return true;

    }


    public void supplierIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id =txtId.getText();
        SupplierDTO supplierDTO = supplierBO.search(id);
        if(supplierDTO != null){
            txtId.setText(supplierDTO.getId());
            txtName.setText(supplierDTO.getName());
            txtContactNumber.setText(supplierDTO.getContactNumber());
            txtEmail.setText(supplierDTO.getEmail());
            txtAddress.setText(supplierDTO.getAddress());



        }
        else {
            new Alert(Alert.AlertType.ERROR,"Supplier is not found!").show();
        }
    }
}
