package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.EmployeeInfoDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeInfoController implements Initializable {

    ObservableList<EmployeeInfoDTO> employeeInfoDTOS = FXCollections.observableArrayList(
            new EmployeeInfoDTO("E001","Sunil Perera", "832451230V", "1983-07-12", "Manager", 75000.0, "0712456789", "No.15 Temple Road, Kalutara", "2018-05-10", "Active"),
            new EmployeeInfoDTO("E001","Sunil Perera", "832451230V", "1983-07-12", "Manager", 75000.0, "0712456789", "No.15 Temple Road, Kalutara", "2018-05-10", "Active"),
            new EmployeeInfoDTO("E001","Sunil Perera", "832451230V", "1983-07-12", "Manager", 75000.0, "0712456789", "No.15 Temple Road, Kalutara", "2018-05-10", "Active"),
            new EmployeeInfoDTO("E001","Sunil Perera", "832451230V", "1983-07-12", "Manager", 75000.0, "0712456789", "No.15 Temple Road, Kalutara", "2018-05-10", "Active"),
            new EmployeeInfoDTO("E001","Sunil Perera", "832451230V", "1983-07-12", "Manager", 75000.0, "0712456789", "No.15 Temple Road, Kalutara", "2018-05-10", "Active"),
            new EmployeeInfoDTO("E001","Sunil Perera", "832451230V", "1983-07-12", "Manager", 75000.0, "0712456789", "No.15 Temple Road, Kalutara", "2018-05-10", "Active")
            );

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContactNumber;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colJoinedDate;

    @FXML
    private TableColumn<?, ?> colNIC;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPosition;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtDOB;

    @FXML
    private TextField txtEmployeeID;

    @FXML
    private TextField txtJoinedDate;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPosition;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtStatus;

    @FXML
    private TableView<EmployeeInfoDTO> txtTbl;

    @FXML
    void btnAddAction(ActionEvent event) {

    }

    @FXML
    void btnClearAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colID.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colJoinedDate.setCellValueFactory(new PropertyValueFactory<>("joinedDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        txtTbl.setItems(employeeInfoDTOS);

        txtTbl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                txtEmployeeID.setText(newValue.getEmployeeID());
                txtName.setText(newValue.getName());
                txtNIC.setText(newValue.getNic());
                txtDOB.setText(newValue.getDob());
                txtPosition.setText(newValue.getPosition());
                txtSalary.setText(String.valueOf(newValue.getSalary()));
                txtContactNumber.setText(newValue.getContactNumber());
                txtAddress.setText(newValue.getAddress());
                txtJoinedDate.setText(newValue.getJoinedDate());
                txtStatus.setText(newValue.getStatus());
            }
        });

    }
}
