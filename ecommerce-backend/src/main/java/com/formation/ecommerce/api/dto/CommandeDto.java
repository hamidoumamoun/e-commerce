package com.formation.ecommerce.api.dto;

import java.io.Serializable;
import java.util.List;

import com.formation.ecommerce.enums.CommandeStatus;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(exclude = {"lignes"})
@Getter
@Setter
public class CommandeDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private double montant;

	private CommandeStatus status;

	private List<LigneCommandeDto> lignes;
}
