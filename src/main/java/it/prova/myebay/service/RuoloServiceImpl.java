package it.prova.myebay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.myebay.model.Ruolo;
import it.prova.myebay.repository.ruolo.RuoloRepository;
@Service
public class RuoloServiceImpl implements RuoloService{
	
	@Autowired
	private RuoloRepository repository;

	@Override
	public List<Ruolo> listAllElements() {
		return (List<Ruolo>) repository.findAll();
	}

	@Override
	public Ruolo caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Ruolo caricaSingoloElementoEager(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Ruolo ruoloInstance) {
		repository.save(ruoloInstance);
		
	}

	@Override
	public void inserisciNuovo(Ruolo ruoloInstance) {
		repository.save(ruoloInstance);
		
	}

	@Override
	public void rimuovi(Long idRuoloToDelete) {
		repository.deleteById(idRuoloToDelete);
		
	}

	@Override
	public List<Ruolo> findByExample(Ruolo example) {
		// TODO Auto-generated method stub
		return null;
	}

}
