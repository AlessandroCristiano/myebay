package it.prova.myebay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.myebay.model.Categoria;
import it.prova.myebay.repository.categoria.CategoriaRepository;
@Service
public class CategoriaServiceImpl implements CategoriaService{
	
	@Autowired
	private CategoriaRepository repository;

	@Transactional(readOnly = true)
	public List<Categoria> listAllElements() {
		return (List<Categoria>) repository.findAll();
	}

	@Transactional(readOnly = true)
	public Categoria caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public Categoria caricaSingoloElementoEager(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public void aggiorna(Categoria categoriaInstance) {
		repository.save(categoriaInstance);	
		
	}

	@Transactional
	public void inserisciNuovo(Categoria categoriaInstance) {
		repository.save(categoriaInstance);	
		
	}

	@Transactional
	public void rimuovi(Long idCategoriaToDelete) {
		repository.deleteById(idCategoriaToDelete);
		
	}

	@Transactional(readOnly = true)
	public List<Categoria> findByExample(Categoria example) {
		// TODO Auto-generated method stub
		return null;
	}

}
