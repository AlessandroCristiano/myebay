package it.prova.myebay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.myebay.model.Acquisto;
import it.prova.myebay.repository.acquisto.AcquistoRepository;

@Service
public class AcquistoServiceImpl implements AcquistoService{
	
	@Autowired
	private AcquistoRepository repository;

	@Override
	public List<Acquisto> listAllElements() {
		return (List<Acquisto>) repository.findAll();
	}

	@Override
	public Acquisto caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Acquisto caricaSingoloElementoEager(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Acquisto acquistoInstance) {
		repository.save(acquistoInstance);
		
	}

	@Override
	public void inserisciNuovo(Acquisto acquistoInstance) {
		repository.save(acquistoInstance);
		
	}

	@Override
	public void rimuovi(Long idAcquistoToDelete) {
		repository.deleteById(idAcquistoToDelete);
		
	}

	@Override
	public List<Acquisto> findByExample(Acquisto example) {
		// TODO Auto-generated method stub
		return null;
	}

}
