package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.models.EntrepotEntity;
import fr.uga.l3miage.integrator.models.JourneeEntity;
import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.response.JourneeDTO;
import org.mapstruct.Mapper;

import java.util.HashSet;
import java.util.Set;

@Mapper
public interface JourneeMapper {
    JourneeDTO toDTO(JourneeEntity journee);

    default Set<String> mapTournees(Set<TourneeEntity> tournees) {
        Set<String> references = new HashSet<>();
        for (TourneeEntity tournee : tournees) {
            references.add(tournee.getReference());
        }
        return references;
    }

    default String mapEntrepot(EntrepotEntity entrepot) {
        return entrepot.getNom();
    }
}
