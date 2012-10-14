package model;

import java.util.Date;

public class Disponibilidade {
	private int id;
	private Date dataInicio;
	private Date dataFim;
	private int id_acomodacao;
	
	public Disponibilidade(){
		
	}
	
	public Disponibilidade(Date dataInicial, Date dataFinal, int id_acomodacao){
		this.dataInicio = dataInicial;
		this.dataFim = dataFinal;
		this.id_acomodacao = id_acomodacao;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return this.dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public int getIdAcomodacao() {
		return this.id_acomodacao;
	}

	public void setIdAcomodacao(int id_acomodacao) {
		this.id_acomodacao = id_acomodacao;
	}
}
