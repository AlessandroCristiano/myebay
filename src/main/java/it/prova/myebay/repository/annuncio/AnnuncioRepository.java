package it.prova.myebay.repository.annuncio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.myebay.model.Annuncio;

public interface AnnuncioRepository extends CrudRepository<Annuncio, Long>, CustomAnnuncioRepository{
	
	@Query("from Annuncio a join fetch a.utenteInserimento where a.id = ?1")
	Annuncio findByIdEager(Long id);
	
	@Query("from Annuncio a left join fetch a.utenteInserimento u where a.aperto=true and u.id = ?1")
	List<Annuncio> FindAllAnnunciById(Long id);
	
	@Query("from Annuncio a left join fetch a.categorie where a.id = ?1")
	Optional<Annuncio> findByIdConCategorie(Long id);

}
