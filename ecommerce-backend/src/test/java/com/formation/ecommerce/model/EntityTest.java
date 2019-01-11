//package com.formation.ecommerce.model;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Persistence;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import com.formation.ecommerce.entities.Produit;
//
//public class EntityTest {
//
//	private EntityManager em;
//	
//	@Before
//	public void setUp() {
//		em = Persistence.createEntityManagerFactory("testPU").createEntityManager();
//	}
//	
//	@After
//	public void ternDown() {
//		if (em != null) {
//			em.close();
//		}
//	}
//	
//	@Test
//	public void validateEntities() {
//		// Initialisation d'un produit
//		Produit produit = new Produit();
//		produit.setCode("P1");
//		produit.setNom("Produit test");
//		produit.setPrixUnitaire(2);
//		produit.setQuantite(10);
//		// demarrage d'une transaction
//		em.getTransaction().begin();
//		// Sauvegarde du produit en base de donn�es
//		em.persist(produit); // le produit a un id produit.getId()
//		// Find de la transaction et commit
//		em.getTransaction().commit();
//		// Chagement du produit cr��
//		Produit produitCree = em.find(Produit.class, produit.getId());
//		// Comparaison des deux produits
//		Assert.assertEquals("Le produit cr�� doit �tre identique celui initialis�", produit, produitCree);
//	}
//}
