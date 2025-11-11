package controller.customer;

import javafx.collections.ObservableList;
import model.dto.CustomerInfoDTO;
import model.dto.EmployeeInfoDTO;

public interface CustomerService {

    void addCustomerDetails(String customerID,String title,String name,String dob,double salary,String address,String city,String province,String postalCode);

    void deleteCustomerDetails(String customerId);

    void updateCustomerDetails(String customerID,String title,String name,String dob,double salary,String address,String city,String province,String postalCode);

    ObservableList<CustomerInfoDTO> loadCustomerDetails();

}
