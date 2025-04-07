package co.corp.linktic.inventory.controller;


import co.corp.linktic.inventory.dto.GeneralResponseDTO;
import co.corp.linktic.inventory.service.inventory.InventoryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@CrossOrigin(origins = "*",
        allowedHeaders = {"Content-Type", "api-key", "Authorization"},
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class InventoryController {


    private final InventoryServiceImpl inventoryService;

    public InventoryController(InventoryServiceImpl inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping(value = "/product/{id}/quantity", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GeneralResponseDTO<Integer>> getQuantityProductById(@PathVariable("id") Long id) {
        Integer quantity = inventoryService.getProductQuantityById(id);

        GeneralResponseDTO<Integer> response = new GeneralResponseDTO<>();
        response.setData(quantity);
        return ResponseEntity.ok(response);
    }



}
