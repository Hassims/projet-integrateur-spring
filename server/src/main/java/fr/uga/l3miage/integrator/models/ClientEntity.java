package fr.uga.l3miage.integrator.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Data
@Table(name ="client")
public class ClientEntity {
    @Id
    @Email
    private String email;
    @Column(nullable = false)
    private String prenom;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String adresse;
    @Column(nullable = false, length = 5)
    @Pattern(regexp = "^[0-9]{5}$", message = "Code postal non valide.")
    private String codePostal;
    @Column(nullable = false)
    private String ville;
    @OneToMany(mappedBy="client")
    private Set<CommandeEntity> commandes;
}