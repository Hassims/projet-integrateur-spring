package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.models.ClientEntity;
import fr.uga.l3miage.integrator.models.CommandeEntity;
import fr.uga.l3miage.integrator.response.ClientDTO;
import org.mapstruct.Mapper;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface ClientMapper {
    ClientDTO toDto(ClientEntity client);

    default Set<String> mapCommandes(Set<CommandeEntity> commandes){
        return commandes.stream().map(CommandeEntity::getReference).collect(Collectors.toSet());
    }
}
