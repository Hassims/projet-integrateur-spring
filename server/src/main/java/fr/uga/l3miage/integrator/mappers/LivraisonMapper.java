package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.models.CommandeEntity;
import fr.uga.l3miage.integrator.models.LivraisonEntity;
import fr.uga.l3miage.integrator.response.LivraisonDTO;
import org.mapstruct.Mapper;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface LivraisonMapper {

    LivraisonDTO toDTO(LivraisonEntity livraisonEntity) ;

    default Set<String> mapCommande (Set<CommandeEntity> commande){
        return commande.stream().map(CommandeEntity::getReference).collect(Collectors.toSet());
    }
}
