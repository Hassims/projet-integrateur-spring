package fr.uga.l3miage.integrator.models;

import fr.uga.l3miage.integrator.enums.Encombrement;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@EqualsAndHashCode(exclude = {"lignes", "stocks"})
@ToString(exclude = {"lignes", "stocks"})
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="produit")
public class ProduitEntity {
    @Id
    private String reference;
    @Column(nullable = false)
    private String titre;
    @Column(length = 1000)
    private String description;
    @Column(nullable = false)
    private double prix;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Encombrement encombrement;
    @Column(nullable = false)
    private boolean optionMontage;
    private Integer tdmTheorique;
    @OneToMany(mappedBy="produit")
    private Set<LigneEntity> lignes;
    @OneToMany(mappedBy="produit")
    private Set<StockEntity> stocks;
}
