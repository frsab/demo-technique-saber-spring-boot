package com.sprint.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.rest.errors.BadRequestAlertException;
import com.sprint.service.JoueurService;
import com.sprint.service.dto.JoueurDTO;



/**
 * REST controller for managing {@link com.sprint.domain.Joueur}.
 */
@RestController
@RequestMapping("/api")
public class JoueurRest {

    private final Logger log = LoggerFactory.getLogger(JoueurRest.class);

    private static final String ENTITY_NAME = "joueur";

   // @Value("${jhipster.clientApp.name}")
   // private String applicationName;

    private final JoueurService joueurService;

    public JoueurRest(JoueurService joueurService) {
        this.joueurService = joueurService;
    }

    /**
     * {@code POST  /joueurs} : Create a new joueur.
     *
     * @param joueurDTO the joueurDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new joueurDTO, or with status {@code 400 (Bad Request)} if the joueur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/joueurs")
    public ResponseEntity<JoueurDTO> createJoueur(@Valid @RequestBody JoueurDTO joueurDTO) throws URISyntaxException,BadRequestAlertException {
        log.debug("REST request to save Joueur : {}", joueurDTO);
        if (joueurDTO.getId() != null) {
            throw new BadRequestAlertException("A new joueur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JoueurDTO result = joueurService.save(joueurDTO);
        return ResponseEntity.created(new URI("/api/joueurs/" + result.getId()))
           // .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /joueurs} : Updates an existing joueur.
     *
     * @param joueurDTO the joueurDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated joueurDTO,
     * or with status {@code 400 (Bad Request)} if the joueurDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the joueurDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/joueurs")
    public ResponseEntity<JoueurDTO> updateJoueur(@Valid @RequestBody JoueurDTO joueurDTO) throws URISyntaxException,BadRequestAlertException {
        log.debug("REST request to update Joueur : {}", joueurDTO);
        if (joueurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        JoueurDTO result = joueurService.save(joueurDTO);
        return ResponseEntity.ok()
           // .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, joueurDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /joueurs} : get all the joueurs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of joueurs in body.
     */
    @GetMapping("/joueurs")
    public List<JoueurDTO> getAllJoueurs() {
        log.debug("REST request to get all Joueurs");
        return joueurService.findAll();
    }

    /**
     * {@code GET  /joueurs/:id} : get the "id" joueur.
     *
     * @param id the id of the joueurDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the joueurDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/joueurs/{id}")
    public ResponseEntity<JoueurDTO> getJoueur(@PathVariable Long id) {
        log.debug("REST request to get Joueur : {}", id);
        Optional<JoueurDTO> optionalJoueurDTO = joueurService.findOne(id);
        if(optionalJoueurDTO.isPresent()) {
       	 return ResponseEntity.ok()
            		//.headers(headers)
            		.body(optionalJoueurDTO.get());
       }else {
       	ResponseEntity<JoueurDTO> result = new ResponseEntity<JoueurDTO> (null);
       //	HeadersBuilder<?> a = result.notFound();
       	return result;
       }
    //    return ResponseUtil.wrapOrNotFound(optionalJoueurDTO);
    }

    /**
     * {@code DELETE  /joueurs/:id} : delete the "id" joueur.
     *
     * @param id the id of the joueurDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/joueurs/{id}")
    public ResponseEntity<Void> deleteJoueur(@PathVariable Long id) {
        log.debug("REST request to delete Joueur : {}", id);
        joueurService.delete(id);
        return ResponseEntity.noContent()
        		//.headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
        		.build();
    }
}
