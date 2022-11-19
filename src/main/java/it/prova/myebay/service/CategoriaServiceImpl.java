package it.prova.myebay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.myebay.model.Categoria;
import it.prova.myebay.repository.categoria.CategoriaRepository;
@Service
public class CategoriaServiceImpl implements CategoriaService{
	
	@Autowired
	private CategoriaRepository repository;

	@Override
	public List<Categoria> listAllElements() {
		return (List<Categoria>) repository.findAll();
	}

	@Override
	public Categoria caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Categoria caricaSingoloElementoEager(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Categoria categoriaInstance) {
		repository.save(categoriaInstance);	
		
	}

	@Override
	public void inserisciNuovo(Categoria categoriaInstance) {
		repository.save(categoriaInstance);	
		
	}

	@Override
	public void rimuovi(Long idCategoriaToDelete) {
		repository.deleteById(idCategoriaToDelete);
		
	}

	@Override
	public List<Categoria> findByExample(Categoria example) {
		// TODO Auto-generated method stub
		return null;
	}

}
