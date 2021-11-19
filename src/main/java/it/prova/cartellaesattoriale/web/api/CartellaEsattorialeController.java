package it.prova.cartellaesattoriale.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.cartellaesattoriale.dto.CartellaEsattorialeDTO;
import it.prova.cartellaesattoriale.model.CartellaEsattoriale;
import it.prova.cartellaesattoriale.service.CartellaEsattorialeService;
import it.prova.cartellaesattoriale.web.api.exception.CartellaEsattorialeNotFoundException;
import it.prova.cartellaesattoriale.web.api.exception.IdNotNullForInsertException;

@RestController
@RequestMapping("api/cartellaesattoriale")
public class CartellaEsattorialeController {

	@Autowired
	private CartellaEsattorialeService cartellaEsattorialeService;

	@GetMapping
	public List<CartellaEsattorialeDTO> getAll() {
		return CartellaEsattorialeDTO
				.createCartellaEsattorialeDTOListFromModelList(cartellaEsattorialeService.listAllElements(true), true);
	}

	@PostMapping
	public CartellaEsattorialeDTO createNew(@Valid @RequestBody CartellaEsattorialeDTO cartellaEsattorialeInput) {
		if (cartellaEsattorialeInput.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

		CartellaEsattoriale cartellaEsattorialeInserita = cartellaEsattorialeService
				.inserisciNuovo(cartellaEsattorialeInput.buildCartellaEsattorialeModel());
		return CartellaEsattorialeDTO.buildCartellaEsattorialeDTOFromModel(cartellaEsattorialeInserita, true);
	}

	@GetMapping("/{id}")
	public CartellaEsattorialeDTO findById(@PathVariable(value = "id", required = true) long id) {
		CartellaEsattoriale cartella = cartellaEsattorialeService.caricaSingoloElementoEager(id);

		if (cartella == null)
			throw new CartellaEsattorialeNotFoundException("Cartella Esattoriale not found con id: " + id);

		return CartellaEsattorialeDTO.buildCartellaEsattorialeDTOFromModel(cartella, true);
	}

	@PutMapping("/{id}")
	public CartellaEsattorialeDTO update(@Valid @RequestBody CartellaEsattorialeDTO cartellaEsattorialeInput,
			@PathVariable(required = true) Long id) {
		CartellaEsattoriale cartellaEsattoriale = cartellaEsattorialeService.caricaSingoloElementoEager(id);

		if (cartellaEsattoriale == null)
			throw new CartellaEsattorialeNotFoundException("CartellaEsattoriale not found con id: " + id);

		cartellaEsattorialeInput.setId(id);
		CartellaEsattoriale cartellaEsattorialeAggiornata = cartellaEsattorialeService
				.aggiorna(cartellaEsattorialeInput.buildCartellaEsattorialeModel());
		return CartellaEsattorialeDTO.buildCartellaEsattorialeDTOFromModel(cartellaEsattorialeAggiornata, false);
	}

	@PostMapping("/search")
	public List<CartellaEsattorialeDTO> search(@RequestBody CartellaEsattorialeDTO example) {
		return CartellaEsattorialeDTO.createCartellaEsattorialeDTOListFromModelList(
				cartellaEsattorialeService.findByExample(example.buildCartellaEsattorialeModel()), false);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(required = true) Long id) {
		CartellaEsattoriale cartellaEsattoriale = cartellaEsattorialeService.caricaSingoloElementoEager(id);

		if (cartellaEsattoriale == null)
			throw new CartellaEsattorialeNotFoundException("Cartella Esattoriale not found con id: " + id);

		cartellaEsattorialeService.rimuovi(cartellaEsattoriale);
	}

}
