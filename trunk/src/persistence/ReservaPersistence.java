package persistence;

import com.mysql.jdbc.Connection;
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
import model.Reserva;
import model.Usuario;

public class ReservaPersistence {

    private Connection conn;

    public ReservaPersistence() {
        this.conn = ConnectionSingleton.getInstance().getConnnection();
    }

    public int inserir(Reserva r) {
        PreparedStatement pstmt;
        try {
            pstmt = this.conn.prepareStatement("INSERT INTO reserva "
                    + "(id_acomodacao, id_usuario, dataInicio, dataFim, valortotal) VALUES "
                    + "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, r.getIdAcomodacao());
            pstmt.setInt(2, r.getIdUsuario());
            pstmt.setString(3, new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(r.getDataInicio()));
            pstmt.setString(4, new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(r.getDataFim()));
            pstmt.setFloat(5, r.getValorTotal());

            int retorno = pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            r.setId(rs.getInt(1));

            return retorno;
        } catch (SQLException e) {
            System.out.println("Problemas ao inserir reserva" + e.getMessage());
            return 0;
        }
    }

    public List buscar() {
        List<Reserva> lista = new ArrayList<Reserva>();
        PreparedStatement pstmt;
        try {
            pstmt = this.conn.prepareStatement("SELECT * FROM reserva");
            ResultSet res = pstmt.executeQuery();
            boolean achou = false;
            while (res.next()) {
                achou = true;
                lista.add(new Reserva(res.getInt("id"), res.getInt("id_acomodacao"), res.getInt("id_usuario"),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(res.getString("datainicio")),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(res.getString("datafim")),
                        res.getFloat("valortotal")));
            }
            if (!achou) {
                System.out.println("Nenhuma reserva encontrada");
            }
        } catch (SQLException e) {
        } catch (ParseException pe) {
        }
        return lista;
    }

    public List buscar(Acomodacao a) {
        List<Reserva> lista = new ArrayList<Reserva>();
        PreparedStatement pstmt;
        try {
            pstmt = this.conn.prepareStatement("SELECT * FROM reserva WHERE id_acomodacao = ?");
            pstmt.setInt(1, a.getId());
            ResultSet res = pstmt.executeQuery();
            boolean achou = false;
            while (res.next()) {
                achou = true;
                lista.add(new Reserva(res.getInt("id"), res.getInt("id_acomodacao"), res.getInt("id_usuario"),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(res.getString("datainicio")),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(res.getString("datafim")),
                        res.getFloat("valortotal")));
            }
            if (!achou) {
                System.out.println("Nenhuma reserva encontrada");
            }
        } catch (SQLException e) {
        } catch (ParseException pe) {
        }
        return lista;
    }

    public List buscar(Usuario u) {
        List<Reserva> lista = new ArrayList<Reserva>();
        PreparedStatement pstmt;
        try {
            pstmt = this.conn.prepareStatement("SELECT * FROM reserva WHERE id_usuario = ?");
            pstmt.setInt(1, u.getId());
            ResultSet res = pstmt.executeQuery();
            boolean achou = false;
            while (res.next()) {
                achou = true;
                lista.add(new Reserva(res.getInt("id"), res.getInt("id_acomodacao"), res.getInt("id_usuario"),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(res.getString("datainicio")),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(res.getString("datafim")),
                        res.getFloat("valortotal")));
            }
            if (!achou) {
                System.out.println("Nenhuma reserva encontrada");
            }
        } catch (SQLException e) {
        } catch (ParseException pe) {
        }
        return lista;
    }

    public Reserva consultar(Reserva r) {
        Reserva reserva = null;
        PreparedStatement pstmt;
        try {
            pstmt = this.conn.prepareStatement("SELECT * FROM reserva WHERE id = ?");
            pstmt.setInt(1, r.getId());
            ResultSet res = pstmt.executeQuery();
            boolean achou = res.next();
            if (achou == false) {
                System.out.println("Nenhuma reserva encontrada");
            }
            reserva = new Reserva(res.getInt("id"), res.getInt("id_acomodacao"), res.getInt("id_usuario"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(res.getString("datainicio")),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(res.getString("datafim")),
                    res.getFloat("valortotal"));
        } catch (SQLException e) {
        } catch (ParseException pe) {
        }
        return reserva;
    }

    public int remover(Reserva r) {
        PreparedStatement pstmt;
        try {
            pstmt = this.conn.prepareStatement("DELETE FROM reserva "
                    + " WHERE id = ? ", Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, r.getId());

            int retorno = pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            r.setId(rs.getInt(1));

            return retorno;
        } catch (SQLException e) {
            System.out.println("Problemas ao remover reserva" + e.getMessage());
            return 0;
        }
    }
}
