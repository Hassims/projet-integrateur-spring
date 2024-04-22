package fr.uga.l3miage.integrator.models;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Data
@Table(name ="stock")
public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Min(0)
    private int quantite;
    @ManyToOne
    private ProduitEntity produit;
    @ManyToOne
    private EntrepotEntity entrepot;
}
