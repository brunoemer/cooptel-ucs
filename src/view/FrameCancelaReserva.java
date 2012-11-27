package view;

import controller.AcomodacaoController;
import controller.ReservaController;
import controller.UsuarioController;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Acomodacao;
import model.Disponibilidade;
import model.Reserva;

public class FrameCancelaReserva extends JInternalFrame implements ActionListener {

    private JTable tableReservas;
    private JScrollPane tableScrollPane;
    private JButton buttonCancelar;
    private ReservaController reservaController;
    private AcomodacaoController acomodacaoController;
    private List<Reserva> reservas;

    public FrameCancelaReserva() {
        this.inicializar();
    }

    private void inicializar() {
        this.setTitle("Cancelamento de Reserva");
        this.setSize(500, 300);
        this.setClosable(true);
        this.setMaximizable(true);
        this.setResizable(true);

        this.reservaController = new ReservaController();
        this.acomodacaoController = new AcomodacaoController();

        this.setLayout(new BorderLayout());

        this.inicializarTable();
        this.inicializarDadosTable();
        this.inicializarBotoes();
    }

    private void inicializarTable() {
        this.tableReservas = new JTable();
        this.tableScrollPane = new JScrollPane(this.tableReservas);
        this.tableReservas.setFillsViewportHeight(true);

        this.add(this.tableScrollPane, BorderLayout.CENTER);
    }

    private void inicializarDadosTable() {

        this.reservas = this.reservaController.consultar(UsuarioController.getUsuarioLogado());

        String[][] dados = new String[this.reservas.size()][4];

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        int i = 0;
        for (Reserva r : this.reservas) {
            Acomodacao a = new Acomodacao();
            a.setId(r.getIdAcomodacao());
            a = this.acomodacaoController.detalhar(a);

            dados[i++] = new String[]{a.getDescricao(), format.format(r.getDataInicio()), format.format(r.getDataFim()), "R$" + String.valueOf(r.getValorTotal())};
        }

        String[] colunas = new String[]{"Acomodação", "Data Início", "Data Fim", "Valor Total"};

        DefaultTableModel model = new CustomTableModel(dados, colunas);

        this.tableReservas.setModel(model);
    }

    private void inicializarBotoes() {
        this.buttonCancelar = new JButton("Cancelar");
        this.buttonCancelar.addActionListener(this);
        this.add(this.buttonCancelar, BorderLayout.PAGE_END);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.buttonCancelar) {
            int index = this.tableReservas.getSelectedRow();

            if (index >= 0) {
                Reserva r = this.reservas.get(index);
                Date dataAtual = new Date();

                if (dataAtual.compareTo(r.getDataInicio()) >= 0) {
                    JOptionPane.showMessageDialog(this, "Data atual deve ser menor que a data de início da reserva!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (dataAtual.compareTo(r.getDataInicio()) >= 0) {
                    JOptionPane.showMessageDialog(this, "Data atual deve ser menor que a data de início da reserva!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Acomodacao a = new Acomodacao();
                a.setId(r.getIdAcomodacao());
                        
                a = this.acomodacaoController.detalhar(a);
                
                if (JOptionPane.showConfirmDialog(this, "Tem certeza que deseja cancelar a reserva?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    a.getDisponibilidades().add(new Disponibilidade(r.getDataInicio(), r.getDataFim(), a.getId()));
                    this.acomodacaoController.alterar(a);
                    this.reservaController.cancelar(r);
                    
                    this.inicializarDadosTable();
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma reserva!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
