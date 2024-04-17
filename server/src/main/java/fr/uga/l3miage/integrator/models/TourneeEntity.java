package fr.uga.l3miage.integrator.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourneeEntity {
    @Id
    private String reference;
    private String lettre;
}
