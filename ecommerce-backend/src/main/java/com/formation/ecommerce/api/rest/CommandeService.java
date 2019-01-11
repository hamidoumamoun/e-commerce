package com.formation.ecommerce.api.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.ecommerce.api.dto.CommandeDto;
import com.formation.ecommerce.api.mappers.CommandeMapper;
import com.formation.ecommerce.entities.Commande;
import com.formation.ecommerce.enums.CommandeStatus;
import com.formation.ecommerce.soa.CommandeSoa;

@RestController
@RequestMapping("/api/commandes")
public class CommandeService {
	@Autowired
	private CommandeSoa commandeSoa;

	@GetMapping
	public List<CommandeDto> list() {
		return commandeSoa.getAllCommandes() //
				.parallelStream() //
				.map(entity -> CommandeMapper.map(entity)) //
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public CommandeDto consultation(@PathVariable(name = "id", required = true) Long id) {
		Commande commande = commandeSoa.getCommande(id);
		return CommandeMapper.map(commande);
	}

	@PutMapping("/traiter/{id}")
	public void traiter(@PathVariable(name = "id", required = true) Long id) {
		commandeSoa.traiter(id);
	}

	@PutMapping("/valider/{id}")
	public void valider(@PathVariable(name = "id", required = true) Long id) {
		commandeSoa.valider(id);
	}

	@PutMapping("/annuler/{id}")
	public void annuler(@PathVariable(name = "id", required = true) Long id) {
		commandeSoa.annuler(id);
	}

	@PostMapping
	public CommandeDto create(@RequestBody CommandeDto commande) {
		Commande entity = CommandeMapper.map(commande);
		entity.setStatus(CommandeStatus.TO_VALIDATE);
		entity = commandeSoa.create(entity);
		return CommandeMapper.map(entity);
	}
}
