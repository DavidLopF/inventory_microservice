package co.corp.linktic.inventory.dto.product;

import lombok.Data;

@Data
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String imageUrl;
}
