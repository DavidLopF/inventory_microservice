package co.corp.linktic.inventory.dto.purchase;

import java.util.List;

public class PurchaseRequestDTO {
    private String email;
    private List<PurchaseItemDTO> purchaseItems;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<PurchaseItemDTO> getPurchaseItems() {
        return purchaseItems;
    }
    public void setPurchaseItems(List<PurchaseItemDTO> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }
}