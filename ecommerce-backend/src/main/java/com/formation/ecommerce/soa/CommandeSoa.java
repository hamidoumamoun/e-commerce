package com.formation.ecommerce.soa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.ecommerce.dao.CommandeDao;
import com.formation.ecommerce.entities.Commande;
import com.formation.ecommerce.entities.LigneCommande;
import com.formation.ecommerce.enums.CommandeStatus;

@Service
@Transactional
public class CommandeSoa {

	@Autowired
	private CommandeDao commandeDao;

	@Autowired
	private LigneCommandeSoa ligneCommandeSoa;

	public List<Commande> getAllCommandes() {
		return commandeDao.findAll();
	}

	public Commande create(Commande commande) {
		Set<LigneCommande> lignesCommande = commande.getLignes();
		if (lignesCommande == null || lignesCommande.isEmpty()) {
			throw new RuntimeException("La commande doit contenir au moins un produit");
		}
		commande.setStatus(CommandeStatus.TO_VALIDATE);
		lignesCommande = new HashSet<>(lignesCommande);
		commande.getLignes().clear();
		// Sauvegarde de la commande
		commandeDao.save(commande);
		for (LigneCommande ligneCommande : lignesCommande) {
			ligneCommande.setCommande(commande);
			ligneCommandeSoa.create(ligneCommande);
		}
		return commande;
	}

	public Commande getCommande(Long id) {
		try {
			return commandeDao.getOne(id);
		} catch (EntityNotFoundException e) {
			throw new RuntimeException("Impossible de trouver la commande d'identifiant " + id);
		}
	}

	public void traiter(Long id) {
		Commande commande = getCommande(id);
		if (commande.getStatus() != CommandeStatus.TO_VALIDATE) {
			throw new RuntimeException("La comande " + id + " doit être au statut à valider");
		}
		commande.setStatus(CommandeStatus.VALIDATING);
		commandeDao.save(commande);
	}

	public void valider(Long id) {
		Commande commande = getCommande(id);
		if (commande.getStatus() != CommandeStatus.VALIDATING) {
			throw new RuntimeException("La comande " + id + " doit être au statut en cours de validation");
		}
		commande.setStatus(CommandeStatus.VALIDATED);
		commandeDao.save(commande);
	}

	public void annuler(Long id) {
		Commande commande = getCommande(id);

		if (commande.getStatus() == CommandeStatus.VALIDATED) {
			throw new RuntimeException("La comande " + id + " a déjà été validée");
		}

		if (commande.getStatus() == CommandeStatus.CANCELED) {
			throw new RuntimeException("La comande " + id + " a déjà été annulée");
		}

		commande.setStatus(CommandeStatus.CANCELED);
		commandeDao.save(commande);

		for (LigneCommande ligneCommande : commande.getLignes()) {
			ligneCommandeSoa.annuler(ligneCommande);
		}
	}

}
