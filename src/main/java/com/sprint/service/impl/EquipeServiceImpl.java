package com.sprint.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprint.domain.Equipe;
import com.sprint.repository.EquipeRepository;
import com.sprint.repository.JoueurRepository;
import com.sprint.service.EquipeService;
import com.sprint.service.dto.EquipeDTO;
import com.sprint.service.mapper.EquipeMapper;

/**
 * Service Implementation for managing {@link Equipe}.
 */
@Service
@Transactional
public class EquipeServiceImpl implements EquipeService {

	private final Logger log = LoggerFactory.getLogger(EquipeServiceImpl.class);

	private final EquipeRepository equipeRepository;
	private final JoueurRepository joueurRepository;
	private final EquipeMapper equipeMapper;

	public EquipeServiceImpl(EquipeRepository equipeRepository, JoueurRepository joueurRepository,
			EquipeMapper equipeMapper) {
		this.equipeRepository = equipeRepository;
		this.equipeMapper = equipeMapper;
		this.joueurRepository = joueurRepository;
	}

	/**
	 * Save a equipe.
	 *
	 * @param equipeDTO the entity to save.
	 * @return the persisted entity.
	 */
	@Override
	public EquipeDTO save(EquipeDTO equipeDTO) {
		log.error("Request to save Equipe : {}", equipeDTO);
		Equipe equipe = equipeMapper.toEntity(equipeDTO);
		joueurRepository.saveAll(equipe.getJoueurs());
		equipe = equipeRepository.save(equipe);
		return equipeMapper.toDto(equipe);
	}

	/**
	 * Get all the equipes.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<EquipeDTO> findAll(Pageable pageable) {
		log.debug("Request to get all Equipes");
		return equipeRepository.findAll(pageable).map(equipeMapper::toDto);
	}

	/**
	 * Get one equipe by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<EquipeDTO> findOne(Long id) {
		log.error("Request to get Equipe : {}", id);

		return equipeRepository.findById(id).map(equipeMapper::toDto);
	}

	/**
	 * Delete the equipe by id.
	 *
	 * @param id the id of the entity.
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Equipe : {}", id);
		equipeRepository.deleteById(id);
	}
}
