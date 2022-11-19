package it.prova.myebay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.myebay.model.Utente;
import it.prova.myebay.repository.utente.UtenteRepository;
@Service
public class UtenteServiceImpl implements UtenteService{
	
	@Autowired
	private UtenteRepository repository;
	
	@Override
	public List<Utente> listAllElements() {
		return (List<Utente>) repository.findAll();
	}

	@Override
	public Utente caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Utente caricaSingoloElementoEager(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Utente utenteInstance) {
		repository.save(utenteInstance);
		
	}

	@Override
	public void inserisciNuovo(Utente utenteInstance) {
		repository.save(utenteInstance);
		
	}

	@Override
	public void rimuovi(Long idUtenteToDelete) {
		repository.deleteById(idUtenteToDelete);
		
	}

	@Override
	public List<Utente> findByExample(Utente example) {
		// TODO Auto-generated method stub
		return null;
	}

}
