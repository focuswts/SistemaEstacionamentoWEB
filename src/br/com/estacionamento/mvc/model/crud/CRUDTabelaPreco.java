package br.com.estacionamento.mvc.model.crud;

import java.util.ArrayList;

import javax.persistence.Query;

import br.com.estacionamento.mvc.model.persistent_object.POTabelaPreco;

public class CRUDTabelaPreco extends AbstractCRUD{


	@Override
	public void insert(Object o) {
		POTabelaPreco po = (POTabelaPreco) o;
		super.open();
		super.entityManager.getTransaction().begin();
		super.entityManager.persist(po);
		super.entityManager.getTransaction().commit();
		super.close();

	}

	@Override
	public void update(Object o) {
		POTabelaPreco po = (POTabelaPreco) o;
		super.open();
		super.entityManager.getTransaction().begin();
		super.entityManager.merge(po);
		super.entityManager.getTransaction().commit();
		super.close();
	}

	@Override
	public void delete(Object o) {
		POTabelaPreco po = (POTabelaPreco) o;
		super.open();
		super.entityManager.getTransaction().begin();
		po = super.entityManager.find(POTabelaPreco.class, po.getIdTabelaP());
		super.entityManager.remove(po);
		super.entityManager.getTransaction().commit();
		super.close();

	}

	@Override
	public ArrayList<Object> list(String statement) {

		if (statement == null || statement.equals("")) {
			statement = "SELECT o FROM POTabelaPreco o";
		}

		super.open();
		Query query = super.entityManager.createQuery(statement);
		ArrayList<Object> set = (ArrayList<Object>) query.getResultList();
		super.close();
		return set;
	}
	
}