package co.corp.linktic.inventory.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "purchase_product")
public class PurchaseProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    // OJO: Este "purchase" coincide con la propiedad en la clase Purchase
    @JsonIgnoreProperties("purchaseProducts")
    private Purchase purchase;

    private Long productId;
    private Integer quantity;
}
