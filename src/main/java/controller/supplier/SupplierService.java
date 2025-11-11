package controller.supplier;

import javafx.collections.ObservableList;
import model.dto.SupplierInfoDTO;

public interface SupplierService {

    void addSupplierDetails(String supplierID,String name,String companyName,String address,String city,String province,String postalCode,String phone,String email);

    void deleteSupplierDetails(String supplierID);

    void updateSupplierDetails(String supplierID,String name,String companyName,String address,String city,String province,String postalCode,String phone,String email);

    ObservableList<SupplierInfoDTO> getAllRoomsDetails();
}
