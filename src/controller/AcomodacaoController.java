package controller;

import model.Acomodacao;
import java.util.List;

import persistence.AcomodacaoPersistence;
import persistence.ColaboradorPersistence;

public class AcomodacaoController {

	private AcomodacaoPersistence acomodacaoPersistence;
	private ColaboradorPersistence colaboradorPersistence;

	public AcomodacaoController() {
		this.acomodacaoPersistence = new AcomodacaoPersistence();
		this.colaboradorPersistence = new ColaboradorPersistence();
	}
	
	public int cadastrar(Acomodacao a) {
		return acomodacaoPersistence.inserir(a);
	}

	public List consultar() {
		return null;
	}

	public void detalhar(Acomodacao a) {

	}

}
