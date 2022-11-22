package it.prova.myebay.dto;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.model.StatoUtente;

public class AnnuncioDTO {
	
	private Long id;
	
	@NotBlank(message = "{annuncio.testoAnnuncio.notblank}")
	private String testoAnnuncio;
	
	@NotNull(message = "{annuncio.prezzo.notnull}")
	private Integer prezzo;
	
	private Date data;
	
	private boolean aperto;
	
	private UtenteDTO utente;
	
	private Long[] categorieIds;

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
			UtenteDTO utente, Long[] categorieIds) {
		super();
		this.id = id;
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.data = data;
		this.aperto = aperto;
		this.utente = utente;
		this.categorieIds = categorieIds;
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

	public UtenteDTO getUtente() {
		return utente;
	}

	public void setUtente(UtenteDTO utente) {
		this.utente = utente;
	}
	
	public Long[] getCategorieIds() {
		return categorieIds;
	}

	public void setCategorieIds(Long[] categorieIds) {
		this.categorieIds = categorieIds;
	}

	public boolean isAperto2() {
		return this.aperto != false;
	}
	
	public Annuncio buildAnnuncioModel(boolean includeCategorie) {
		Annuncio result = new Annuncio(this.id, this.testoAnnuncio, this.prezzo, this.data, this.aperto,
				this.utente.buildUtenteModel(true));
		if (includeCategorie && categorieIds != null)
			result.setCategorie(Arrays.asList(categorieIds).stream().map(id -> new Categoria(id)).collect(Collectors.toSet()));

		return result;
	}
	
	public static AnnuncioDTO buildAnnuncioDTOFromModel(Annuncio annuncioModel, boolean includeUtenti, boolean includeCategorie) {
		AnnuncioDTO result = new AnnuncioDTO(annuncioModel.getId(), annuncioModel.getTestoAnnuncio(), annuncioModel.getPrezzo(),
				annuncioModel.getData(), annuncioModel.isAperto());

		if (includeUtenti)
			result.setUtente(UtenteDTO.buildUtenteDTOFromModel(annuncioModel.getUtenteInserimento(), false));
		
		if (annuncioModel.getCategorie() != null && includeCategorie && !annuncioModel.getCategorie().isEmpty())
			result.categorieIds = annuncioModel.getCategorie().stream().map(r -> r.getId()).collect(Collectors.toList())
					.toArray(new Long[] {});

		return result;
	}
	
	public static List<AnnuncioDTO> createAnnuncioDTOFromModelList(List<Annuncio> modelListInput,
			boolean includeUtente, boolean includeCategorie) {
		return modelListInput.stream().map(annuncioEntity -> {
			return AnnuncioDTO.buildAnnuncioDTOFromModel(annuncioEntity, includeUtente, includeCategorie);
		}).collect(Collectors.toList());
	}

}
