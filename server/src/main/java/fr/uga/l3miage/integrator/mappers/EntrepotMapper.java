package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.models.*;
import fr.uga.l3miage.integrator.repositories.EntrepotRepository;
import fr.uga.l3miage.integrator.response.EntrepotsDTO;
import fr.uga.l3miage.integrator.response.VisualiserUneTourneeDTO;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper
public interface EntrepotMapper {
    EntrepotsDTO toDTO(EntrepotEntity entrepot);

    default String mapJournees(Set<JourneeEntity> journees) {
        return null;
    }

    default String mapStocks(Set<StockEntity> stocks) {
        return null;
    }

    default String mapEmployes(Set<EmployeEntity> employes) {
        return null;
    }

    default String mapCamions(Set<CamionEntity> camions) {
        return null;
    }

}
