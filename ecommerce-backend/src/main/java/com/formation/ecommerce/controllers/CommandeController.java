package com.formation.ecommerce.controllers;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.formation.ecommerce.entities.Commande;
import com.formation.ecommerce.entities.LigneCommande;
import com.formation.ecommerce.enums.CommandeStatus;
import com.formation.ecommerce.model.LignePanier;
import com.formation.ecommerce.model.Panier;
import com.formation.ecommerce.soa.CommandeSoa;
import com.formation.ecommerce.soa.PanierSoa;

@Controller
public class CommandeController {

	@Autowired
	private CommandeSoa commandeSoa;

	@Autowired
	private PanierSoa panierSoa;
	
	@GetMapping("/commande/{id}")
	public String consultation(@PathVariable(name = "id", required = true) Long id, Model model) {
		model.addAttribute("commande", commandeSoa.getCommande(id));
		return "commande";
	}
	
	@GetMapping("/commande/traiter/{id}")
	public String traiter(@PathVariable(name = "id", required = true) Long id, RedirectAttributes redirectAttributes) {
		try {
			commandeSoa.traiter(id);
			redirectAttributes.addFlashAttribute("successMessage", "Votre commande est en cours de validation");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		}
		return "redirect:/commande/" + id;
	}
	
	@GetMapping("/commande/valider/{id}")
	public String valider(@PathVariable(name = "id", required = true) Long id, RedirectAttributes redirectAttributes) {
		try {
			commandeSoa.valider(id);
			redirectAttributes.addFlashAttribute("successMessage", "Votre commande a été validée");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		}
		return "redirect:/commande/" + id;
	}
	
	@GetMapping("/commande/annuler/{id}")
	public String annuler(@PathVariable(name = "id", required = true) Long id, RedirectAttributes redirectAttributes) {
		try {
			commandeSoa.annuler(id);
			redirectAttributes.addFlashAttribute("successMessage", "Votre commande a été annulée");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		}
		return "redirect:/commande/" + id;
	}

	@GetMapping("/commande/passer")
	public String passer(RedirectAttributes redirectAttributes) {
		Panier panier = panierSoa.getPanier();
		Commande commande = new Commande();
		commande.setMontant(panier.getMontantTotal());
		commande.setStatus(CommandeStatus.TO_VALIDATE);
		commande.setLignes(new HashSet<>());
		for (LignePanier lignePanier : panier.getLignes()) {
			LigneCommande lc = new LigneCommande();
			lc.setProduit(lignePanier.getProduit());
			lc.setQuantite(lignePanier.getQuantite());
			commande.getLignes().add(lc);
		}
		// Création de la commande
		try {
			commandeSoa.create(commande);
			// Vidange du panier
			panierSoa.vider();
			redirectAttributes.addFlashAttribute("successMessage", "Votre commande a bien été prise en compte");
			return "redirect:/commande/" + commande.getId();
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		}
		return "redirect:/panier";
	}

}
