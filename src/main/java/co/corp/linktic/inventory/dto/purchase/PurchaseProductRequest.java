package co.corp.linktic.inventory.dto.purchase;

import lombok.Data;

@Data
public class PurchaseProductRequest {

    public Long product_id;
    public Integer quantity;
}
