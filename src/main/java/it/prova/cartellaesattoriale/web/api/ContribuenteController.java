package it.prova.cartellaesattoriale.web.api;

import java.util.ArrayList;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.prova.cartellaesattoriale.dto.CartellaEsattorialeDTO;
import it.prova.cartellaesattoriale.dto.ContribuenteBusinessDTO;
import it.prova.cartellaesattoriale.dto.ContribuenteDTO;
import it.prova.cartellaesattoriale.model.CartellaEsattoriale;
import it.prova.cartellaesattoriale.model.Contribuente;
import it.prova.cartellaesattoriale.model.Stato;
import it.prova.cartellaesattoriale.service.ContribuenteService;
import it.prova.cartellaesattoriale.web.api.exception.ContribuenteConCartellaEsattorialeException;
import it.prova.cartellaesattoriale.web.api.exception.ContribuenteNotFoundException;
import it.prova.cartellaesattoriale.web.api.exception.IdNotNullForInsertException;

@RestController
@RequestMapping("api/contribuente")
public class ContribuenteController {

	@Autowired
	private ContribuenteService contribuenteService;

	@GetMapping
	public List<ContribuenteDTO> getAll() {
		return ContribuenteDTO.createContribuenteDTOListFromModelList(contribuenteService.listAllElementsEager(), true);
	}

	@GetMapping("/{id}")
	public ContribuenteDTO findById(@PathVariable(value = "id", required = true) long id) {
		Contribuente contribuente = contribuenteService.caricaSingoloElementoConCartelleEsattoriali(id);

		if (contribuente == null)
			throw new ContribuenteNotFoundException("Contribuente not found con id: " + id);

		return ContribuenteDTO.buildContribuenteDTOFromModel(contribuente, true);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ContribuenteDTO createNew(@Valid @RequestBody ContribuenteDTO contribuenteInput) {
		if (contribuenteInput.getId() != null)
			throw new IdNotNullForInsertException("Non ?? ammesso fornire un id per la creazione");

		Contribuente contribuenteInserito = contribuenteService
				.inserisciNuovo(contribuenteInput.buildContribuenteModel());
		return ContribuenteDTO.buildContribuenteDTOFromModel(contribuenteInserito, false);
	}

	@PutMapping("/{id}")
	public ContribuenteDTO update(@Valid @RequestBody ContribuenteDTO contribuenteInput,
			@PathVariable(required = true) Long id) {
		Contribuente contribuente = contribuenteService.caricaSingoloElemento(id);

		if (contribuente == null)
			throw new ContribuenteNotFoundException("Contribuente not found con id: " + id);

		contribuenteInput.setId(id);
		Contribuente contribuenteAggiornato = contribuenteService.aggiorna(contribuenteInput.buildContribuenteModel());
		return ContribuenteDTO.buildContribuenteDTOFromModel(contribuenteAggiornato, false);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(required = true) Long id) {
		Contribuente contribuente = contribuenteService.caricaSingoloElementoConCartelleEsattoriali(id);

		if (contribuente == null)
			throw new ContribuenteNotFoundException("Contribuente not found con id: " + id);

		if (contribuente.getCartelleEsattoriali().size() > 0)
			throw new ContribuenteConCartellaEsattorialeException(
					"Sono presenti delle cartelle esattoriali in contribuente ");

		contribuenteService.rimuovi(contribuente);
	}

	@GetMapping("/verificaContenziosi")
	public List<ContribuenteBusinessDTO> verificaContenziosi() {

		List<ContribuenteBusinessDTO> contribuenti = ContribuenteBusinessDTO
				.createContribuenteBusinessDTOListFromModelList(contribuenteService.listAllElementsEager(), true);

			contribuenti.stream().forEach(item -> {

			item.getCartelleEsattoriali().stream().forEach(cartelle -> {

				if (cartelle.getStato().equals(Stato.IN_CONTENZIOSO)) {
					item.setDaAttenzionare(true);
				}
			});
		});
		return contribuenti;

	}

	@GetMapping("/reportContribuenti")
	public List<ContribuenteBusinessDTO> reportContribuenti() {

		List<ContribuenteBusinessDTO> contribuenti = ContribuenteBusinessDTO
				.createContribuenteBusinessDTOListFromModelList(contribuenteService.listAllElementsEager(), true);

		for (ContribuenteBusinessDTO contribuentiItem : contribuenti) {

			Integer importoTotale = 0;
			Integer conclusoEPagato = 0;
			Integer inContenzioso = 0;

			for (CartellaEsattorialeDTO cartelleItem : contribuentiItem.getCartelleEsattoriali()) {

				importoTotale += cartelleItem.getImporto();
				contribuentiItem.setImportoTotale(importoTotale);

				if (cartelleItem.getStato().equals(Stato.CONCLUSA)) {
					conclusoEPagato += cartelleItem.getImporto();
					contribuentiItem.setConclusoEPagato(conclusoEPagato);
				}

				if (cartelleItem.getStato().equals(Stato.IN_CONTENZIOSO)) {
					inContenzioso += cartelleItem.getImporto();
					contribuentiItem.setInContenzioso(inContenzioso);
				}

			}
		}

		return contribuenti;
	}
}
