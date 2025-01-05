package tn.iit.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor // obligatoire selon JPA
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity // --> il y a mapping avec la BD
@Table(name = "t_compte")
public class Compte implements Serializable /* obligatoire selon JPA */ {

	private static final long serialVersionUID = 1L;


	@Id // rib --> PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
	@Include // equals et hashCode générés à base du RIB
	private Integer rib;

	@Column(name = "client")
	private String nomClient;

	private float solde;


	@ManyToOne
	@JoinColumn(name = "cin", insertable = false, updatable = false)
	private Client client;
	private  String cin;

	// Constructeur
	public Compte(String nomClient, float solde, String cin) {
		super();
		this.nomClient = nomClient;
		this.solde = solde;
		this.cin = cin;
	}
}