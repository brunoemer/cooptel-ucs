package controller;

import java.util.AbstractList;
import java.util.ArrayList;
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

        for (Disponibilidade d : a.getDisponibilidades()) {
            d.setIdAcomodacao(a.getId());
            retorno += disponibilidadePersistence.inserir(d);
        }

        return retorno;
    }

    public List consultar() {
        List<Acomodacao> acomodacoes = this.acomodacaoPersistence.buscar();

        for (Acomodacao a : acomodacoes) {
            a.setDisponibilidades(this.disponibilidadePersistence.buscar(a));
        }

        return acomodacoes;
    }

    public List consultar(String endereco, int tipo, int cafeDaManha, float valorDiariaInicial, float valorDiariaFinal, Disponibilidade disponibilidade) {
        List<Acomodacao> acomodacoes = this.acomodacaoPersistence.buscar(endereco, tipo, cafeDaManha, valorDiariaInicial, valorDiariaFinal);
        List<Acomodacao> removerAcomodacoes = new ArrayList<Acomodacao>();

        for (Acomodacao a : acomodacoes) {
            List<Disponibilidade> disponibilidades = this.disponibilidadePersistence.buscar(a);
            a.setDisponibilidades(disponibilidades);

            if (disponibilidade != null) {
                boolean encontrou = false;
                for (Disponibilidade d : disponibilidades) {
                    if (d.getDataInicio().compareTo(disponibilidade.getDataInicio()) <= 0 && d.getDataFim().compareTo(disponibilidade.getDataFim()) >= 0) {
                        encontrou = true;
                    }
                }

                if (!encontrou) {
                    removerAcomodacoes.add(a);
                }
            }

        }

        for (Acomodacao a : removerAcomodacoes) {
            acomodacoes.remove(a);
        }

        return acomodacoes;
    }

    public void alterar(Acomodacao a){
        this.acomodacaoPersistence.alterar(a);
        this.disponibilidadePersistence.remover(a);
        
        for (Disponibilidade d : a.getDisponibilidades())
            this.disponibilidadePersistence.inserir(d);
    }
    
    public Acomodacao detalhar(Acomodacao a) {
        return this.acomodacaoPersistence.consultar(a);
    }
}
