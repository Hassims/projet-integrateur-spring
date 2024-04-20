package fr.uga.l3miage.integrator.models;

import javax.persistence.*;

@Entity
@Table(name ="ligne")
public class LigneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantite;
    private boolean optionMontage;
    @ManyToOne
    private CommandeEntity commande;
    @ManyToOne
    private ProduitEntity produit;
}
