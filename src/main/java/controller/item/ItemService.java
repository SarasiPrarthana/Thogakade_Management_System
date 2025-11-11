package controller.item;

public interface ItemService {

    void addItemDetails(String itemCode,String description,String category,int qtyOnHand,double unitPrice);

    void deleteItemDetails(String ItemCode);

    void updateItemDetails(String itemCode,String description,String category,int qtyOnHand,double unitPrice);
}
