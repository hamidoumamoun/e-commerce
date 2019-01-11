package com.formation.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.ecommerce.entities.LigneCommande;

public interface LigneCommandeDao extends JpaRepository<LigneCommande, Long> {

}
