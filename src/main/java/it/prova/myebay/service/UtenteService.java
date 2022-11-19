package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.model.Utente;

public interface UtenteService {
	
	public List<Utente> listAllElements();

	public Utente caricaSingoloElemento(Long id);
	
	public Utente caricaSingoloElementoEager(Long id);

	public void aggiorna(Utente utenteInstance);

	public void inserisciNuovo(Utente utenteInstance);

	public void rimuovi(Long idUtenteToDelete);

	public List<Utente> findByExample(Utente example);

}
