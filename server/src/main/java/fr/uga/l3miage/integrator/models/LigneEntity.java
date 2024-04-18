package fr.uga.l3miage.integrator.models;

import javax.persistence.ManyToOne;
import javax.persistence.Entity ;


@Entity
public class LigneEntity extends BaseEntity {

    private int quantite;
    private boolean optionMontage;

    @ManyToOne
    private CommandeEntity commande;
    @ManyToOne
    private ProduitEntity produit;
}
