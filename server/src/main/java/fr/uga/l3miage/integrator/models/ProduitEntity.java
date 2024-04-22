package fr.uga.l3miage.integrator.models;

import fr.uga.l3miage.integrator.enums.Encombrement;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
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
