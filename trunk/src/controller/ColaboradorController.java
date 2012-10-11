package controller;

import java.util.List;

import persistence.ColaboradorPersistence;
import model.Colaborador;

public class ColaboradorController {

	private ColaboradorPersistence colaboradorPersistence;

	public ColaboradorController() {
		this.colaboradorPersistence = new ColaboradorPersistence();
	}

	public List consultar() {
		return null;
	}

	public void liberar(Colaborador c) {

	}

	public int cadastrar(Colaborador c) {
		return colaboradorPersistence.inserir(c);
	}

}
