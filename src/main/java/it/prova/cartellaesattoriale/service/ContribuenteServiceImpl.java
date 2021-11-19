package it.prova.cartellaesattoriale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.cartellaesattoriale.model.Contribuente;
import it.prova.cartellaesattoriale.repository.contribuente.ContribuenteRepository;

@Service
public class ContribuenteServiceImpl implements ContribuenteService {

	@Autowired
	private ContribuenteRepository repository;

	@Override
	public List<Contribuente> listAllElements() {
		return (List<Contribuente>) repository.findAll();
	}

	@Override
	public List<Contribuente> listAllElementsEager() {
		return (List<Contribuente>) repository.findAllEager();
	}

	@Override
	public Contribuente caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Contribuente caricaSingoloElementoConCartelleEsattoriali(Long id) {
		return repository.findByIdEager(id);
	}

	@Transactional
	public Contribuente aggiorna(Contribuente contribuenteInstance) {
		return repository.save(contribuenteInstance);
	}

	@Transactional
	public Contribuente inserisciNuovo(Contribuente contribuenteInstance) {
		return repository.save(contribuenteInstance);
	}

	@Transactional
	public void rimuovi(Contribuente contribuenteInstance) {
		repository.delete(contribuenteInstance);

	}

	@Override
	public List<Contribuente> findByExample(Contribuente example) {
		return repository.findByExample(example);
	}

	@Override
	public List<Contribuente> cercaByCognomeENomeILike(String term) {
		return repository.findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByNomeAsc(term, term);
	}

}
