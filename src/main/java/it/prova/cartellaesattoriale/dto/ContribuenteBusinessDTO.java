package it.prova.cartellaesattoriale.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.cartellaesattoriale.model.Contribuente;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContribuenteBusinessDTO {

	private Long id;

	@NotBlank(message = "{nome.notblank}")
	private String nome;

	@NotBlank(message = "{cognome.notblank}")
	private String cognome;

	@NotNull(message = "{dataDiNascita.notnull}")
	private Date dataDiNascita;

	@NotBlank(message = "{codiceFiscale.notblank}")
	private String codiceFiscale;

	@NotBlank(message = "{indirizzo.notblank}")
	private String indirizzo;

	private Integer importoTotale;
	private Integer conclusoEPagato;
	private Integer inContenzioso;
	private Boolean daAttenzionare;

	@JsonIgnore
	@JsonIgnoreProperties(value = { "contribuente" })
	private Set<CartellaEsattorialeDTO> cartelleEsattoriali = new HashSet<CartellaEsattorialeDTO>(0);

	public ContribuenteBusinessDTO() {
		// TODO Auto-generated constructor stub
	}

	public ContribuenteBusinessDTO(Long id, String nome, String cognome, Date dataDiNascita, String codiceFiscale,
			String indirizzo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
	}

	public ContribuenteBusinessDTO(String nome, String cognome, Date dataDiNascita, String codiceFiscale,
			String indirizzo) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
	}

	public ContribuenteBusinessDTO(Long id, String nome, String cognome, Date dataDiNascita, String codiceFiscale,
			String indirizzo, Set<CartellaEsattorialeDTO> cartelleEsattoriali) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.codiceFiscale = codiceFiscale;
		this.indirizzo = indirizzo;
		this.cartelleEsattoriali = cartelleEsattoriali;
	}

	public Integer getInContenzioso() {
		return inContenzioso;
	}

	public void setInContenzioso(Integer inContenzioso) {
		this.inContenzioso = inContenzioso;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
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

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
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

	public Set<CartellaEsattorialeDTO> getCartelleEsattoriali() {
		return cartelleEsattoriali;
	}

	public void setCartelleEsattoriali(Set<CartellaEsattorialeDTO> cartelleEsattoriali) {
		this.cartelleEsattoriali = cartelleEsattoriali;
	}

	public Boolean getDaAttenzionare() {
		return daAttenzionare;
	}

	public void setDaAttenzionare(Boolean daAttenzionare) {
		this.daAttenzionare = daAttenzionare;
	}

	// ##################################################
	public Contribuente buildContribuenteModel() {
		return new Contribuente(this.id, this.nome, this.cognome, this.dataDiNascita, this.codiceFiscale,
				this.indirizzo);
	}

	public static ContribuenteBusinessDTO buildContribuenteDTOFromModel(Contribuente contribuenteModel,
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
			ContribuenteBusinessDTO result = ContribuenteBusinessDTO.buildContribuenteDTOFromModel(contribuenteEntity,
					includeCartelle);
			if (includeCartelle)
				result.setCartelleEsattoriali(CartellaEsattorialeDTO.createCartellaEsattorialeDTOSetFromModelSet(
						contribuenteEntity.getCartelleEsattoriali(), false));
			return result;
		}).collect(Collectors.toList());
	}

}
