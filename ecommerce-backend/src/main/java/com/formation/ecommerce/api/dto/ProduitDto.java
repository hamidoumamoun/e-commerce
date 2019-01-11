package com.formation.ecommerce.api.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
public class ProduitDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String code;

	private String nom;

	private long quantite;

	private double prixUnitaire;
	
}
