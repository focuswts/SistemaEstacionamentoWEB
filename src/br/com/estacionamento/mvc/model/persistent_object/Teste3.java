package br.com.estacionamento.mvc.model.persistent_object;

import java.util.ArrayList;

import br.com.estacionamento.mvc.model.crud.CRUDCidade;
import br.com.estacionamento.mvc.model.crud.CRUDEstado;
import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

public class Teste3 {

	public static void main(String[] args) {
		
		try {

			CRUDEstado estadoCrud = new CRUDEstado();
//			CRUDCidade cidadeCrud = new CRUDCidade();

			// estadoCrud.save(estado);

			ArrayList<Object> estadoResult = estadoCrud.list("SELECT o FROM POEstado o WHERE o.idEstado = 1");

			
			
			POEstado estado = (POEstado) estadoResult.get(0);
			System.out.println(estado.getNomeEstado());
//			POCidade cidade = new POCidade();
//			cidade.setNomeCidade("Rolândia");
//			cidade.setIdEstado(estado);
//			cidade.setStatusCidade(EnumStatus.ATIVO);
//			System.out.println("Banco");

//			try {
//				cidadeCrud.insert(cidade);
//				System.out.println(cidade.getIdCidade());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
