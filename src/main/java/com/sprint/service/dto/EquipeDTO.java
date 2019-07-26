package com.sprint.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.sprint.domain.Joueur;

/**
 * A DTO for the {@link com.sprint.domain.Equipe} entity.
 */
public class EquipeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2171688639513170714L;

	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String acronym;

	@NotNull
	private Float budget;

	private List<Joueur> joueurs;

	public List<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(List<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public Float getBudget() {
		return budget;
	}

	public void setBudget(Float budget) {
		this.budget = budget;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		EquipeDTO equipeDTO = (EquipeDTO) o;
		if (equipeDTO.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), equipeDTO.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "EquipeDTO [id=" + id + ", name=" + name + ", acronym=" + acronym + ", budget=" + budget + ", joueurs="
				+ joueurs + "]";
	}

}
