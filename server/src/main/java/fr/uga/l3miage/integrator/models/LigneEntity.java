package fr.uga.l3miage.integrator.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="ligne")
public class LigneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Min(1)
    private int quantite;
    @Column(nullable = false)
    private boolean optionMontage;
    @ManyToOne
    private CommandeEntity commande;
    @ManyToOne
    private ProduitEntity produit;
}
