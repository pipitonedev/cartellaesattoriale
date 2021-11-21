package it.prova.cartellaesattoriale;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.cartellaesattoriale.model.CartellaEsattoriale;
import it.prova.cartellaesattoriale.model.Contribuente;
import it.prova.cartellaesattoriale.model.Stato;
import it.prova.cartellaesattoriale.service.CartellaEsattorialeService;
import it.prova.cartellaesattoriale.service.ContribuenteService;

@SpringBootApplication
public class CartellaesattorialeApplication implements CommandLineRunner {
	
	@Autowired
	private ContribuenteService contribuenteService;
	@Autowired
	private CartellaEsattorialeService cartellaService;

	public static void main(String[] args) {
		SpringApplication.run(CartellaesattorialeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		String mario = "Mario";
//		String rossi = "Rossi";
//		Contribuente contribuente1 = contribuenteService.findByNomeAndCognome(mario, rossi);
//
//		if (contribuente1 == null) {
//			contribuente1 = new Contribuente("Mario", "Rossi",
//					new SimpleDateFormat("dd/MM/yyyy").parse("10/07/1997"), "PPTVCN97L10E974Q", "Via Mosca");
//			contribuenteService.inserisciNuovo(contribuente1);
//		}
//
//		CartellaEsattoriale cartella = new CartellaEsattoriale("riscossione tasse", 15000, Stato.IN_CONTENZIOSO,
//				contribuente1);
//		if (cartellaService.findByDescrizioneAndImporto("riscossione tasse", 15000) != null)
//			cartellaService.inserisciNuovo(cartella);
//
//
//		String giovanni = "Giovanni";
//		String verdi = "Verdi";
//		Contribuente contribuente2 = contribuenteService.findByNomeAndCognome(giovanni, verdi);
//
//		if (contribuente2 == null) {
//			contribuente2 = new Contribuente("Giovanni", "Verdi",
//					new SimpleDateFormat("dd/MM/yyyy").parse("15/10/1960"), "EQ789JHYTR", "Via Trapani");
//			contribuenteService.inserisciNuovo(contribuente2);
//		}
//
//		CartellaEsattoriale cartella2 = new CartellaEsattoriale("multa", 200, Stato.IN_CONTENZIOSO, contribuente2);
//		if (cartellaService.findByDescrizioneAndImporto("multa", 200) != null)
//			cartellaService.inserisciNuovo(cartella2);
//
//	}


}
}
