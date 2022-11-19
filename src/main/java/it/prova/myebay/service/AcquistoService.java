package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.model.Acquisto;


public interface AcquistoService {
	public List<Acquisto> listAllElements();

	public Acquisto caricaSingoloElemento(Long id);
	
	public Acquisto caricaSingoloElementoEager(Long id);

	public void aggiorna(Acquisto acquistoInstance);

	public void inserisciNuovo(Acquisto acquistoInstance);

	public void rimuovi(Long idAcquistoToDelete);

	public List<Acquisto> findByExample(Acquisto example);

}
