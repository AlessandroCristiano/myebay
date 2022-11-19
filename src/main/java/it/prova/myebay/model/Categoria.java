package it.prova.myebay.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "codice")
	private String codice;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "categorie")
	private Set<Annuncio> annunci = new HashSet<Annuncio>();

	public Categoria() {
		super();
	}

	public Categoria(String descrizione, String codice) {
		super();
		this.descrizione = descrizione;
		this.codice = codice;
	}

	public Categoria(String descrizione, String codice, Set<Annuncio> annunci) {
		super();
		this.descrizione = descrizione;
		this.codice = codice;
		this.annunci = annunci;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Set<Annuncio> getAnnunci() {
		return annunci;
	}

	public void setAnnunci(Set<Annuncio> annunci) {
		this.annunci = annunci;
	}
	
}
