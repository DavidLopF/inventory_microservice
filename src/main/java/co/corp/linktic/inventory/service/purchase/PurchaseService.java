package co.corp.linktic.inventory.service.purchase;

import co.corp.linktic.inventory.dto.purchase.PurchaseItemDTO;
import co.corp.linktic.inventory.dto.purchase.PurchaseRequestDTO;
import co.corp.linktic.inventory.entity.Purchase;

import java.util.List;

public interface PurchaseService {

    Purchase createPurchase(PurchaseRequestDTO purchaseRequest);
}
