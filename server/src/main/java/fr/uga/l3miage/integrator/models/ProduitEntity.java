package fr.uga.l3miage.integrator.models;

import fr.uga.l3miage.integrator.enums.Encombrement;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Entity ;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name ="produit")
public class ProduitEntity {
    @Id
    private String reference;
    private String titre;
    private String description;
    private double prix;
    private Encombrement encombrement;
    private boolean optionMontage;
    private Integer tdmTheorique;
    @OneToMany(mappedBy="produit")
    private Set<LigneEntity> lignes;
    @OneToMany(mappedBy="produit")
    private Set<StockEntity> stocks;
}
