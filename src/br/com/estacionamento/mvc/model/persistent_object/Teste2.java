package br.com.estacionamento.mvc.model.persistent_object;


import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Teste2 {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ESTACIONAMENTO_PU"); 
		EntityManager em = emf.createEntityManager();

		Query q = em.createQuery("SELECT o FROM POEstado o");
		q.setFirstResult(0);
		q.setMaxResults(10);
		
		
		ArrayList<POEstado> estados = (ArrayList<POEstado>) q.getResultList();
		
		em.close();
		emf.close();
		
		for (POEstado e : estados) {
			System.out.println(e.getNomeEstado());
		}
		
	}

}
