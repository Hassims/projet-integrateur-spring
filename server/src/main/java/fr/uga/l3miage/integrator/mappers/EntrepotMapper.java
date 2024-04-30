package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.models.*;
import fr.uga.l3miage.integrator.response.EntrepotDTO;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface EntrepotMapper {
    EntrepotDTO toDTO(EntrepotEntity entrepot);
    List<EntrepotDTO> toDTOList(List<EntrepotEntity> entities);
    Set<EntrepotDTO> toDTOSet(Set<EntrepotEntity> entities);

    default Set<Long> mapJournees(Set<JourneeEntity> journees) {
        return journees == null ?
                Set.of() :
                journees.stream().map(JourneeEntity::getId).collect(Collectors.toSet());
    }

    default Set<Long> mapStocks(Set<StockEntity> stocks) {
        return stocks == null ?
                Set.of() :
                stocks.stream().map(StockEntity::getId).collect(Collectors.toSet());
    }

    default Set<String> mapEmployes(Set<EmployeEntity> employes) {
        return employes == null ?
                Set.of() :
                employes.stream().map(EmployeEntity::getTrigramme).collect(Collectors.toSet());
    }

    default Set<String> mapCamions(Set<CamionEntity> camions) {
        return camions == null ?
                Set.of() :
                camions.stream().map(CamionEntity::getImmatriculation).collect(Collectors.toSet());
    }

}
