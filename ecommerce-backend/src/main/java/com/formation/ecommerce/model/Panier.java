package com.formation.ecommerce.model;

import java.io.Serializable;
import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Panier implements Serializable {

	private static final long serialVersionUID = 81598582080308441L;
	
	private Set<LignePanier> lignes;
	private double montantTotal;
}
