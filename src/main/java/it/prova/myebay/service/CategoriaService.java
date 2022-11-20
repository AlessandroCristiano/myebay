package it.prova.myebay.service;

import java.util.List;

import it.prova.myebay.model.Categoria;

public interface CategoriaService {
	
	public List<Categoria> listAllElements();

	public Categoria caricaSingoloElemento(Long id);
	
	public Categoria caricaSingoloElementoEager(Long id);

	public void aggiorna(Categoria categoriaInstance);

	public void inserisciNuovo(Categoria categoriaInstance);

	public void rimuovi(Long idCategoriaToDelete);

	public List<Categoria> findByExample(Categoria example);
	
	public List<Categoria> cercaCategorieFromId(Long[] ids);
	
	

}
