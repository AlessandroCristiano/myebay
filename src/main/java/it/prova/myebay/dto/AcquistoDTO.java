package it.prova.myebay.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.prova.myebay.model.Acquisto;

public class AcquistoDTO {
	
	private Long id;
	
	@NotBlank(message = "{acquisto.descrizione.notblank}")
	private String descrizione;
	
	@NotBlank(message = "{acquisto.data.notblank}")
	private Date data;
	
	@NotBlank(message = "{acquisto.prezzo.notblank}")
	private Integer prezzo;
	
	@NotNull(message = "{acquisto.utente.notnull}")
	private UtenteDTO utente;

	public AcquistoDTO() {
		super();
	}

	public AcquistoDTO(Long id, @NotBlank(message = "{acquisto.descrizione.notblank}") String descrizione,
			@NotBlank(message = "{acquisto.data.notblank}") Date data,
			@NotBlank(message = "{acquisto.prezzo.notblank}") Integer prezzo) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.data = data;
		this.prezzo = prezzo;
	}

	public AcquistoDTO(Long id, @NotBlank(message = "{acquisto.descrizione.notblank}") String descrizione,
			@NotBlank(message = "{acquisto.data.notblank}") Date data,
			@NotBlank(message = "{acquisto.prezzo.notblank}") Integer prezzo,
			@NotNull(message = "{acquisto.utente.notnull}") UtenteDTO utente) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.data = data;
		this.prezzo = prezzo;
		this.utente = utente;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public UtenteDTO getUtente() {
		return utente;
	}

	public void setUtente(UtenteDTO utente) {
		this.utente = utente;
	}
	
	public Acquisto buildAcquistoModel() {
		return new Acquisto(this.id, this.descrizione, this.data, this.prezzo, this.utente.buildUtenteModel(false));
	}
	
	public static AcquistoDTO buildAcquistoDTOFromModel(Acquisto acquistoModel, boolean includeUtente) {
		AcquistoDTO result = new AcquistoDTO(acquistoModel.getId(), acquistoModel.getDescrizione(), acquistoModel.getData(), acquistoModel.getPrezzo());
		
		if(includeUtente)
			result.setUtente(UtenteDTO.buildUtenteDTOFromModel(acquistoModel.getUtenteAcquirente(), false));
		
		return result;
	}

	public static List<AcquistoDTO> createAcquistoDTOFromModelList(List<Acquisto> modelListInput, boolean includeUtente) {
		return modelListInput.stream().map(acquistoEntity -> {
			return AcquistoDTO.buildAcquistoDTOFromModel(acquistoEntity, includeUtente);
		}).collect(Collectors.toList());
	}

}
