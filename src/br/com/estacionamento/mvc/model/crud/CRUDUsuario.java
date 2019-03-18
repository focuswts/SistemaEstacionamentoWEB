package br.com.estacionamento.mvc.model.crud;

import java.util.ArrayList;

import javax.persistence.Query;

import br.com.estacionamento.mvc.model.persistent_object.POUsuario;

public class CRUDUsuario extends AbstractCRUD{


	@Override
	public void insert(Object o) {
		POUsuario po = (POUsuario) o;
		super.open();
		super.entityManager.getTransaction().begin();
		super.entityManager.persist(po);
		super.entityManager.getTransaction().commit();
		super.close();

	}

	@Override
	public void update(Object o) {
		POUsuario po = (POUsuario) o;
		super.open();
		super.entityManager.getTransaction().begin();
		super.entityManager.merge(po);
		super.entityManager.getTransaction().commit();
		super.close();
	}

	@Override
	public void delete(Object o) {
		POUsuario po = (POUsuario) o;
		super.open();
		super.entityManager.getTransaction().begin();
		po = super.entityManager.find(POUsuario.class, po.getIdUsuario());
		super.entityManager.remove(po);
		super.entityManager.getTransaction().commit();
		super.close();

	}

	@Override
	public ArrayList<Object> list(String statement) {

		if (statement == null || statement.equals("")) {
			statement = "SELECT o FROM POUsuario o";
		}

		super.open();
		Query query = super.entityManager.createQuery(statement);
		ArrayList<Object> set = (ArrayList<Object>) query.getResultList();
		super.close();
		return set;
	}
	
}
