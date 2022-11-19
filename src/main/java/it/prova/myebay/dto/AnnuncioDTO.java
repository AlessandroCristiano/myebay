package it.prova.myebay.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.prova.myebay.model.Annuncio;

public class AnnuncioDTO {
	
	private Long id;
	
	@NotBlank(message = "{annuncio.testoAnnuncio.notblank}")
	@Size(min = 4, max = 40, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	private String testoAnnuncio;
	
	@NotBlank(message = "{annuncio.prezzo.notblank}")
	private Integer prezzo;
	
	@NotBlank(message = "{annuncio.data.notblank}")
	private Date data;
	
	@NotBlank(message = "{annuncio.aperto.notblank}")
	private boolean aperto;
	
	@NotNull(message = "{annuncio.utente.notnull}")
	private UtenteDTO utente;
	
	@NotNull(message = "{annuncio.categorie.notnull}")
	private CategoriaDTO categorie;

	public AnnuncioDTO() {
		super();
	}

	public AnnuncioDTO(Long id, String testoAnnuncio, Integer prezzo, Date data, boolean aperto) {
		super();
		this.id = id;
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.data = data;
		this.aperto = aperto;
	}

	public AnnuncioDTO(Long id,String testoAnnuncio, Integer prezzo, Date data, boolean aperto,
			UtenteDTO utente, CategoriaDTO categorie) {
		super();
		this.id = id;
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.data = data;
		this.aperto = aperto;
		this.utente = utente;
		this.categorie = categorie;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTestoAnnuncio() {
		return testoAnnuncio;
	}

	public void setTestoAnnuncio(String testoAnnuncio) {
		this.testoAnnuncio = testoAnnuncio;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isAperto() {
		return aperto;
	}

	public void setAperto(boolean aperto) {
		this.aperto = aperto;
	}

	public UtenteDTO getUtenti() {
		return utente;
	}

	public void setUtenti(UtenteDTO utente) {
		this.utente = utente;
	}

	public CategoriaDTO getCategorie() {
		return categorie;
	}

	public void setCategorie(CategoriaDTO categorie) {
		this.categorie = categorie;
	}
	
	public Annuncio buildAnnuncioModel() {
		return new Annuncio(this.id, this.testoAnnuncio, this.prezzo, this.data, this.aperto,
				this.utente.buildUtenteModel(false));
	}
	
	public static AnnuncioDTO buildAnnuncioDTOFromModel(Annuncio annuncioModel, boolean includeUtenti) {
		AnnuncioDTO result = new AnnuncioDTO(annuncioModel.getId(), annuncioModel.getTestoAnnuncio(), annuncioModel.getPrezzo(),
				annuncioModel.getData(), annuncioModel.isAperto());

		if (includeUtenti)
			result.setUtenti(UtenteDTO.buildUtenteDTOFromModel(annuncioModel.getUtenteInserimento(), false));

		return result;
	}
	
	public static List<AnnuncioDTO> createAnnuncioDTOFromModelList(List<Annuncio> modelListInput,
			boolean includeUtente) {
		return modelListInput.stream().map(annuncioEntity -> {
			return AnnuncioDTO.buildAnnuncioDTOFromModel(annuncioEntity, includeUtente);
		}).collect(Collectors.toList());
	}

}
