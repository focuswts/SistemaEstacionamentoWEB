package br.com.estacionamento.mvc.model.crud;

import java.util.ArrayList;

import javax.persistence.Query;

import br.com.estacionamento.mvc.model.persistent_object.POTipoVeiculo;

public class CRUDTipoVeiculo extends AbstractCRUD{


	@Override
	public void insert(Object o) {
		POTipoVeiculo po = (POTipoVeiculo) o;
		super.open();
		super.entityManager.getTransaction().begin();
		super.entityManager.persist(po);
		super.entityManager.getTransaction().commit();
		super.close();

	}

	@Override
	public void update(Object o) {
		POTipoVeiculo po = (POTipoVeiculo) o;
		super.open();
		super.entityManager.getTransaction().begin();
		super.entityManager.merge(po);
		super.entityManager.getTransaction().commit();
		super.close();
	}

	@Override
	public void delete(Object o) {
		POTipoVeiculo po = (POTipoVeiculo) o;
		super.open();
		super.entityManager.getTransaction().begin();
		po = super.entityManager.find(POTipoVeiculo.class, po.getIdTipoV());
		super.entityManager.remove(po);
		super.entityManager.getTransaction().commit();
		super.close();

	}

	@Override
	public ArrayList<POTipoVeiculo> list(String statement) {

		if (statement == null || statement.equals("")) {
			statement = "SELECT o FROM POTipoVeiculo o";
		}

		super.open();
		Query query = super.entityManager.createQuery(statement);
		ArrayList<POTipoVeiculo> set = (ArrayList<POTipoVeiculo>) query.getResultList();
		super.close();
		return set;
	}
	
}
