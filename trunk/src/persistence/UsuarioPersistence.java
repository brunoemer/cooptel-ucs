package persistence;

import model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

public class UsuarioPersistence {
	private Connection conn;

	public UsuarioPersistence() {
		this.conn = ConnectionSingleton.getInstance().getConnnection();
	}
	
	public int inserir(Usuario a) {
		PreparedStatement pstmt;
		try {
			pstmt = this.conn.prepareStatement("INSERT INTO usuario " +
				"(login, senha, tipo) VALUES " +
				"(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, a.getLogin());
			pstmt.setString(2, a.getSenha());
                        pstmt.setInt(3, a.getTipo());
			int retorno = pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
		    rs.next();
		    a.setId(rs.getInt(1));
		   
		   return retorno;

		} catch (SQLException e) {
			System.out.println("Problemas ao inserir usuario"+e.getMessage());
			return 0;
		}
	}

	public Usuario consultar(Usuario a) {
		Usuario usuario = null;
		PreparedStatement pstmt;
		try {
			pstmt = this.conn.prepareStatement("SELECT * FROM usuario WHERE id = ?");
			pstmt.setInt(1, a.getId());
			ResultSet res = pstmt.executeQuery();
			boolean achou = res.next();
			if(achou == false){
				System.out.println("Nenhuma usuario encontrado");
			}
			usuario = new Usuario(res.getInt("id"), res.getString("login"), res.getString("senha"), res.getInt("tipo"));
		} catch (SQLException e) {
			
		}
		return usuario;
	}

        public Usuario logar(Usuario a) {
		Usuario usuario = null;
		PreparedStatement pstmt;
		try {
			pstmt = this.conn.prepareStatement("SELECT * FROM usuario WHERE login = ? AND senha = ?");
			pstmt.setString(1, a.getLogin());
                        pstmt.setString(2, a.getSenha());
			ResultSet res = pstmt.executeQuery();
			boolean achou = res.next();
			if(achou == false){
				System.out.println("Nenhum usuario encontrado");
			}
			usuario = new Usuario(res.getInt("id"), res.getString("login"), res.getString("senha"), res.getInt("tipo"));
		} catch (SQLException e) {
			
		}
		return usuario;
	}
}
