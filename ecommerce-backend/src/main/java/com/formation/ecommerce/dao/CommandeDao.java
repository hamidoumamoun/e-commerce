package com.formation.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.ecommerce.entities.Commande;

public interface CommandeDao extends JpaRepository<Commande, Long> {

}
