package com.formation.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(exclude= {"commande"})
@ToString
@Entity
@Table(name = "table_commande_ligne")
public class LigneCommande implements Serializable {

	private static final long serialVersionUID = -4495945604793488359L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private long quantite;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "produit_id", nullable = false)
	private Produit produit;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commande_id", nullable = false)
	private Commande commande;

}
