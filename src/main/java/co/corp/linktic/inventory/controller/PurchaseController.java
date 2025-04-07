package co.corp.linktic.inventory.controller;

import co.corp.linktic.inventory.dto.GeneralResponseDTO;
import co.corp.linktic.inventory.dto.purchase.PurchaseItemDTO;
import co.corp.linktic.inventory.dto.purchase.PurchaseRequestDTO;
import co.corp.linktic.inventory.entity.Purchase;
import co.corp.linktic.inventory.service.purchase.PurchaseServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
@CrossOrigin(origins = "*",
        allowedHeaders = {"Content-Type", "api-key", "Authorization"},
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class PurchaseController {


    private final PurchaseServiceImpl purchaseService;

    public PurchaseController(PurchaseServiceImpl purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GeneralResponseDTO<Purchase>> createPurchase(
            @RequestBody PurchaseRequestDTO purchaseRequest) {

        Purchase createdPurchase = purchaseService.createPurchase(purchaseRequest);

        GeneralResponseDTO<Purchase> response = new GeneralResponseDTO<>();
        response.setData(createdPurchase);

        return ResponseEntity.ok(response);
    }
}
