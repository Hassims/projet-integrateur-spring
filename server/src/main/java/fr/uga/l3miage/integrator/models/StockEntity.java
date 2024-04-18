package fr.uga.l3miage.integrator.models;


import javax.persistence.ManyToOne;
import javax.persistence.Entity ;
import javax.persistence.Table;


@Entity
@Table(name ="stock")

public class StockEntity extends BaseEntity {

    private int quantite;
    @ManyToOne
    private ProduitEntity produit;
    @ManyToOne
    private EntrepotEntity entrepot;

}
