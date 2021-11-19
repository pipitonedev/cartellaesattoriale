package it.prova.cartellaesattoriale.repository.contribuente;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.cartellaesattoriale.model.Contribuente;

public interface ContribuenteRepository extends CrudRepository<Contribuente, Long>, CustomContribuenteRepository {

	List<Contribuente> findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByNomeAsc(String cognome,
			String nome);

	@Query("select distinct c from Contribuente r left join fetch c.cartelleEsattoriali ")
	List<Contribuente> findAllEager();

	@Query("from Contribuente c left join fetch c.cartelleEsattoriali where c.id=?1")
	Contribuente findByIdEager(Long idContribuente);
}
