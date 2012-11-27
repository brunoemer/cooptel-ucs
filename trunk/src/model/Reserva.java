package model;

import java.util.Date;

public class Reserva {
    private int id;
    private int id_acomodacao;
    private int id_usuario;
    private Date dataInicio;
    private Date dataFim;
    private float valorTotal;
    
    public Reserva(){
    }
    
    public Reserva(int id, int id_acomodacao, int id_usuario, Date dataInicio, Date dataFim, float valorTotal){
        this.id = id;
        this.id_acomodacao = id_acomodacao;
        this.id_usuario = id_usuario;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorTotal = valorTotal;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAcomodacao() {
        return id_acomodacao;
    }

    public void setIdAcomodacao(int id_acomodacao) {
        this.id_acomodacao = id_acomodacao;
    }

    public int getIdUsuario() {
        return id_usuario;
    }

    public void setIdUsuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
}
