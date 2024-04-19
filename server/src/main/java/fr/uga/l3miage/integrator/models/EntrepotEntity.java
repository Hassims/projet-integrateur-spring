package fr.uga.l3miage.integrator.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name ="entrepot")
public class EntrepotEntity {
    @Id
    private String nom;
    private String lettre;
    private String adresse;
    private String codePostal;
    private String ville;
    @OneToMany(mappedBy="entrepot")
    private Set<StockEntity> stocks;
    @OneToMany(mappedBy="entrepot")
    private Set<EmployeEntity> employes;
    @OneToMany(mappedBy="entrepot")
    private Set<CamionEntity> camions;
    @OneToMany(mappedBy="entrepot")
    private Set<JourneeEntity> journees;
}
