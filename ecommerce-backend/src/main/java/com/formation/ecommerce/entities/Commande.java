package com.formation.ecommerce.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.formation.ecommerce.enums.CommandeStatus;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"lignes"})
@ToString
@Entity
@Table(name = "table_commande")
public class Commande implements Serializable {

	private static final long serialVersionUID = -4495945604793488359L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private double montant;

	@Column(nullable = false)
	private CommandeStatus status;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "commande")
	private Set<LigneCommande> lignes;

}
