package com.sprint.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Equipe.
 */

@Entity
@Table(name = "equipe")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Equipe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "acronym", nullable = false)
    private String acronym;

    @NotNull
    @Column(name = "budget", nullable = false)
    private Float budget;

    @OneToMany(mappedBy = "equipe")
//    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//    private Set<Joueur> joueurs = new HashSet<>();
    private List<Joueur> joueurs = new ArrayList<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Equipe name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public Equipe acronym(String acronym) {
        this.acronym = acronym;
        return this;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public Float getBudget() {
        return budget;
    }

    public Equipe budget(Float budget) {
        this.budget = budget;
        return this;
    }

    public void setBudget(Float budget) {
        this.budget = budget;
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public Equipe joueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
        return this;
    }

    public Equipe addJoueur(Joueur joueur) {
        this.joueurs.add(joueur);
        joueur.setEquipe(this);
        return this;
    }

    public Equipe removeJoueur(Joueur joueur) {
        this.joueurs.remove(joueur);
        joueur.setEquipe(null);
        return this;
    }

    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Equipe)) {
            return false;
        }
        return id != null && id.equals(((Equipe) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

	@Override
	public String toString() {
		return "Equipe [id=" + id + ", name=" + name + ", acronym=" + acronym + ", budget=" + budget + ", joueurs="
				+ joueurs + "]";
	}


}
