package persistence;

import model.Acomodacao;
import model.Colaborador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

public class AcomodacaoPersistence {
	private Connection conn;

	public AcomodacaoPersistence() {
		this.conn = ConnectionSingleton.getInstance().getConnnection();
	}
	
	public void inserir(Acomodacao a) {
		PreparedStatement pstmt;
		try {
			pstmt = this.conn.prepareStatement("INSERT INTO acomodacao " +
				"(id_colaborador, tipo, cafe, endereco, latitude, logitude, foto1, foto2, valordiario, descricao) VALUES " +
				"(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, a.getIdColaborador());
			pstmt.setInt(2, a.getTipo());
			pstmt.setInt(3, a.isCafe()?1:0);
			pstmt.setString(4, a.getEndereco());
			pstmt.setDouble(5, a.getLatitude());
			pstmt.setDouble(6, a.getLongitude());
			pstmt.setString(7, a.getFoto1());
			pstmt.setString(8, a.getFoto2());
			pstmt.setFloat(9, a.getValorDiario());
			pstmt.setString(10, a.getDescricao());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Problemas ao inserir colaborador");
		}
	}

	public List buscar() {
		List<Acomodacao> lista = new ArrayList<Acomodacao>();
		PreparedStatement pstmt;
		try {
			pstmt = this.conn.prepareStatement("SELECT * FROM acomodacao");
			ResultSet res = pstmt.executeQuery();
			boolean achou = false;
			while(res.next()){
				achou = true;
				lista.add(new Acomodacao(res.getInt("id_colaborador"), res.getInt("tipo"), (res.getInt("cafe") == 1), res.getString("endereco"), res.getDouble("latitude"), res.getDouble("longitude"), res.getString("foto1"), res.getString("foto2"), res.getFloat("valordiario"), res.getString("descricao"), res.getInt("id")));
			}
			if(!achou){
				System.out.println("Nenhuma acomodacao encontrado");
			}
		} catch (SQLException e) {
			
		}
		return lista;
	}

	public Acomodacao consultar(Acomodacao a) {
		Acomodacao acomodacao = null;
		PreparedStatement pstmt;
		try {
			pstmt = this.conn.prepareStatement("SELECT * FROM acomodacao WHERE id = ?");
			pstmt.setInt(1, a.getId());
			ResultSet res = pstmt.executeQuery();
			boolean achou = res.next();
			if(achou == false){
				System.out.println("Nenhuma acomodacao encontrado");
			}
			acomodacao = new Acomodacao(res.getInt("id_colaborador"), res.getInt("tipo"), (res.getInt("cafe") == 1), res.getString("endereco"), res.getDouble("latitude"), res.getDouble("longitude"), res.getString("foto1"), res.getString("foto2"), res.getFloat("valordiario"), res.getString("descricao"), res.getInt("id"));
		} catch (SQLException e) {
			
		}
		return acomodacao;
	}

}
