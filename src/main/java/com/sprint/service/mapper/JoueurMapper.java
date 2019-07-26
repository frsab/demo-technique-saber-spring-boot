package com.sprint.service.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.sprint.domain.Joueur;
import com.sprint.service.dto.JoueurDTO;

/**
 * Mapper for the entity {@link Joueur} and its DTO {@link JoueurDTO}.
 */
@Mapper(componentModel = "spring", uses = { EquipeMapper.class })
public interface JoueurMapper extends EntityMapper<JoueurDTO, Joueur> {
	JoueurMapper MAPPER = Mappers.getMapper(JoueurMapper.class);

	@Mapping(source = "equipe.id", target = "equipeId")
	JoueurDTO toDto(Joueur joueur);

	@InheritInverseConfiguration
	Joueur toEntity(JoueurDTO joueurDTO);

	default Joueur fromId(Long id) {
		if (id == null) {
			return null;
		}
		Joueur joueur = new Joueur();
		joueur.setId(id);
		return joueur;
	}

}
