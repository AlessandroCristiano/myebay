package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.model.Ruolo;

public interface RuoloService {
	
	public List<Ruolo> listAllElements();

	public Ruolo caricaSingoloElemento(Long id);
	
	public Ruolo caricaSingoloElementoEager(Long id);

	public void aggiorna(Ruolo ruoloInstance);

	public void inserisciNuovo(Ruolo ruoloInstance);

	public void rimuovi(Long idRuoloToDelete);

	public List<Ruolo> findByExample(Ruolo example);

}
