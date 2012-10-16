package controller;

import java.util.ArrayList;
import java.util.List;

import persistence.ColaboradorPersistence;
import model.Colaborador;

public class ColaboradorController {

	private ColaboradorPersistence colaboradorPersistence;

	public ColaboradorController() {
		this.colaboradorPersistence = new ColaboradorPersistence();
	}

	public ArrayList<Colaborador> consultar() {
		return this.colaboradorPersistence.buscar();
	}
	
	public ArrayList<Colaborador> consultarInativos(){
		return this.colaboradorPersistence.buscarInativos();
	}

	public void liberar(Colaborador c) {
		this.colaboradorPersistence.alterar(c);
	}

	public int cadastrar(Colaborador c) {
		return colaboradorPersistence.inserir(c);
	}
}
