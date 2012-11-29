package persistence;

import model.Acomodacao;
import model.Colaborador;
import model.Disponibilidade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import java.text.SimpleDateFormat;

public class AcomodacaoPersistence {
	private Connection conn;

	public AcomodacaoPersistence() {
		this.conn = ConnectionSingleton.getInstance().getConnnection();
	}
	
	public int inserir(Acomodacao a) {
		PreparedStatement pstmt;
		try {
			pstmt = this.conn.prepareStatement("INSERT INTO acomodacao " +
				"(id_colaborador, tipo, cafe, endereco, latitude, longitude, foto1, foto2, foto_externa, valordiario, descricao) VALUES " +
				"(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, a.getIdColaborador());
			pstmt.setInt(2, a.getTipo());
			pstmt.setInt(3, a.isCafe()?1:0);
			pstmt.setString(4, a.getEndereco());
			pstmt.setDouble(5, a.getLatitude());
			pstmt.setDouble(6, a.getLongitude());
			pstmt.setString(7, a.getFoto1());
			pstmt.setString(8, a.getFoto2());
			pstmt.setString(9, a.getFotoExterna());
			pstmt.setFloat(10, a.getValorDiario());
			pstmt.setString(11, a.getDescricao());
			int retorno = pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
		    rs.next();
		    a.setId(rs.getInt(1));
		   
		   return retorno;

		} catch (SQLException e) {
			System.out.println("Problemas ao inserir acomodacao"+e.getMessage());
			return 0;
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
				lista.add(new Acomodacao(res.getInt("id_colaborador"), res.getInt("tipo"), (res.getInt("cafe") == 1), res.getString("endereco"), res.getDouble("latitude"), res.getDouble("longitude"), res.getString("foto1"), res.getString("foto2"), res.getString("foto_externa"), res.getFloat("valordiario"), res.getString("descricao"), res.getInt("id"), new ArrayList<Disponibilidade>()));
			}
			if(!achou){
				System.out.println("Nenhuma acomodacao encontrada");
			}
		} catch (SQLException e) {
			
		}
		return lista;
	}

        public ArrayList<Acomodacao> buscar(String endereco, int tipo, int cafeDaManha, float valorDiariaInicial, float valorDiariaFinal){
                ArrayList<Acomodacao> lista = new ArrayList<Acomodacao>();
		PreparedStatement pstmt;
		try {
			pstmt = this.montarComandoBuscaAvancada(endereco, tipo, cafeDaManha, valorDiariaInicial, valorDiariaFinal);
			ResultSet res = pstmt.executeQuery();
			boolean achou = false;
			while(res.next()){
				achou = true;
				lista.add(new Acomodacao(res.getInt("id_colaborador"), res.getInt("tipo"), (res.getInt("cafe") == 1), res.getString("endereco"), res.getDouble("latitude"), res.getDouble("longitude"), res.getString("foto1"), res.getString("foto2"), res.getString("foto_externa"), res.getFloat("valordiario"), res.getString("descricao"), res.getInt("id"), new ArrayList<Disponibilidade>()));
			}
			if(!achou){
				System.out.println("Nenhuma acomodacao encontrado");
			}
		} catch (SQLException e) {
			
		}
                
		return lista;
        }
        
        private PreparedStatement montarComandoBuscaAvancada(String endereco, int tipo, int cafeDaManha, float valorDiariaInicial, float valorDiariaFinal) throws SQLException{
            ArrayList<String> filtros = new ArrayList<String>();
            
            if (!endereco.isEmpty())
                filtros.add(" endereco LIKE '%" + endereco + "%' ");
            
            if (tipo >= 0)
                filtros.add(" tipo = " + tipo + " ");
            if (cafeDaManha >= 0)
                filtros.add(" cafe = " + cafeDaManha + " ");
            
            if (valorDiariaInicial > 0 && valorDiariaFinal > valorDiariaInicial)
                filtros.add(" valordiario BETWEEN " + valorDiariaInicial + " AND " + valorDiariaFinal + " ");
            
            String comando = "SELECT * FROM acomodacao  ";
            
            if (!filtros.isEmpty()){
                comando += " WHERE ";
                comando += filtros.get(0);
                filtros.remove(0);
            }
            
            for (String filtro : filtros){
                comando += " AND ";
                comando += filtro;
            }
            
            return this.conn.prepareStatement(comando);
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
			acomodacao = new Acomodacao(res.getInt("id_colaborador"), res.getInt("tipo"), (res.getInt("cafe") == 1), res.getString("endereco"), res.getDouble("latitude"), res.getDouble("longitude"), res.getString("foto1"), res.getString("foto2"), res.getString("foto_externa"), res.getFloat("valordiario"), res.getString("descricao"), res.getInt("id"), new ArrayList<Disponibilidade>());
		} catch (SQLException e) {
			
		}
		return acomodacao;
	}

    public int alterar(Acomodacao a) {
        PreparedStatement pstmt;
		try {
			pstmt = this.conn.prepareStatement("UPDATE acomodacao SET " +
				"id_colaborador = ?, tipo = ?, cafe = ?, endereco = ?, latitude = ?, longitude = ?, foto1 = ?, foto2 = ?, foto_externa = ?, valordiario = ?, descricao = ? " +
				" WHERE id = ? AND id_colaborador = ?", Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, a.getIdColaborador());
			pstmt.setInt(2, a.getTipo());
			pstmt.setInt(3, a.isCafe()?1:0);
			pstmt.setString(4, a.getEndereco());
			pstmt.setDouble(5, a.getLatitude());
			pstmt.setDouble(6, a.getLongitude());
			pstmt.setString(7, a.getFoto1());
			pstmt.setString(8, a.getFoto2());
			pstmt.setString(9, a.getFotoExterna());
			pstmt.setFloat(10, a.getValorDiario());
			pstmt.setString(11, a.getDescricao());
                        pstmt.setInt(12, a.getId());
                        pstmt.setInt(13, a.getIdColaborador());
			int retorno = pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
		    rs.next();
		    a.setId(rs.getInt(1));
		   
		   return retorno;

		} catch (SQLException e) {
			System.out.println("Problemas ao alterar acomodacao: "+e.getMessage());
			return 0;
		}
    }

}
