package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginFormController {

    @FXML
    private PasswordField txtPasswordId;

    @FXML
    private TextField txtUserNameId;

    @FXML
    void btnLoginAction(ActionEvent event) {
        String userName = txtUserNameId.getText();
        String password = txtPasswordId.getText();

        if (userName.equals("Admin") && password.equals("1234")) {
            try {
                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml")));
                stage.setScene(scene);
                stage.centerOnScreen();
            } catch (IOException e) {
                e.printStackTrace();
                showError("Error loading dashboard.fxml");
            }
            txtUserNameId.clear();
            txtPasswordId.clear();
        } else {
            showError("Invalid Username or Password");
            txtUserNameId.clear();
            txtPasswordId.clear();
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
