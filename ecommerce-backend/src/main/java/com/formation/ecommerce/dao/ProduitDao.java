package com.formation.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.ecommerce.entities.Produit;

public interface ProduitDao extends JpaRepository<Produit, Long> {

}
