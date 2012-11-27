package view;

import controller.AcomodacaoController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import model.Colaborador;

import controller.ColaboradorController;
import controller.ReservaController;
import controller.UsuarioController;
import java.text.SimpleDateFormat;
import java.util.List;
import model.Acomodacao;
import model.Reserva;

public class FrameCancelaReserva extends JInternalFrame implements ActionListener {
	
	private JTable tableReservas;
	private JScrollPane tableScrollPane;
	private JButton buttonCancelar;
	
	private ReservaController reservaController;
        private AcomodacaoController acomodacaoController;
        
	private List<Reserva> reservas;
	
	public FrameCancelaReserva(){
		this.inicializar();
	}
	
	private void inicializar(){
		this.setTitle("Cancelamento de Reserva");
		this.setSize(500, 300);
		this.setClosable(true);
		this.setMaximizable(true);
		this.setResizable(true);

		this.reservaController = new ReservaController();
		this.acomodacaoController = new AcomodacaoController();
                
		this.setLayout(new BorderLayout());

		this.inicializarTableColaboradores();
		this.inicializarDadosTableColaboradores();
		this.inicializarBotoes();
	}
	
	private void inicializarTableColaboradores(){
		this.tableReservas = new JTable();
		this.tableScrollPane = new JScrollPane(this.tableReservas);
		this.tableReservas.setFillsViewportHeight(true);
		
		this.add(this.tableScrollPane, BorderLayout.CENTER);
	}
	
	private void inicializarDadosTableColaboradores(){
		
                this.reservas = this.reservaController.consultar(UsuarioController.getUsuarioLogado());
		
		String[][] dados = new String[this.reservas.size()][4];
		
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		int i = 0;
		for (Reserva r : this.reservas){
                        Acomodacao a = new Acomodacao();
                        a.setId(r.getIdAcomodacao());
                        a = this.acomodacaoController.detalhar(a);
                        
			dados[i++] = new String[] { a.getDescricao(), format.format(r.getDataInicio()), format.format(r.getDataFim()), "R$" + String.valueOf(r.getValorTotal()) };
		}
		
		String[] colunas = new String[] { "Acomodação", "Data Início", "Data Fim", "Valor Total" }; 
		
		DefaultTableModel model = new CustomTableModel(dados, colunas);
		
		this.tableReservas.setModel(model);
	}
	
	private void inicializarBotoes(){
		this.buttonCancelar = new JButton("Cancelar");
		this.buttonCancelar.addActionListener(this);
		this.add(this.buttonCancelar, BorderLayout.PAGE_END);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.buttonCancelar){
			int index = this.tableReservas.getSelectedRow();
			
			if (index >= 0){
				
			}
			else{
				JOptionPane.showMessageDialog(this, "Selecione um colaborador!", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
