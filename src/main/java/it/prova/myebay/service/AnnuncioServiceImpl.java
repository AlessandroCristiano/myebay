package it.prova.myebay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.repository.annuncio.AnnuncioRepository;


@Service
public class AnnuncioServiceImpl implements AnnuncioService{
	
	@Autowired
	private AnnuncioRepository repository;

	@Override
	public List<Annuncio> listAllElements() {
		return (List<Annuncio>) repository.findAll();
	}

	@Override
	public Annuncio caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Annuncio caricaSingoloElementoEager(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Annuncio annuncioInstance) {
		repository.save(annuncioInstance);		
	}

	@Override
	public void inserisciNuovo(Annuncio annuncioInstance) {
		repository.save(annuncioInstance);		
	}

	@Override
	public void rimuovi(Long idAnnuncioToDelete) {
		repository.deleteById(idAnnuncioToDelete);
		
	}

	@Override
	public List<Annuncio> findByExample(Annuncio example) {
		// TODO Auto-generated method stub
		return null;
	}

}
