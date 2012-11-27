package controller;

import java.util.ArrayList;

import model.Colaborador;
import model.Visitante;
import persistence.ColaboradorPersistence;
import persistence.VisitantePersistence;

public class VisitanteController {

	private VisitantePersistence visitantePersistence;

	public VisitanteController() {
		this.visitantePersistence = new VisitantePersistence();
	}

	public ArrayList<Visitante> consultar() {
		return this.visitantePersistence.buscar();
	}

	public int cadastrar(Visitante v) {
		return this.visitantePersistence.inserir(v);
	}
}
