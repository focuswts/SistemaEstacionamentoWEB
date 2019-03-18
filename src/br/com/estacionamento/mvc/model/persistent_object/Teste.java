package br.com.estacionamento.mvc.model.persistent_object;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

public class Teste {

	public static void main(String[] args) {

		POEstado e = new POEstado();
		e.setIdEstado(1);
		// e.setNomeEstado("Santa Catarina");
		// e.setSiglaEstado("SC");
		// e.setStatusEstado(EnumStatus.ATIVO);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ESTACIONAMENTO_PU"); // Define A Unidade De
																								// Persistencia, o
																						// EntityManagerFactory
																								// Cria A Conexao
		EntityManager em = emf.createEntityManager();

		// Operação Com O DB

		em.getTransaction().begin(); // Inicia A Transação Com O Banco De Dados
		// em.persist(e); //Faz A Inserção Do Objeto No DB
	//	e = em.find(POEstado.class, e.getIdEstado()); //Faz A busca no DB

		// em.remove(e); // Remove No DB

		
		e.setNomeEstado("Paraná");
		e.setStatusEstado(EnumStatus.ATIVO);
		em.merge(e);//Efetua O Insert Se não Possuir O Valor,Se Possuir Ele Atualiza
		
		em.getTransaction().commit(); // Efetua A Operação

		em.close();
		emf.close();

		//System.out.println(e.getNomeEstado());

		// find

	}

}
