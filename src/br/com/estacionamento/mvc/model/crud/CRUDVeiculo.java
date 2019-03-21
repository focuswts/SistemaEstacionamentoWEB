package br.com.estacionamento.mvc.model.crud;

import java.util.ArrayList;

import javax.persistence.Query;

import br.com.estacionamento.mvc.model.persistent_object.POVeiculo;

public class CRUDVeiculo extends AbstractCRUD{


	@Override
	public void insert(Object o) {
		POVeiculo po = (POVeiculo) o;
		super.open();
		super.entityManager.getTransaction().begin();
		super.entityManager.persist(po);
		super.entityManager.getTransaction().commit();
		super.close();

	}

	@Override
	public void update(Object o) {
		POVeiculo po = (POVeiculo) o;
		super.open();
		super.entityManager.getTransaction().begin();
		super.entityManager.merge(po);
		super.entityManager.getTransaction().commit();
		super.close();
	}

	@Override
	public void delete(Object o) {
		POVeiculo po = (POVeiculo) o;
		super.open();
		super.entityManager.getTransaction().begin();
		po = super.entityManager.find(POVeiculo.class, po.getIdVeiculo());
		super.entityManager.remove(po);
		super.entityManager.getTransaction().commit();
		super.close();

	}

	@Override
	public ArrayList<POVeiculo> list(String statement) {

		if (statement == null || statement.equals("")) {
			statement = "SELECT o FROM POVeiculo o";
		}

		super.open();
		Query query = super.entityManager.createQuery(statement);
		ArrayList<POVeiculo> set = (ArrayList<POVeiculo>) query.getResultList();
		super.close();
		return set;
	}
	
}
