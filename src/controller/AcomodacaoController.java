package controller;

import model.Acomodacao;
import model.Disponibilidade;

import java.util.List;

import persistence.AcomodacaoPersistence;
import persistence.ColaboradorPersistence;
import persistence.DisponibilidadePersistence;

public class AcomodacaoController {

	private AcomodacaoPersistence acomodacaoPersistence;
	private ColaboradorPersistence colaboradorPersistence;
	private DisponibilidadePersistence disponibilidadePersistence;

	public AcomodacaoController() {
		this.acomodacaoPersistence = new AcomodacaoPersistence();
		this.colaboradorPersistence = new ColaboradorPersistence();
		this.disponibilidadePersistence = new DisponibilidadePersistence();
	}
	
	public int cadastrar(Acomodacao a) {
		int retorno;
		retorno = acomodacaoPersistence.inserir(a);
		
		for (Disponibilidade d : a.getDisponibilidades())
		{
			d.setIdAcomodacao(a.getId());
			retorno += disponibilidadePersistence.inserir(d);
		}
		
		return retorno;
	}

	public List consultar() {
		return this.acomodacaoPersistence.buscar();
	}

	public Acomodacao detalhar(Acomodacao a) {
		return this.acomodacaoPersistence.consultar(a);
	}

}
