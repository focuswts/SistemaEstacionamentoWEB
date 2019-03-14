package br.com.estacionamento.mvc.model.persistent_object;

import java.util.ArrayList;

import br.com.estacionamento.mvc.model.crud.EstadoCRUD;

public class Teste3 {

	public static void main(String[] args) {

		POEstado estado = new POEstado();
		EstadoCRUD estadoCrud = new EstadoCRUD();

		estado.setNomeEstado("Teste Bacalhau");
		estado.setSiglaEstado("XT");
		
		//estadoCrud.save(estado);
		
		 ArrayList<POEstado> estados = estadoCrud.list();

		for(POEstado e: estados){
			System.out.println(e.getNomeEstado());
		}

	}

}
