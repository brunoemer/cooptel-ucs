package controller;

import java.util.ArrayList;
import java.util.List;
import model.Acomodacao;
import model.Colaborador;
import model.Disponibilidade;
import model.Reserva;
import model.Usuario;
import persistence.ReservaPersistence;

public class ReservaController {
    
    private ReservaPersistence reservaPersistence;
    
    public ReservaController(){
        this.reservaPersistence = new ReservaPersistence();
    }
    
    	public int cadastrar(Reserva r) {
		return reservaPersistence.inserir(r);
	}
        
        public List consultar(Usuario u) {
		return this.reservaPersistence.buscar(u);
	}
        
        public int cancelar(Reserva r){
            return this.reservaPersistence.remover(r);
        }
}
