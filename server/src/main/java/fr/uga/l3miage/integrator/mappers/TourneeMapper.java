package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.models.*;
import fr.uga.l3miage.integrator.response.VisualiserUneTourneeDTO;
import org.mapstruct.Mapper;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface TourneeMapper {

    VisualiserUneTourneeDTO toResponse(TourneeEntity playlistEntity);

    default String mapJournee(JourneeEntity journee) {
        return journee.getDate().toString();
    }

    default String mapCamion(CamionEntity camion) { return camion.getImmatriculation(); }

    default Set<String> mapSetEmploye(Set<EmployeEntity> employes) {
        return employes.stream().map(EmployeEntity::getTrigramme).collect(Collectors.toSet());
    }

    default Set<String> mapSetCommande(Set<CommandeEntity> commandes) {
        return commandes.stream().map(CommandeEntity::getReference).collect(Collectors.toSet());
    }
}
