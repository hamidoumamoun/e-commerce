package com.formation.ecommerce.model;

import java.io.Serializable;

import com.formation.ecommerce.entities.Produit;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LignePanier implements Serializable {

	private Produit produit;

	private int quantite;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produit == null) ? 0 : (produit.getId() == null ? 0 : produit.getId().intValue()));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LignePanier other = (LignePanier) obj;
		if (produit == null) {
			if (other.produit != null)
				return false;
		} else if (produit.getId() == null) {
			if (other.produit != null && other.produit.getId() != null) {
				return false;
			}
		}
		return other.produit != null && produit.getId().equals(other.produit.getId());
	}

}
