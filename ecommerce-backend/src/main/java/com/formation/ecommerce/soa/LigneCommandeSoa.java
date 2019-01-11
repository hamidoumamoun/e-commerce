package com.formation.ecommerce.soa;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.ecommerce.dao.CommandeDao;
import com.formation.ecommerce.dao.LigneCommandeDao;
import com.formation.ecommerce.entities.Commande;
import com.formation.ecommerce.entities.LigneCommande;
import com.formation.ecommerce.entities.Produit;

@Service
@Transactional
public class LigneCommandeSoa {

	@Autowired
	private LigneCommandeDao ligneCommandeDao;

	@Autowired
	private CommandeDao commandeDao;

	@Autowired
	private ProduitSoa produitSoa;

	public void create(LigneCommande ligneCommande) {
		Commande commande = ligneCommande.getCommande();
		Produit produit = ligneCommande.getProduit();
		if (commande == null || commande.getId() == null) {
			throw new RuntimeException("La commande de la ligne est obligatoire");
		}
		if (produit == null || produit.getId() == null) {
			throw new RuntimeException("Le produit de la ligne commande est obligatoire");
		}
		// Recherge le produit
		produit = produitSoa.getProduit(produit.getId());
		// Recherge la commande
		commande = commandeDao.getOne(commande.getId());

		// Vérification de la quantité disponible
		if (produit.getQuantite() < ligneCommande.getQuantite()) {
			throw new RuntimeException(
					String.format("La quantité du produit %s n'est pas suffisante", produit.getNom()));
		}

		// Sauvegarde de la commande
		ligneCommandeDao.save(ligneCommande);

		// MAJ du stock du produit
		produit.setQuantite(produit.getQuantite() - ligneCommande.getQuantite());
		produitSoa.update(produit);

	}

	public LigneCommande getLigneCommande(Long id) {
		try {
			return ligneCommandeDao.getOne(id);
		} catch (EntityNotFoundException e) {
			throw new RuntimeException("Impossible de trouver la ligne de commande d'identifiant " + id);
		}
	}

	public void annuler(LigneCommande ligneCommande) {
		LigneCommande oldLigne = getLigneCommande(ligneCommande.getId());
		Produit produit = oldLigne.getProduit();
		produit.setQuantite(produit.getQuantite() + ligneCommande.getQuantite());
		produitSoa.update(produit);
	}

}
