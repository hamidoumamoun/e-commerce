package com.formation.ecommerce.api.mappers;

import com.formation.ecommerce.api.dto.ProduitDto;
import com.formation.ecommerce.entities.Produit;

public class ProduitMapper {

	public static ProduitDto map(Produit produitEntity) {
		ProduitDto dto = new ProduitDto();
		dto.setId(produitEntity.getId());
		dto.setCode(produitEntity.getCode());
		dto.setNom(produitEntity.getNom());
		dto.setQuantite(produitEntity.getQuantite());
		dto.setPrixUnitaire(produitEntity.getPrixUnitaire());
		return dto;
	}

	public static Produit map(ProduitDto produitDto) {
		Produit entity = new Produit();
		entity.setId(produitDto.getId());
		entity.setCode(produitDto.getCode());
		entity.setNom(produitDto.getNom());
		entity.setQuantite(produitDto.getQuantite());
		entity.setPrixUnitaire(produitDto.getPrixUnitaire());
		return entity;
	}
	
}
