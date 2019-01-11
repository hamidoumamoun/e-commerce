package com.formation.ecommerce.soa;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.formation.ecommerce.entities.Produit;
import com.formation.ecommerce.model.LignePanier;
import com.formation.ecommerce.model.Panier;

@Service
public class PanierSoa {

	@Autowired
	private ProduitSoa produitSoa;

	@Autowired
	private Panier panier;

	public String consulter(Model model) {
		model.addAttribute("panier", panier);
		return "panier";
	}

	public Panier ajouterProduit(Long idProduit, int quantite) {
		modifierPanier(idProduit, quantite);
		return panier;
	}

	public Panier modifierQuantiteProduit(Long idProduit, int quantite) {
		modifierPanier(idProduit, quantite);
		return panier;
	}

	public Panier retirerProduit(Long idProduit) {
		if (panier.getLignes() != null && !panier.getLignes().isEmpty()) {
			LignePanier ligne = new LignePanier();
			Produit produit = new Produit();
			produit.setId(idProduit);
			ligne.setProduit(produit);
			panier.getLignes().remove(ligne);
			updateMontantTotal();
		}
		return panier;
	}

	public Panier getPanier() {
		return panier;
	}

	private void modifierPanier(Long idProduit, int quantite) {
		// Initialisation de la liste des lignes panier si c'esst la première fois
		if (panier.getLignes() == null) {
			panier.setLignes(new HashSet<LignePanier>());
		}
		// Création de la lignes de panier
		LignePanier ligne = panier.getLignes() //
				.stream() //
				.filter(l -> l.getProduit().getId() == idProduit) //
				.findAny() //
				.orElse(createLignePanier(idProduit));
		ligne.setQuantite(quantite);
		panier.getLignes().add(ligne);
		updateMontantTotal();
	}

	private void updateMontantTotal() {
		double montant = panier.getLignes() //
				.stream() //
				.map(l -> l.getProduit().getPrixUnitaire() * l.getQuantite()) //
				.reduce(0d, (m, total) -> total + m);
		panier.setMontantTotal(montant);
	}

	private LignePanier createLignePanier(Long idProduit) {
		// Chargement du produit
		Produit produit = produitSoa.getProduit(idProduit);
		LignePanier ligne = new LignePanier();
		ligne.setProduit(produit);
		return ligne;
	}

	public void vider() {
		panier.getLignes().clear();
		panier.setMontantTotal(0);
	}

}
