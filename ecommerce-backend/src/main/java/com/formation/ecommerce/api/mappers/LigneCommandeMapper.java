package com.formation.ecommerce.api.mappers;

import com.formation.ecommerce.api.dto.LigneCommandeDto;
import com.formation.ecommerce.entities.LigneCommande;

public class LigneCommandeMapper {

	public static LigneCommandeDto map(LigneCommande entity) {
		LigneCommandeDto dto = new LigneCommandeDto();
		dto.setId(entity.getId());
		dto.setProduit(ProduitMapper.map(entity.getProduit()));
		dto.setQuantite(entity.getQuantite());
		return dto;
	}

	public static LigneCommande map(LigneCommandeDto dto) {
		LigneCommande entity = new LigneCommande();
		entity.setId(dto.getId());
		entity.setProduit(ProduitMapper.map(dto.getProduit()));
		entity.setQuantite(dto.getQuantite());
		return entity;
	}
	
}
