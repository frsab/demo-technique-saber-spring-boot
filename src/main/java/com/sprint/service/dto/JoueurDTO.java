package com.sprint.service.dto;
import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the {@link com.sprint.domain.Joueur} entity.
 */
public class JoueurDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6070959110056578000L;

	private Long id;

    @NotNull
    private String name;

    @NotNull
    private String position;


    private Long equipeId;

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getEquipeId() {
        return equipeId;
    }

    public void setEquipeId(Long equipeId) {
        this.equipeId = equipeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JoueurDTO joueurDTO = (JoueurDTO) o;
        if (joueurDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), joueurDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JoueurDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", position='" + getPosition() + "'" +
            ", equipe=" + getEquipeId() +
            "}";
    }
}
