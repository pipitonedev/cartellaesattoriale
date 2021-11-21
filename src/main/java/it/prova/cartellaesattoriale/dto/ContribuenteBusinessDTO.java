package it.prova.cartellaesattoriale.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.cartellaesattoriale.model.Contribuente;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContribuenteBusinessDTO {

	private Long id;

	@NotBlank(message = "{nome.notblank}")
	private String nome;

	@NotBlank(message = "{nome.notblank}")
	private String cognome;

	@NotNull(message = "{nome.notblank}")
	private Date dataNascita;

	@NotBlank(message = "{nome.notblank}")
	private String codiceFiscale;

	@NotBlank(message = "{nome.notblank}")
	private String indirizzo;

	private Integer importoTotale;
	private Integer conclusoEPagato;
	private Integer inContenzioso;
	private Boolean daAttenzionare;

	@JsonIgnoreProperties(value = { "contribuente" })
	private Set<CartellaEsattorialeDTO> CartelleEsattoriali = new HashSet<CartellaEsattorialeDTO>();

	public ContribuenteBusinessDTO() {
	}

	public ContribuenteBusinessDTO(Long id, String nome, String cognome, Date dataNascita, String codiceFiscale,
			String indirizzo, Integer importoTotale, Integer conclusoEPagato, Integer inContenzioso,
			Boolean daAttenzionare, Set<CartellaEsattorialeDTO> CartelleEsattoriali) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
		this.importoTotale = importoTotale;
		this.conclusoEPagato = conclusoEPagato;
		this.inContenzioso = inContenzioso;
		this.daAttenzionare = daAttenzionare;
		this.CartelleEsattoriali = CartelleEsattoriali;
	}

	public ContribuenteBusinessDTO(Long id, @NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{nome.notblank}") String cognome,
			@NotNull(message = "{nome.notblank}") Date dataNascita,
			@NotBlank(message = "{nome.notblank}") String codiceFiscale,
			@NotBlank(message = "{nome.notblank}") String indirizzo, Set<CartellaEsattorialeDTO> CartelleEsattoriali) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
		this.CartelleEsattoriali = CartelleEsattoriali;
	}

	public ContribuenteBusinessDTO(@NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{nome.notblank}") String cognome,
			@NotNull(message = "{nome.notblank}") Date dataNascita,
			@NotBlank(message = "{nome.notblank}") String codiceFiscale,
			@NotBlank(message = "{nome.notblank}") String indirizzo, Set<CartellaEsattorialeDTO> CartelleEsattoriali) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
		this.CartelleEsattoriali = CartelleEsattoriali;
	}

	public ContribuenteBusinessDTO(Long id, @NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{nome.notblank}") String cognome,
			@NotNull(message = "{nome.notblank}") Date dataNascita,
			@NotBlank(message = "{nome.notblank}") String codiceFiscale,
			@NotBlank(message = "{nome.notblank}") String indirizzo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Integer getImportoTotale() {
		return importoTotale;
	}

	public void setImportoTotale(Integer importoTotale) {
		this.importoTotale = importoTotale;
	}

	public Integer getConclusoEPagato() {
		return conclusoEPagato;
	}

	public void setConclusoEPagato(Integer conclusoEPagato) {
		this.conclusoEPagato = conclusoEPagato;
	}

	public Integer getInContenzioso() {
		return inContenzioso;
	}

	public void setInContenzioso(Integer inContenzioso) {
		this.inContenzioso = inContenzioso;
	}

	public Boolean getDaAttenzionare() {
		return daAttenzionare;
	}

	public void setDaAttenzionare(Boolean daAttenzionare) {
		this.daAttenzionare = daAttenzionare;
	}

	public Set<CartellaEsattorialeDTO> getCartelleEsattoriali() {
		return CartelleEsattoriali;
	}

	public void setCartelleEsattoriali(Set<CartellaEsattorialeDTO> cartelleEsattoriali) {
		CartelleEsattoriali = cartelleEsattoriali;
	}

	public Contribuente buildContribuenteModel() {
		return new Contribuente(this.id, this.nome, this.cognome, this.dataNascita, this.codiceFiscale, this.indirizzo);
	}

	public static ContribuenteBusinessDTO buildContribuenteBusinessDTOFromModel(Contribuente contribuenteModel,
			boolean includeCartelle) {
		ContribuenteBusinessDTO result = new ContribuenteBusinessDTO(contribuenteModel.getId(),
				contribuenteModel.getNome(), contribuenteModel.getCognome(), contribuenteModel.getDataDiNascita(),
				contribuenteModel.getCodiceFiscale(), contribuenteModel.getIndirizzo());
		if (includeCartelle)
			result.setCartelleEsattoriali(CartellaEsattorialeDTO
					.createCartellaEsattorialeDTOSetFromModelSet(contribuenteModel.getCartelleEsattoriali(), false));
		return result;
	}

	public static List<ContribuenteBusinessDTO> createContribuenteBusinessDTOListFromModelList(
			List<Contribuente> modelListInput, boolean includeCartelle) {
		return modelListInput.stream().map(contribuenteEntity -> {
			ContribuenteBusinessDTO result = ContribuenteBusinessDTO
					.buildContribuenteBusinessDTOFromModel(contribuenteEntity, includeCartelle);
			if (includeCartelle)
				result.setCartelleEsattoriali(CartellaEsattorialeDTO.createCartellaEsattorialeDTOSetFromModelSet(
						contribuenteEntity.getCartelleEsattoriali(), false));
			return result;
		}).collect(Collectors.toList());
	}

}
