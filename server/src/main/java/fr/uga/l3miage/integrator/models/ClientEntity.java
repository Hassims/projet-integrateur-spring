package fr.uga.l3miage.integrator.models;

import javax.persistence.OneToMany;
import javax.persistence.Entity ;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name ="client")

public class ClientEntity extends BaseEntity {

    private String email;
    private String prenom;
    private String nom;
    private String adresse;
    private String codePostal;
    private String ville;

    @OneToMany(mappedBy="client")
    private Set<CommandeEntity> commandes;
}