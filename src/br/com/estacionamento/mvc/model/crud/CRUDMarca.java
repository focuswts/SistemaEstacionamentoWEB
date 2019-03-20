package br.com.estacionamento.mvc.model.crud;

import java.util.ArrayList;

import javax.persistence.Query;

import br.com.estacionamento.mvc.model.persistent_object.POMarca;

public class CRUDMarca extends AbstractCRUD{


	@Override
	public void insert(Object o) {
		POMarca po = (POMarca) o;
		super.open();
		super.entityManager.getTransaction().begin();
		super.entityManager.persist(po);
		super.entityManager.getTransaction().commit();
		super.close();

	}

	@Override
	public void update(Object o) {
		POMarca po = (POMarca) o;
		super.open();
		super.entityManager.getTransaction().begin();
		super.entityManager.merge(po);
		super.entityManager.getTransaction().commit();
		super.close();
	}

	@Override
	public void delete(Object o) {
		POMarca po = (POMarca) o;
		super.open();
		super.entityManager.getTransaction().begin();
		po = super.entityManager.find(POMarca.class, po.getIdMarca());
		super.entityManager.remove(po);
		super.entityManager.getTransaction().commit();
		super.close();

	}

	@Override
	public ArrayList<POMarca> list(String statement) {

		if (statement == null || statement.equals("")) {
			statement = "SELECT o FROM POMarca o";
		}

		super.open();
		Query query = super.entityManager.createQuery(statement);
		ArrayList<POMarca> set = (ArrayList<POMarca>) query.getResultList();
		super.close();
		return set;
	}
	
}
