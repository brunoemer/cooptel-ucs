package persistence;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Acomodacao;
import model.Disponibilidade;

import com.mysql.jdbc.Connection;

public class DisponibilidadePersistence {

    private Connection conn;

    public DisponibilidadePersistence() {
        this.conn = ConnectionSingleton.getInstance().getConnnection();
    }

    public int inserir(Disponibilidade d) {
        PreparedStatement pstmt;
        try {
            pstmt = this.conn.prepareStatement("INSERT INTO disponibilidade "
                    + "(id_acomodacao, dataInicio, dataFim) VALUES "
                    + "(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, d.getIdAcomodacao());
            pstmt.setString(2, new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(d.getDataInicio()));
            pstmt.setString(3, new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(d.getDataFim()));

            int retorno = pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            d.setId(rs.getInt(1));

            return retorno;
        } catch (SQLException e) {
            System.out.println("Problemas ao inserir acomodacao" + e.getMessage());
            return 0;
        }
    }

    public List buscar() {
        List<Disponibilidade> lista = new ArrayList<Disponibilidade>();
        PreparedStatement pstmt;
        try {
            pstmt = this.conn.prepareStatement("SELECT * FROM disponibilidade");
            ResultSet res = pstmt.executeQuery();
            boolean achou = false;
            while (res.next()) {
                achou = true;
                lista.add(new Disponibilidade(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(res.getString("datainicio")),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(res.getString("datafim")),
                        res.getInt("id_acomodacao")));
            }
            if (!achou) {
                System.out.println("Nenhuma acomodacao encontrado");
            }
        } catch (SQLException e) {
        } catch (ParseException pe) {
        }
        return lista;
    }

    public List buscar(Acomodacao a) {
        List<Disponibilidade> lista = new ArrayList<Disponibilidade>();
        PreparedStatement pstmt;
        try {
            pstmt = this.conn.prepareStatement("SELECT * FROM disponibilidade WHERE id_acomodacao = ?");
            pstmt.setInt(1, a.getId());
            ResultSet res = pstmt.executeQuery();
            boolean achou = false;
            while (res.next()) {
                achou = true;
                lista.add(new Disponibilidade(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(res.getString("datainicio")),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(res.getString("datafim")),
                        res.getInt("id_acomodacao")));
            }
            if (!achou) {
                System.out.println("Nenhuma acomodacao encontrado");
            }
        } catch (SQLException e) {
        } catch (ParseException pe) {
        }
        return lista;
    }

    public Disponibilidade consultar(Disponibilidade d) {
        Disponibilidade disponibilidade = null;
        PreparedStatement pstmt;
        try {
            pstmt = this.conn.prepareStatement("SELECT * FROM disponibilidade WHERE id = ? AND id_acomodacao = ?");
            pstmt.setInt(1, d.getId());
            pstmt.setInt(2, d.getIdAcomodacao());
            ResultSet res = pstmt.executeQuery();
            boolean achou = res.next();
            if (achou == false) {
                System.out.println("Nenhuma disponibilidade encontrada");
            }
            disponibilidade = new Disponibilidade(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(res.getString("datainicio")),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(res.getString("datafim")),
                    res.getInt("id_acomodacao"));
        } catch (SQLException e) {
        } catch (ParseException pe) {
        }
        return disponibilidade;
    }

    public int remover(Acomodacao a) {
        PreparedStatement pstmt;
        try {
            pstmt = this.conn.prepareStatement("DELETE FROM disponibilidade WHERE id_acomodacao = ?");
            pstmt.setInt(1, a.getId());

            int retorno = pstmt.executeUpdate();
            
            return retorno;
        } catch (SQLException e) {
            System.out.println("Problemas ao inserir acomodacao" + e.getMessage());
            return 0;
        }
    }
}
