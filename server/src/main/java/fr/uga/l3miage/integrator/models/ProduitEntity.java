package fr.uga.l3miage.integrator.models;


import fr.uga.l3miage.integrator.enums.Encombrement;
import javax.persistence.OneToMany;
import javax.persistence.Entity ;
import java.util.Set;



@Entity
public class ProduitEntity extends BaseEntity {

    private String reference;
    private String titre;
    private String description;
    private String photo;
    private double prix;
    private Encombrement encombrement;
    private boolean optionMontage;
    private Integer tdmTheorique;

    @OneToMany(mappedBy="produit")
    private Set<LigneEntity> lignes;
    @OneToMany(mappedBy="produit")
    private Set<StockEntity> stocks;
}
