package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.models.*;
import fr.uga.l3miage.integrator.response.EntrepotDTO;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface EntrepotMapper {
    EntrepotDTO toDTO(EntrepotEntity entrepot);
    List<EntrepotDTO> toDTOList(List<EntrepotEntity> entities);
    Set<EntrepotDTO> toDTOSet(Set<EntrepotEntity> entities);

    default Set<String> mapJournees(Set<JourneeEntity> journees) {
        return journees == null ?
                Set.of() :
                journees.stream().map(JourneeEntity::getReference).collect(Collectors.toSet());
    }

    default Set<String> mapStocks(Set<StockEntity> stocks) {
        return stocks == null ?
                Set.of() :
                stocks.stream().map(stock -> stock.getProduit().getReference()).collect(Collectors.toSet());
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
