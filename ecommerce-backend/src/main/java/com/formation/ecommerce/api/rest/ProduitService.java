package com.formation.ecommerce.api.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.ecommerce.api.dto.ProduitDto;
import com.formation.ecommerce.api.mappers.ProduitMapper;
import com.formation.ecommerce.entities.Produit;
import com.formation.ecommerce.soa.ProduitSoa;

@RestController
@RequestMapping("/api/produits")
public class ProduitService {

	@Autowired
	private ProduitSoa soa;

	@GetMapping
	public List<ProduitDto> list() {
		return soa.getAllProduits() //
				.parallelStream() //
				.map(produitEntity -> ProduitMapper.map(produitEntity)) //
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ProduitDto getProduit(@PathVariable(name = "id", required = true) Long id) {
		Produit produitEntity = soa.getProduit(id);
		return ProduitMapper.map(produitEntity);
	}

	@PostMapping
	public ProduitDto createProduit(@RequestBody ProduitDto produitDto) {
		Produit produitEntity = ProduitMapper.map(produitDto);
		produitEntity = soa.create(produitEntity);
		return ProduitMapper.map(produitEntity);
	}

	@PutMapping
	public ProduitDto updateProduit(@RequestBody ProduitDto produitDto) {
		Produit produitEntity = ProduitMapper.map(produitDto);
		produitEntity = soa.update(produitEntity);
		return ProduitMapper.map(produitEntity);
	}

	@DeleteMapping("/{id}")
	public void deleteProduit(@PathVariable(name = "id", required = true) Long id) {
		Produit produit = new Produit();
		produit.setId(id);
		soa.delete(produit);
	}
}
