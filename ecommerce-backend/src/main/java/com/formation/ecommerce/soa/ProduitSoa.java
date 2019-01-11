package com.formation.ecommerce.soa;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.ecommerce.dao.ProduitDao;
import com.formation.ecommerce.entities.Produit;

@Service
@Transactional
public class ProduitSoa {

	@Autowired
	private ProduitDao dao;

	public Produit create(Produit produit) {
		if (produit == null) {
			throw new RuntimeException("Le produit est obligatoire");
		}

		if (produit.getCode() == null || produit.getCode().isEmpty()) {
			throw new RuntimeException("Le code du produit est obligatoire");
		}
		return dao.save(produit);
	}

	public Produit update(Produit produit) {
		if (produit == null || produit.getId() == null) {
			throw new RuntimeException("L'id du produit est obligatoire");
		}

		if (produit.getCode() == null || produit.getCode().isEmpty()) {
			throw new RuntimeException("Le code du produit est obligatoire");
		}

		return dao.save(produit);
	}

	public void delete(Produit produit) {
		if (produit == null || produit.getId() == null) {
			throw new RuntimeException("L'id du produit est obligatoire");
		}

		dao.delete(produit);
	}

	public List<Produit> getAllProduits() {
		return dao.findAll();
	}

	public Produit getProduit(Long id) {
		if (id == null) {
			throw new RuntimeException("L'id du produit est obligatoire");
		}

		Optional<Produit> produitOptional = dao.findById(id);

		if (!produitOptional.isPresent()) {
			throw new RuntimeException("Produit d'id " + id + " est introuvable");
		}

		return produitOptional.get();
	}
}
