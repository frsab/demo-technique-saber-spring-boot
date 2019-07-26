package com.sprint.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sprint.rest.errors.BadRequestAlertException;
import com.sprint.service.EquipeService;
import com.sprint.service.dto.EquipeDTO;

/**
 * REST controller for managing {@link com.sprint.domain.Equipe}.
 */
@RestController
@RequestMapping("/api")
public class EquipeRest {

	private final Logger log = LoggerFactory.getLogger(EquipeRest.class);

	private static final String ENTITY_NAME = "equipe";

//    @Value("${jhipster.clientApp.name}")
//    private String applicationName;

	private final EquipeService equipeService;

	public EquipeRest(EquipeService equipeService) {
		this.equipeService = equipeService;
	}

	/**
	 * {@code POST  /equipes} : Create a new equipe.
	 *
	 * @param equipeDTO the equipeDTO to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new equipeDTO, or with status {@code 400 (Bad Request)} if
	 *         the equipe has already an ID.
	 * @throws URISyntaxException       if the Location URI syntax is incorrect.
	 * @throws BadRequestAlertException
	 */
	@PostMapping("/equipes")
	public ResponseEntity<EquipeDTO> createEquipe(@RequestBody EquipeDTO equipeDTO)
			throws URISyntaxException, BadRequestAlertException {
		log.debug("REST request to save Equipe : {}", equipeDTO);
		if (equipeDTO.getId() != null) {
			throw new BadRequestAlertException("A new equipe cannot already have an ID", ENTITY_NAME, "idexists");
		}
		EquipeDTO result = equipeService.save(equipeDTO);
		log.debug("REST request to save Equipe saved: {}", result);

		return ResponseEntity.created(new URI("/api/equipes/" + result.getId())).body(result);
	}

	/**
	 * {@code PUT  /equipes} : Updates an existing equipe.
	 *
	 * @param equipeDTO the equipeDTO to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated equipeDTO, or with status {@code 400 (Bad Request)} if
	 *         the equipeDTO is not valid, or with status
	 *         {@code 500 (Internal Server Error)} if the equipeDTO couldn't be
	 *         updated.
	 * @throws URISyntaxException       if the Location URI syntax is incorrect.
	 * @throws BadRequestAlertException
	 */
	@PutMapping("/equipes")
	public ResponseEntity<EquipeDTO> updateEquipe(@Valid @RequestBody EquipeDTO equipeDTO)
			throws URISyntaxException, BadRequestAlertException {
		log.debug("REST request to update Equipe : {}", equipeDTO);
		if (equipeDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		EquipeDTO result = equipeService.save(equipeDTO);
		return ResponseEntity.ok().body(result);
	}

	/**
	 * {@code GET  /equipes} : get all the equipes.
	 *
	 * @param pageable    the pagination information.
	 * @param queryParams a {@link MultiValueMap} query parameters.
	 * @param uriBuilder  a {@link UriComponentsBuilder} URI builder.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of equipes in body.
	 */
	@GetMapping("/equipes")
	public ResponseEntity<List<EquipeDTO>> getAllEquipes(Pageable pageable,
			@RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
		log.debug("REST request to get a page of Equipes");
		Page<EquipeDTO> page = equipeService.findAll(pageable);

		return ResponseEntity.ok().body(page.getContent());
	}

	/**
	 * {@code GET  /equipes/:id} : get the "id" equipe.
	 *
	 * @param id the id of the equipeDTO to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the equipeDTO, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/equipes/{id}")
	public ResponseEntity<EquipeDTO> getEquipe(@PathVariable Long id) {
		log.debug("REST request to get Equipe : {}", id);
		Optional<EquipeDTO> optionalEquipeDTO = equipeService.findOne(id);
		if (optionalEquipeDTO.isPresent()) {
			return ResponseEntity.ok()

					.body(optionalEquipeDTO.get());
		} else {
			ResponseEntity<EquipeDTO> result = new ResponseEntity<EquipeDTO>(null);

			return result;
		}
	}

	/**
	 * {@code DELETE  /equipes/:id} : delete the "id" equipe.
	 *
	 * @param id the id of the equipeDTO to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/equipes/{id}")
	public ResponseEntity<Void> deleteEquipe(@PathVariable Long id) {
		log.debug("REST request to delete Equipe : {}", id);
		equipeService.delete(id);
		return ResponseEntity.noContent()

				.build();
	}
}
