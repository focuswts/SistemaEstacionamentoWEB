package br.com.estacionamento.mvc.model.crud;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.estacionamento.mvc.model.persistent_object.POEstado;

public class EstadoCRUD {

	public void save(POEstado estado) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ESTACIONAMENTO_PU");

		EntityManager em = emf.createEntityManager();
		try {

			em.getTransaction().begin();
			em.persist(estado);

			em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Erro Ao Salvar Estado");
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();

		}

	}

	public void update(POEstado estado) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ESTACIONAMENTO_PU");

		EntityManager em = emf.createEntityManager();
		try {

			em.getTransaction().begin();

			em.merge(estado);

			em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Erro Ao Atualizar Os Valores Do Estado");
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();

		}

	}

	public void delete(POEstado estado) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ESTACIONAMENTO_PU");

		EntityManager em = emf.createEntityManager();
		try {

			em.getTransaction().begin();
			estado = em.find(POEstado.class, estado.getIdEstado());

			em.remove(estado);

			em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Erro Ao Remover O Estado");
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();

		}

	}

	public ArrayList<POEstado> list() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ESTACIONAMENTO_PU");

		EntityManager em = emf.createEntityManager();
		ArrayList<POEstado> estados = new ArrayList<POEstado>();
		try {
			Query q = em.createQuery("SELECT o FROM POEstado o");
			em.getTransaction().begin();
			estados = (ArrayList<POEstado>) q.getResultList();

		} catch (Exception e) {
			System.out.println("Erro Ao Remover O Estado");
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
			return estados;

		}
	}

}
