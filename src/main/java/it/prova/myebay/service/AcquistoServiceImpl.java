package it.prova.myebay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.myebay.model.Acquisto;
import it.prova.myebay.repository.acquisto.AcquistoRepository;

@Service
public class AcquistoServiceImpl implements AcquistoService{
	
	@Autowired
	private AcquistoRepository repository;

	@Transactional(readOnly = true)
	public List<Acquisto> listAllElements() {
		return (List<Acquisto>) repository.findAll();
	}

	@Transactional(readOnly = true)
	public Acquisto caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public Acquisto caricaSingoloElementoEager(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public void aggiorna(Acquisto acquistoInstance) {
		repository.save(acquistoInstance);
		
	}

	@Transactional
	public void inserisciNuovo(Acquisto acquistoInstance) {
		repository.save(acquistoInstance);
		
	}

	@Transactional
	public void rimuovi(Long idAcquistoToDelete) {
		repository.deleteById(idAcquistoToDelete);
		
	}

	@Transactional(readOnly = true)
	public List<Acquisto> findByExample(Acquisto example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Acquisto> FindAllAcquistiByIdUtente(Long id) {
		return repository.FindAllAcquistiByIdUtente(id);
	}

}
