package com.formation.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "table_produit")
public class Produit implements Serializable {

	private static final long serialVersionUID = -4495945604793488359L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 5)
	private String code;
	
	@Column(nullable = false, length = 50)
	private String nom;
	
	@Column(nullable = false)
	private long quantite;
	
	@Column(nullable = false, scale = 5, precision = 2)
	private double prixUnitaire;
}
