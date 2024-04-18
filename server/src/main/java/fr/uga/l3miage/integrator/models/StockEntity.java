package fr.uga.l3miage.integrator.models;


import javax.persistence.ManyToOne;
import javax.persistence.Entity ;


@Entity
public class StockEntity extends BaseEntity {

    private int quantite;
    @ManyToOne
    private ProduitEntity produit;
    @ManyToOne
    private EntrepotEntity entrepot;

}
