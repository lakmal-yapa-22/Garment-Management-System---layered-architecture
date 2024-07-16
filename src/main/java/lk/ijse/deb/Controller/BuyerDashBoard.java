package lk.ijse.deb.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class BuyerDashBoard {


    @FXML
    private AnchorPane root;




    private void navigateAboutFactory() throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Buyer/buyerAboutFactort.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("About Factory Form");
    }



    private void navigateTrouser() throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Buyer/trousers.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("trouser");
    }

    private void navigateShirt() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/Buyer/shirt_.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("shirt");

    }

    private void navigateT_Shirt() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/Buyer/t_shirt.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("T shirt");
    }
    private void navigateFrock() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/Buyer/frock.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("frock");

    }

    private void navigateShort() throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Buyer/short.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("short");
    }
    @FXML
    void AnchorAboutFactoryOnMouseClicked(MouseEvent event) throws IOException {
        navigateAboutFactory();
    }
    @FXML
    void AnchorRegisterBuyerMouseOnClick(MouseEvent event) throws IOException {
        navigateTrouser();

    }

    @FXML
    void ancapaneShirtOnMouseClick(MouseEvent event) throws IOException {
       navigateShirt();
    }


    @FXML//
    void ancharPaneTshirtMouseOnClick(MouseEvent event) throws IOException {
     navigateT_Shirt();

    }


    @FXML
    void anchorPaneFrockMouseOnClick(MouseEvent event) throws IOException {
      navigateFrock();
    }



    @FXML
    void anchorPaneTrouserMouseOnClick(MouseEvent event) throws IOException {
      navigateShort();
    }



    @FXML
    void txtAboutFactoryMouseOnClick(MouseEvent event) throws IOException {
      navigateAboutFactory();
    }

    @FXML
    void txtFrockOnClick(MouseEvent event) throws IOException {
   navigateFrock();
    }



    @FXML
    void txtRegisterBuyerOnClick(MouseEvent event) throws IOException {
     navigateTrouser();
    }

    @FXML
    void txtShirtMouseOnClick(MouseEvent event) throws IOException {
       navigateShirt();
    }

    @FXML
    void txtTrouserMouseOnClick(MouseEvent event) throws IOException {
  navigateShort();

    }

    @FXML
    void txtTshirtOnClick(MouseEvent event) throws IOException {
       navigateT_Shirt();
    }

    public void btnBackMouseOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Buyer/buyerLogin.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("login");
    }
}
