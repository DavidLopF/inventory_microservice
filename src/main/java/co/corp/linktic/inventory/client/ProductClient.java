package co.corp.linktic.inventory.client;

import co.corp.linktic.inventory.dto.GeneralResponseDTO;
import co.corp.linktic.inventory.dto.ResponseService;
import co.corp.linktic.inventory.dto.product.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "product-service",
        url = "${product.service.url}"
)
public interface ProductClient {

    @GetMapping("/api/products/{productId}/quantity")
    ResponseService<Integer> getProductQuantityById(
            @PathVariable("productId") Long productId,
            @RequestHeader("X-API-KEY") String apiKey
    );

    @PutMapping("/api/products/{productId}/stock")
    ResponseService<ProductDTO> updateStock(
            @PathVariable("productId") Long productId,
            @RequestParam("stock") Integer newStock,
            @RequestHeader("X-API-KEY") String apiKey
    );

    @GetMapping("/api/products/{id}")
    GeneralResponseDTO<ProductDTO> getProductById(
            @PathVariable("id") Long id,
            @RequestHeader("X-API-KEY") String apiKey
    );
}