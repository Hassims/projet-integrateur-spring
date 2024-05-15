package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.models.ClientEntity;
import fr.uga.l3miage.integrator.models.CommandeEntity;
import fr.uga.l3miage.integrator.models.LivraisonEntity;
import fr.uga.l3miage.integrator.response.CommandeDTO;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper
public interface CommandeMapper {

    CommandeDTO toDTO(CommandeEntity entity);

    Set<CommandeDTO> toSetDTO(Set<CommandeEntity> entities);

    default String mapClient(ClientEntity client) {
        return client.getEmail();
    }

    default String mapLivraison(LivraisonEntity livraison) {
        return livraison == null ? null : livraison.getReference();
    }
}
