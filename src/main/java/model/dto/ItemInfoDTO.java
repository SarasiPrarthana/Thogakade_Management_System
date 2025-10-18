package model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemInfoDTO {

    private String itemCode;

    private String description;

    private String category;

    private int qtyOnHand;

    private double unitPrice;
}
