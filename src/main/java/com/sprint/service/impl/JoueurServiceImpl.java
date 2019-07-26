package com.sprint.service.impl;

import com.sprint.service.JoueurService;
import com.sprint.domain.Joueur;
import com.sprint.repository.JoueurRepository;
import com.sprint.service.dto.JoueurDTO;
import com.sprint.service.mapper.JoueurMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Joueur}.
 */
@Service
@Transactional
public class JoueurServiceImpl implements JoueurService {

    private final Logger log = LoggerFactory.getLogger(JoueurServiceImpl.class);

    private final JoueurRepository joueurRepository;

    private final JoueurMapper joueurMapper;

    public JoueurServiceImpl(JoueurRepository joueurRepository, JoueurMapper joueurMapper) {
        this.joueurRepository = joueurRepository;
        this.joueurMapper = joueurMapper;
    }

    /**
     * Save a joueur.
     *
     * @param joueurDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public JoueurDTO save(JoueurDTO joueurDTO) {
        log.debug("Request to save Joueur : {}", joueurDTO);
        Joueur joueur = joueurMapper.toEntity(joueurDTO);
        joueur = joueurRepository.save(joueur);
        return joueurMapper.toDto(joueur);
    }

    /**
     * Get all the joueurs.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<JoueurDTO> findAll() {
        log.debug("Request to get all Joueurs");
        return joueurRepository.findAll().stream()
            .map(joueurMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one joueur by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<JoueurDTO> findOne(Long id) {
        log.debug("Request to get Joueur : {}", id);
        return joueurRepository.findById(id)
            .map(joueurMapper::toDto);
    }

    /**
     * Delete the joueur by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Joueur : {}", id);
        joueurRepository.deleteById(id);
    }
}
