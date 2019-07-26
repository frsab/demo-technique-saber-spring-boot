package com.sprint.service.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.sprint.domain.Equipe;
import com.sprint.service.dto.EquipeDTO;

/**
 * Mapper for the entity {@link Equipe} and its DTO {@link EquipeDTO}.
 */
@Mapper(componentModel = "spring", uses = {JoueurMapper.class})
public interface EquipeMapper extends EntityMapper<EquipeDTO, Equipe> {

	EquipeMapper MAPPER = Mappers.getMapper( EquipeMapper.class );

    //@Mapping(target = "joueurs",source = "joueurs")
    @Mapping(target = "removeJoueur", ignore = true)
    Equipe toEntity(EquipeDTO equipeDTO);
    
    
//    @Mapping(source = "orders", target = "orderItems")
//    @Mapping(source = "customerName", target = "name")
//    Customer toCustomer(CustomerDto customerDto);

    @InheritInverseConfiguration
    EquipeDTO toDto(Equipe equipe);

    default Equipe fromId(Long id) {
        if (id == null) {
            return null;
        }
        Equipe equipe = new Equipe();
        equipe.setId(id);
        return equipe;
    }
}
