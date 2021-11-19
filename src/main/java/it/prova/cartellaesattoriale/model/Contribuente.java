package it.prova.cartellaesattoriale.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "contribuente")
public class Contribuente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "cognome")
	private String cognome;

	@Column(name = "datadinascita")
	private Date dataDiNascita;

	@Column(name = "codFiscale")
	private String codiceFiscale;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contribuente")
	private Set<CartellaEsattoriale> cartelleEsattoriali = new HashSet<CartellaEsattoriale>(0);

}
