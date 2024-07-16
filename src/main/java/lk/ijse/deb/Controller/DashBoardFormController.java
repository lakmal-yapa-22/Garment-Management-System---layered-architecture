package lk.ijse.deb.Controller;

import animatefx.animation.SlideInDown;
import animatefx.animation.SlideInLeft;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.deb.db.DbConnection;
import net.sf.jasperreports.engine.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashBoardFormController {

    @FXML
    private AnchorPane root;
    private void employeeNavigare() throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/employeePage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("employee Form");
    }

    private void navigateBuyer() throws IOException {

        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/buyerPageAdmin.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Buyer Form");
    }
    private void navigateRowMatirial() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/rowMatirialPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Row Matirial Form");
    }

    private void ProductNavigare() throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/productPagee.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("product Form");
    }
    private void SupplierNavigare() throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/supplierPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("supplier Form");
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {

        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/welcomePage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("welcome Form");

    }
    private void OrderNavigare() throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/adminPlaseOrder.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Order Form");
    }
    private void navigatePayment() throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/payment.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("payment Form");
    }


    @FXML
    void ancapaneBuyerMouseOnClick(MouseEvent event) throws IOException {
        navigateBuyer();
    }

    @FXML
    void AnchorPaneEmployeeOnMouseClicked(MouseEvent event) throws IOException {
        employeeNavigare();

    }
    @FXML
    void txtEmployeeMouseOnClick(MouseEvent event) throws IOException {
        employeeNavigare();

    }

    @FXML
    void ancarPaneOrderMouseOnClick(MouseEvent event) throws IOException {
        OrderNavigare();

    }
    @FXML
    void txtOrderMouseOnClick(MouseEvent event) throws IOException {
        OrderNavigare();

    }

    @FXML
    void ancharPaneMouseOnClick(MouseEvent event) throws IOException {
      navigateRowMatirial();

    }



    @FXML
    void anchorPaneMouseProductOnClick(MouseEvent event) throws IOException {
        ProductNavigare();


    }


    @FXML
    void txtBuyerMouseOnClick(MouseEvent event) throws IOException {
     navigateBuyer();
    }

    @FXML
    void txtProductOnClick(MouseEvent event) throws IOException {

        ProductNavigare();
    }

    @FXML
    void txtRowMatirialMouseOnClick(MouseEvent event) throws IOException {
       navigateRowMatirial();

    }

    @FXML
    void txtSupplierMouseOnClick(MouseEvent event) throws IOException {
        SupplierNavigare();


    }
    public void AnchorSupplierPaneMouseOnClick(MouseEvent mouseEvent) throws IOException {
        SupplierNavigare();
    }
    public void btnDashBoardEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        employeeNavigare();
    }

    public void btnDashBoadSupplierOnAction(ActionEvent actionEvent) throws IOException {
        SupplierNavigare();
    }

    public void btnDashBoardProductOnAction(ActionEvent actionEvent) throws IOException {
        ProductNavigare();
    }


    public void btnDashBoardRowMatirialOnAction(ActionEvent actionEvent) throws IOException {
      navigateRowMatirial();
    }

    public void btnDashBoardBuyerOnAction(ActionEvent actionEvent) throws IOException {
     navigateBuyer();

    }

    public void btnDashBoardOrderOnAction(ActionEvent actionEvent) throws IOException {
        OrderNavigare();
    }

    public void anchorPaneMousePaymentOnClick(MouseEvent mouseEvent) throws IOException {
       navigatePayment();
    }

    public void txtPaymentOnClick(MouseEvent mouseEvent) throws IOException {
     navigatePayment();
    }
    public void btnDashBoardPaymentOrderOnAction(ActionEvent actionEvent) throws IOException {
      navigatePayment();
    }
}
