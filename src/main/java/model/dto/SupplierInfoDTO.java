package model.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplierInfoDTO {

    private String supplierID;

    private String name;

    private String companyName;

    private String address;

    private String city;

    private String province;

    private String postalCode;

    private String phone;

    private String email;



}
