package com.formation.ecommerce.api.mappers;

import java.util.stream.Collectors;

import com.formation.ecommerce.api.dto.CommandeDto;
import com.formation.ecommerce.entities.Commande;

public class CommandeMapper {

	public static CommandeDto map(Commande entity) {
		CommandeDto dto = new CommandeDto();
		dto.setId(entity.getId());
		dto.setStatus(entity.getStatus());
		dto.setMontant(entity.getMontant());
		dto.setLignes(entity.getLignes() //
				.parallelStream() //
				.map(ligne -> LigneCommandeMapper.map(ligne)) //
				.collect(Collectors.toList()));
		return dto;
	}

	public static Commande map(CommandeDto dto) {
		Commande entity = new Commande();
		entity.setId(dto.getId());
		entity.setStatus(dto.getStatus());
		entity.setMontant(dto.getMontant());
		entity.setLignes(dto.getLignes() //
				.parallelStream() //s
				.map(ligne -> LigneCommandeMapper.map(ligne)) //
				.collect(Collectors.toSet()));
		return entity;
	}

}
