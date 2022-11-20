package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.model.Annuncio;

public interface AnnuncioService {
	
	public List<Annuncio> listAllElements();

	public Annuncio caricaSingoloElemento(Long id);
	
	public Annuncio caricaSingoloElementoEager(Long id);
	
	public Annuncio caricaSingoloElementoConCategorie(Long id);

	public void aggiorna(Annuncio annuncioInstance);

	public void inserisciNuovo(Annuncio annuncioInstance);

	public void rimuovi(Long idAnnuncioToDelete);

	public List<Annuncio> findByExample(Annuncio example);
	
	public List<Annuncio> findByExampleEager(Annuncio example);
	
	public 	List<Annuncio> FindAllAnnunciById(Long id);

}
