package com.sprint.service;

import com.sprint.service.dto.JoueurDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.sprint.domain.Joueur}.
 */
public interface JoueurService {

    /**
     * Save a joueur.
     *
     * @param joueurDTO the entity to save.
     * @return the persisted entity.
     */
    JoueurDTO save(JoueurDTO joueurDTO);

    /**
     * Get all the joueurs.
     *
     * @return the list of entities.
     */
    List<JoueurDTO> findAll();


    /**
     * Get the "id" joueur.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<JoueurDTO> findOne(Long id);

    /**
     * Delete the "id" joueur.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
