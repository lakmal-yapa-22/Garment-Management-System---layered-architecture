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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.deb.DTO.EmployeeDTO;
import lk.ijse.deb.DTO.ProductDTO;
import lk.ijse.deb.bo.BOFactory;
import lk.ijse.deb.bo.custom.EmployeeBO;
import lk.ijse.deb.entity.Employee;


import lk.ijse.deb.view.tm.EmployeeTm;
import lk.ijse.deb.util.Regex;

import lk.ijse.deb.util.TextFields;

import java.io.IOException;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class EmployeeFormController {

    @FXML
    private Button btnBack;

    @FXML
    private ComboBox<String> cmbProductIdFeeId;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContactNumber;

    @FXML
    private TableColumn<?, ?> colD_O_B;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private Label lblProductName;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Employee> tblEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtD_O_B;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

    @FXML
    private Text txtWelcome;
    @FXML
    private TextField searchId;

    EmployeeBO employeeBO= (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
      txtContactNumber.setText("");
      txtSalary.setText("");
      txtAddress.setText("");
      txtD_O_B.setText("");
      cmbProductIdFeeId.setValue(null);

    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {

            boolean isDeleted =   employeeBO.delete(id);
        //    boolean isDeleted = EmployeeRepo.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee deleted!").show();
                loadAllEmployee();
                clearFields();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnProductOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/productPagee.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("product Form");

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        // Fetching data from form fields
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String contactNumber = txtContactNumber.getText().trim();
        String salary = txtSalary.getText().trim();
        String address = txtAddress.getText().trim();
        String birthday = txtD_O_B.getText().trim();
        String ProductId = cmbProductIdFeeId.getValue();

        if (isValied()) {
            boolean isSaved = false;
            try {

             isSaved=   employeeBO.save(new EmployeeDTO(id,name,contactNumber,salary,address,birthday,ProductId));
             //   isSaved = EmployeeRepo.save( new EmployeeDTO(id, name, contactNumber, salary, address, birthday, ProductId));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                loadAllEmployee();
                clearFields();

            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Check your fields correctly !").show();
        }
        loadAllEmployee();
    }




    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String contactNumber = txtContactNumber.getText().trim();
        String salary = txtSalary.getText().trim();
        String address = txtAddress.getText().trim();
        String birthday = txtD_O_B.getText().trim();
        String productId = cmbProductIdFeeId.getValue();

        // Validate inputs to ensure they are not null or empty
        if (id.isEmpty() || name.isEmpty() || contactNumber.isEmpty() || salary.isEmpty() || address.isEmpty() || birthday.isEmpty() || productId == null) {
            new Alert(Alert.AlertType.ERROR, "Please fill in all fields.").show();
            return;
        }

        EmployeeDTO employee = new EmployeeDTO(id, name, contactNumber, salary, address, birthday, productId);

        try {
            boolean isUpdated = employeeBO.update(employee);
      //      boolean isUpdated = EmployeeRepo.update(employee);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee updated successfully!").show();
                clearFields();
                loadAllEmployee();
            } else {
                new Alert(Alert.AlertType.ERROR, "Update failed. Please check your data.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbProductIdOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {


        String code = cmbProductIdFeeId.getValue();

        try {
            ProductDTO productDTO = employeeBO.searchId(code);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }


    public void initialize() {
        setCellValueFactory();
        loadAllEmployee();
        getProductIds();


    }

    private void getProductIds() {
        try {
            ArrayList<ProductDTO> allProducts = employeeBO.getProductCode();
            for (ProductDTO p : allProducts) {
                cmbProductIdFeeId.getItems().add(p.getId());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load Product ids").show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colD_O_B.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
    }

        private void loadAllEmployee() {
      ObservableList<Employee> obList = FXCollections.observableArrayList();

        try {
            List<EmployeeDTO> employeeList = employeeBO.getAll();
           // List<EmployeeDTO> employeeList = EmployeeRepo.getAll();
            for (EmployeeDTO employee : employeeList) {
                EmployeeTm tm = new EmployeeTm(
                        employee.getId(),
                        employee.getName(),
                        employee.getContactNumber(),
                        employee.getSalary(),
                        employee.getAddress(),
                        employee.getBirthday(),
                        employee.getProductId()
                );

                obList.add(tm);
            }

            tblEmployee.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


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

    public void btnDashBoardOrderOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/adminPlaseOrder.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Order Form");
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

    public void btnDashBoadSupplierOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/supplierPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("supplier Form");
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/loginpage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Form");
    }

    public void EmpNameOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtName);

    }
    public boolean isValied(){
        if (!Regex.setTextColor(TextFields.NAME,txtName)) return false;
        if (!Regex.setTextColor(TextFields.IDE,txtId)) return false;
        if (!Regex.setTextColor(TextFields.ADDRESS,txtAddress)) return false;
        if (!Regex.setTextColor(TextFields.SALARY,txtSalary)) return false;
        if (!Regex.setTextColor(TextFields.CONTACT,txtContactNumber)) return false;
        if (!Regex.setTextColor(TextFields.DATE,txtD_O_B)) return false;


        return true;

    }


    public void EmpIdOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.IDE,txtId);

    }

    public void EmpAddressOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.ADDRESS,txtAddress);
    }

    public void EmpSalaryOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.SALARY,txtSalary);
    }

    public void EmpContactNumberOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.CONTACT,txtContactNumber);
    }

    public void EmpBirthdayOnKeyAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DATE,txtD_O_B);
    }


    public void txtEmployeeOnActionID(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id =txtId.getText();
        EmployeeDTO employeeDTO = employeeBO.search(id);
        if(employeeDTO != null){
            txtId.setText(employeeDTO.getId());
            txtName.setText(employeeDTO.getName());
            txtContactNumber.setText(employeeDTO.getContactNumber());
            txtSalary.setText(employeeDTO.getSalary());
            txtAddress.setText(employeeDTO.getAddress());


                txtD_O_B.setText(employeeDTO.getBirthday());

         cmbProductIdFeeId.setValue(employeeDTO.getProductId());

        }
        else {
            new Alert(Alert.AlertType.ERROR,"Employee is not found!").show();
        }
    }
}

