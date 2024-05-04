package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.models.CommandeEntity;
import fr.uga.l3miage.integrator.models.LivraisonEntity;
import fr.uga.l3miage.integrator.response.VisualiserLivraisonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper
public interface VisualiserLivraisonMapper {

    default VisualiserLivraisonDTO toDTO(LivraisonEntity entity) {
        return VisualiserLivraisonDTO.builder()
                .reference(entity.getReference())
                .numero(entity.getNumero())
                .client(Mappers.getMapper(ClientMapper.class)
                        .toDto(entity.getCommandes().isEmpty() ?
                                null :
                                entity.getCommandes().stream().findFirst().get().getClient()))
                .commandes(entity.getCommandes().stream().map(CommandeEntity::getReference).collect(Collectors.toSet()))
                .build();
    }
}
