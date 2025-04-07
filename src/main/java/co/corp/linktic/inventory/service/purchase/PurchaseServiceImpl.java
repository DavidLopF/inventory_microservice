package co.corp.linktic.inventory.service.purchase;

import co.corp.linktic.inventory.client.ProductClient;
import co.corp.linktic.inventory.dto.GeneralResponseDTO;
import co.corp.linktic.inventory.dto.product.ProductDTO;
import co.corp.linktic.inventory.dto.purchase.PurchaseItemDTO;
import co.corp.linktic.inventory.dto.purchase.PurchaseRequestDTO;
import co.corp.linktic.inventory.entity.Purchase;
import co.corp.linktic.inventory.entity.PurchaseProduct;
import co.corp.linktic.inventory.repository.PurchaseProductRepository;
import co.corp.linktic.inventory.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static co.corp.linktic.inventory.utils.Constants.PRODUCT_API_KEY;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PurchaseProductRepository purchaseProductRepository;

    @Autowired
    private ProductClient productClient;

    public Purchase createPurchase(PurchaseRequestDTO purchaseRequest) {

        Purchase purchase = new Purchase();
        purchase.setCreatedAt(LocalDateTime.now());
        // Se asigna el email de la petición
        purchase.setEmail(purchaseRequest.getEmail());

        double totalPrice = 0.0;
        List<PurchaseProduct> purchaseProducts = new ArrayList<>();

        for (PurchaseItemDTO item : purchaseRequest.getPurchaseItems()) {
            Long productId = item.getProductId();
            Integer quantity = item.getQuantity();

            ProductDTO product = productClient.getProductById(productId, PRODUCT_API_KEY).getData();
            if (product.getStock() < quantity) {
                throw new RuntimeException("Stock insuficiente para el producto con ID " + productId);
            }

            int newStock = product.getStock() - quantity;
            productClient.updateStock(productId, newStock, PRODUCT_API_KEY).getData();

            PurchaseProduct pp = new PurchaseProduct();
            pp.setPurchase(purchase);
            pp.setProductId(productId);
            pp.setQuantity(quantity);
            purchaseProducts.add(pp);

            totalPrice += product.getPrice() * quantity;
        }

        purchase.setPurchaseProducts(purchaseProducts);
        purchase.setTotalPrice(totalPrice);

        return purchaseRepository.save(purchase);
    }
}
