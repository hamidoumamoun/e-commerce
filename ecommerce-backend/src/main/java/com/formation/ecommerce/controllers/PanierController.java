package com.formation.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.formation.ecommerce.soa.PanierSoa;

@Controller
public class PanierController {

	@Autowired
	private PanierSoa panierSoa;

	@GetMapping("/panier")
	public String consulter(Model model) {
		model.addAttribute("panier", panierSoa.getPanier());
		return "panier";
	}

	@GetMapping("/panier/add-produit/{idProduit}/{quantite}")
	public String ajouterProduit(@PathVariable(name = "idProduit", required = true) Long idProduit,
			@PathVariable(name = "quantite", required = true) int quantite, Model model) {
		panierSoa.ajouterProduit(idProduit, quantite);
		return "redirect:/produits";
	}

	@GetMapping("/panier/update-quantite-produit/{idProduit}/{quantite}")
	public String modifierQuantiteProduit(@PathVariable(name = "idProduit", required = true) Long idProduit,
			@PathVariable(name = "quantite", required = true) int quantite, Model model) {
		panierSoa.modifierQuantiteProduit(idProduit, quantite);
		return "redirect:/panier";
	}

	@GetMapping("/panier/remove-produit/{idProduit}")
	public String retirerProduit(@PathVariable(name = "idProduit", required = true) Long idProduit, Model model) {
		panierSoa.retirerProduit(idProduit);
		return "redirect:/panier";
	}
	
	@GetMapping("/panier/vider")
	public String vider(Model model) {
		panierSoa.vider();
		return "redirect:/panier";
	}

}
