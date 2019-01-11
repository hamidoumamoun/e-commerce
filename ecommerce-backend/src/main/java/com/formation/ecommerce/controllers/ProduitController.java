package com.formation.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.formation.ecommerce.entities.Produit;
import com.formation.ecommerce.soa.ProduitSoa;

@Controller
public class ProduitController {

	@Autowired
	private ProduitSoa soa;

	@GetMapping("/produits")
	public String list(Model model) {
		model.addAttribute("produits", soa.getAllProduits());
		return "produits";
	}

	@GetMapping("/new-produit")
	public String newProduit(Model model) {
		model.addAttribute("produit", new Produit());
		model.addAttribute("action", "/create-produit");
		return "edit-produit";
	}

	@PostMapping("/create-produit")
	public String createProduit(@RequestParam String code, @RequestParam String nom, @RequestParam double prixUnitaire,
			@RequestParam long quantite, Model model) {
		Produit produit = new Produit();
		produit.setCode(code);
		produit.setNom(nom);
		produit.setPrixUnitaire(prixUnitaire);
		produit.setQuantite(quantite);
		try {
			soa.create(produit);
		} catch (Exception e) {
			model.addAttribute("produit", produit);
			model.addAttribute("action", "/create-produit");
			model.addAttribute("error", e.getMessage());
			return "edit-produit";
		}
		return "redirect:/produits";
	}

	@GetMapping("/edit-produit/{id}")
	public String editProduit(@PathVariable(name = "id", required = true) Long id, Model model) {
		model.addAttribute("produit", soa.getProduit(id));
		model.addAttribute("action", "/update-produit/" + id);
		return "edit-produit";
	}

	@PostMapping("/update-produit/{id}")
	public String updateProduit(@PathVariable(name = "id", required = true) Long id, @RequestParam String code,
			@RequestParam String nom, @RequestParam double prixUnitaire, @RequestParam long quantite, Model model) {
		Produit produit = new Produit();
		produit.setId(id);
		produit.setCode(code);
		produit.setNom(nom);
		produit.setPrixUnitaire(prixUnitaire);
		produit.setQuantite(quantite);
		try {
			soa.update(produit);
		} catch (Exception e) {
			model.addAttribute("produit", produit);
			model.addAttribute("action", "/update-produit/"+id);
			model.addAttribute("error", e.getMessage());
			return "edit-produit";
		}
		return "redirect:/produits";
	}

	@GetMapping("/delete-produit/{id}")
	public String deleteProduit(@PathVariable(name = "id", required = true) Long id, Model model) {
		Produit produit = new Produit();
		produit.setId(id);
		soa.delete(produit);
		return "redirect:/produits";
	}
}
