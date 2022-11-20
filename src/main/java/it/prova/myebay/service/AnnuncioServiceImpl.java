package it.prova.myebay.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.repository.annuncio.AnnuncioRepository;


@Service
public class AnnuncioServiceImpl implements AnnuncioService{
	
	@Autowired
	private AnnuncioRepository repository;

	@Transactional(readOnly = true)
	public List<Annuncio> listAllElements() {
		return (List<Annuncio>) repository.findAll();
	}

	@Transactional(readOnly = true)
	public Annuncio caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public Annuncio caricaSingoloElementoEager(Long id) {	
		return repository.findByIdEager(id);
	}

	@Transactional
	public void aggiorna(Annuncio annuncioInstance) {
		repository.save(annuncioInstance);		
	}

	@Transactional
	public void inserisciNuovo(Annuncio annuncioInstance) {
		repository.save(annuncioInstance);		
	}

	@Transactional
	public void rimuovi(Long idAnnuncioToDelete) {
		repository.deleteById(idAnnuncioToDelete);
		
	}

	@Transactional(readOnly = true)
	public List<Annuncio> findByExample(Annuncio example) {
		return repository.findByExample(example);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Annuncio> findByExampleEager(Annuncio example) {
		return repository.findByExampleEager(example);
	}

}
