package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Visitante;

import com.mysql.jdbc.Connection;

public class VisitantePersistence {
	private Connection conn;

	public VisitantePersistence() {
		this.conn = ConnectionSingleton.getInstance().getConnnection();
	}
	
	public ArrayList<Visitante> buscar() {
		ArrayList<Visitante> lista = new ArrayList<Visitante>();
		PreparedStatement pstmt;
		try {
			pstmt = this.conn.prepareStatement("SELECT * FROM visitante");
			ResultSet res = pstmt.executeQuery();
			boolean achou = false;
			while(res.next()){
				achou = true;
				lista.add(new Visitante(res.getString("nome"), res.getString("pais_origem"), res.getInt("id_usuario"), res.getInt("id")));
			}
			if(!achou){
				System.out.println("Nenhum visitante encontrado");
			}
		} catch (SQLException e) {
			
		}
		return lista;
	}

	public Visitante consultar(Visitante v) {
		Visitante visitante = null;
		PreparedStatement pstmt;
		try {
			pstmt = this.conn.prepareStatement("SELECT * FROM visitante WHERE id = ?");
			pstmt.setInt(1, v.getId());
			ResultSet res = pstmt.executeQuery();
			boolean achou = res.next();
			if(achou == false){
				System.out.println("Nenhum visitante encontrado");
			}
			visitante = new Visitante(res.getString("nome"), res.getString("pais_origem"), res.getInt("id_usuario"), res.getInt("id"));
		} catch (SQLException e) {
			
		}
		return visitante;
	}
    
	public int alterar(Visitante v) {
		PreparedStatement pstmt;
		try {
			pstmt = this.conn.prepareStatement("UPDATE visitante SET nome = ?, pais_origem = ?, id_usuario = ? WHERE id = ?");
			pstmt.setString(1, v.getNome());
			pstmt.setString(2, v.getPaisOrigem());
			pstmt.setInt(3, v.getIdUsuario());
			pstmt.setInt(4, v.getId());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Problemas ao alterar visitante");
			return 0;
		}
	}

	public int inserir(Visitante v) {
		PreparedStatement pstmt;
		try {
			pstmt = this.conn.prepareStatement("INSERT INTO visitante (nome, pais_origem, id_usuario) VALUES (?, ?, ?)");
			pstmt.setString(1, v.getNome());
			pstmt.setString(2, v.getPaisOrigem());
			pstmt.setInt(3, v.getIdUsuario());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Problemas ao inserir visitante: " + e.getMessage());
			return 0;
		}
	}

}
