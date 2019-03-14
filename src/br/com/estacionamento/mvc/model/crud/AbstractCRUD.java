package br.com.estacionamento.mvc.model.crud;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractCRUD {

	protected EntityManagerFactory entityManagerFactory = null;
	protected EntityManager entityManager = null;

	public void open() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("ESTACIONAMENTO_PU");
		this.entityManager = this.entityManagerFactory.createEntityManager();

	}

	public void close() {
		this.entityManager.close();
		this.entityManagerFactory.close();
	}

	public abstract void insert(Object o);

	public abstract void update(Object o);

	public abstract void delete(Object o);

	public abstract ArrayList<Object> list(String statement);
	
	
}
