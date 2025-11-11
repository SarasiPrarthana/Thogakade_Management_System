package controller.item;

import javafx.collections.ObservableList;
import model.dto.ItemInfoDTO;
import model.dto.SupplierInfoDTO;

public interface ItemService {

    void addItemDetails(String itemCode,String description,String category,int qtyOnHand,double unitPrice);

    void deleteItemDetails(String ItemCode);

    void updateItemDetails(String itemCode,String description,String category,int qtyOnHand,double unitPrice);

    ObservableList<ItemInfoDTO> loadItemDetails();
}
