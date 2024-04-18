package fr.uga.l3miage.integrator.mappers;

import fr.uga.l3miage.integrator.models.TourneeEntity;
import fr.uga.l3miage.integrator.response.VisualiserUneTourneeDTO;
import org.mapstruct.Mapper;

@Mapper
public interface TourneeMapper {
    VisualiserUneTourneeDTO toResponse(TourneeEntity playlistEntity);
}
