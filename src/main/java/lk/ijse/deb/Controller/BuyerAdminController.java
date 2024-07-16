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
import lk.ijse.deb.DTO.BuyerDTO;
import lk.ijse.deb.bo.BOFactory;
import lk.ijse.deb.bo.custom.BuyerBO;
import lk.ijse.deb.entity.Buyer;

import lk.ijse.deb.util.Regex;
import lk.ijse.deb.util.TextFields;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BuyerAdminController {
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCompanyName;

    @FXML
    private TableColumn<?, ?> colContacNumber;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Buyer> tblBuyer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCompanyName;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private Text txtWelcome;
    @FXML
    private TextField searchId;


BuyerBO buyerBO= (BuyerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BUYER);
    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();

        boolean isDeleted = buyerBO.delete(id);
        if(isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "buyer deleted!").show();
            loadAllBuyer();
            clearFields();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event)  {
        String id = txtId.getText().trim(); // Trim to remove unnecessary spaces
        String tel = txtContactNumber.getText().trim();
        String address = txtAddress.getText().trim();
        String email = txtEmail.getText().trim();
        String company = txtCompanyName.getText().trim();

        if (isValied()) {
            boolean isSaved = false;
            try {
                isSaved =buyerBO.save( new BuyerDTO(id, tel,address,email,company));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                clearFields();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Check your fields correctly !").show();
        }
        loadAllBuyer();
    }






    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtId.getText().trim(); // Trim to remove unnecessary spaces
        String tel = txtContactNumber.getText().trim();
        String address = txtAddress.getText().trim();
        String email = txtEmail.getText().trim();
        String company = txtCompanyName.getText().trim();

        // Check if any required field is empty
        if (id.isEmpty() || tel.isEmpty() || address.isEmpty() || email.isEmpty() || company.isEmpty()) {
            // Display an alert if any field is empty
            new Alert(Alert.AlertType.WARNING, "All fields are required. Please ensure no fields are empty.").show();
        } else {
            // Proceed with creating the buyer object and updating it
            BuyerDTO buyer = new BuyerDTO(id, tel, address, email, company);

            boolean isUpdated = buyerBO.update(buyer);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer updated successfully!").show();
                loadAllBuyer();
                clearFields();
            } else {
                // This alert can be used to notify that updating was unsuccessful
                new Alert(Alert.AlertType.ERROR, "Failed to update the buyer. Please check the data.").show();
            }
        }
    }



    public void initialize() {
        setCellValueFactory();
        loadAllBuyer();
    }

    private void loadAllBuyer() {
        ObservableList<Buyer> obList = FXCollections.observableArrayList();

        try {
            List<BuyerDTO> buyerList = buyerBO.getAll();
            for (BuyerDTO buyer :buyerList) {
                Buyer tm = new Buyer(
                        buyer.getId(),
                        buyer.getTel(),
                        buyer.getAddress(),
                        buyer.getEmail(),
                        buyer.getCompany()
                );

                obList.add(tm);
            }

            tblBuyer.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colContacNumber.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCompanyName.setCellValueFactory(new PropertyValueFactory<>("company"));
    }


    public void BuyerIDOnKeyRelesed(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.IDB,txtId);
    }

    public void BuyerContactOnKeyRelesed(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.CONTACT,txtContactNumber);

    }

    public void BuyerAddressOnKeyRelesed(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.ADDRESS,txtAddress);
    }

    public void BuyerCompanyNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.ADDRESS,txtCompanyName);
    }

    public void BuyerEmailOnKeyEmail(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.EMAIL,txtEmail);
    }

    public boolean isValied(){
        if (!Regex.setTextColor(TextFields.ADDRESS,txtCompanyName)) return false;
        if (!Regex.setTextColor(TextFields.IDB,txtId)) return false;
        if (!Regex.setTextColor(TextFields.EMAIL,txtEmail)) return false;
        if (!Regex.setTextColor(TextFields.ADDRESS,txtAddress)) return false;
        if (!Regex.setTextColor(TextFields.CONTACT,txtContactNumber)) return false;




        return true;


    }

    public void DashBoardDashBoardOnClick(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashBoardPage.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    public void btnDashBoardPaymentOrderOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/payment.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("payment Form");
    }



    public void btnDashBoadSupplierOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/supplierPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("supplier Form");
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

    public void btnDashBoardEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/employeePage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("employee Form");
    }

    public void btnDashBoardOrderOnAction(ActionEvent actionEvent) throws IOException {

        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/adminPlaseOrder.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Order Form");
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
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtId.setText("");
        txtContactNumber.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtCompanyName.setText("");

    }

    public void buyerIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id =txtId.getText();
        BuyerDTO buyerDTO = buyerBO.search(id);
        if(buyerDTO != null){
            txtId.setText(buyerDTO.getId());
         txtAddress.setText(buyerDTO.getAddress());
txtContactNumber.setText(buyerDTO.getTel());


txtEmail.setText(buyerDTO.getEmail());
txtCompanyName.setText(buyerDTO.getCompany());

        }
        else {
            new Alert(Alert.AlertType.ERROR,"Buyer is not found!").show();
        }
    }
}









