package it.prova.cartellaesattoriale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.cartellaesattoriale.model.CartellaEsattoriale;
import it.prova.cartellaesattoriale.repository.cartellaEsattoriale.CartellaEsattorialeRepository;

@Service
public class CartellaEsattorialeServiceImpl implements CartellaEsattorialeService {

	@Autowired
	private CartellaEsattorialeRepository repository;

	@Override
	public List<CartellaEsattoriale> listAllElements(boolean eager) {
		if (eager)
			return (List<CartellaEsattoriale>) repository.findAllCartellaEsattorialeEager();

		return (List<CartellaEsattoriale>) repository.findAll();
	}

	@Override
	public CartellaEsattoriale caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public CartellaEsattoriale caricaSingoloElementoEager(Long id) {
		return repository.findSingleCartellaEsattorialeEager(id);
	}

	@Override
	public CartellaEsattoriale aggiorna(CartellaEsattoriale cartellaEsattorialeInstance) {
		return repository.save(cartellaEsattorialeInstance);
	}

	@Override
	public CartellaEsattoriale inserisciNuovo(CartellaEsattoriale cartellaEsattorialeInstance) {
		return repository.save(cartellaEsattorialeInstance);
	}

	@Override
	public void rimuovi(CartellaEsattoriale cartellaEsattorialeInstance) {
		repository.delete(cartellaEsattorialeInstance);

	}

	@Override
	public List<CartellaEsattoriale> findByExample(CartellaEsattoriale example) {
		return repository.findByExample(example);
	}

	@Override
	public List<CartellaEsattoriale> findByDescrizioneAndImporto(String descrizione, Integer importo) {
		return repository.findByDescrizioneAndImporto(descrizione, importo);
	}

}
