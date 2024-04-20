package fr.uga.l3miage.integrator.models;


import javax.persistence.*;


@Entity
@Table(name ="stock")
public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantite;
    @ManyToOne
    private ProduitEntity produit;
    @ManyToOne
    private EntrepotEntity entrepot;
}
