package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import model.Colaborador;

public class ColaboradorPersistence {
	private Connection conn;

	public ColaboradorPersistence() {
		this.conn = ConnectionSingleton.getInstance().getConnnection();
	}
	
	public List buscar() {
		List<Colaborador> lista = new ArrayList<Colaborador>();
		PreparedStatement pstmt;
		try {
			pstmt = this.conn.prepareStatement("SELECT * FROM colaborador");
			ResultSet res = pstmt.executeQuery();
			boolean achou = false;
			while(res.next()){
				achou = true;
				lista.add(new Colaborador(res.getString("cpf"), res.getString("nome"), res.getString("endereco"), res.getString("email"), (res.getInt("ativo") == 1), res.getInt("id")));
			}
			if(!achou){
				System.out.println("Nenhum colaborador encontrado");
			}
		} catch (SQLException e) {
			
		}
		return lista;
	}

	public int alterar(Colaborador c) {
		PreparedStatement pstmt;
		try {
			pstmt = this.conn.prepareStatement("UPDATE colaborador SET cpf = ?, nome = ?, endereco = ?, email = ?, ativo = ? WHERE id = ?");
			pstmt.setString(1, c.getCpf());
			pstmt.setString(2, c.getNome());
			pstmt.setString(3, c.getEndereco());
			pstmt.setString(4, c.getEmail());
			pstmt.setInt(5, c.getAtivo()?1:0);
			pstmt.setInt(6, c.getId());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Problemas ao alterar colaborador");
			return 0;
		}
	}

	public int inserir(Colaborador c) {
		PreparedStatement pstmt;
		try {
			pstmt = this.conn.prepareStatement("INSERT INTO colaborador (cpf, nome, endereco, email, ativo) VALUES (?, ?, ?, ?, ?)");
			pstmt.setString(1, c.getCpf());
			pstmt.setString(2, c.getNome());
			pstmt.setString(3, c.getEndereco());
			pstmt.setString(4, c.getEmail());
			pstmt.setInt(5, c.getAtivo()?1:0);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Problemas ao inserir colaborador");
			return 0;
		}
	}

}
