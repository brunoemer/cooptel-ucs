package view;

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

public class FrameLiberacaoAcesso extends JInternalFrame implements ItemListener, ActionListener {
	private JLabel labelPesquisarColaborador;
	private JTextField fieldColaborador;
	private JCheckBox checkBoxNaoLiberados;
	private JTable tableColaboradores;
	private JScrollPane tableScrollPane;
	private JButton buttonLiberar;
	
	private ColaboradorController colaboradorController;
	private ArrayList<Colaborador> colaboradores;
	
	public FrameLiberacaoAcesso(){
		this.inicializar();
	}
	
	private void inicializar(){
		this.setTitle("Liberação de Acesso");
		this.setSize(500, 300);
		this.setClosable(true);
		this.setMaximizable(true);

		this.colaboradorController = new ColaboradorController();
		
		this.setLayout(new BorderLayout());
		this.inicializarCamposBusca();
		this.inicializarTableColaboradores();
		this.inicializarDadosTableColaboradores();
		this.inicializarBotoes();
	}
	
	private void inicializarCamposBusca(){
		ImageIcon icon = new ImageIcon("Lupa.png");
		this.labelPesquisarColaborador = new JLabel(icon);
		
		this.fieldColaborador = new JTextField();
		this.fieldColaborador.setPreferredSize(new Dimension(300, 20));

		this.fieldColaborador.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
		    warn();
		  }
		  public void removeUpdate(DocumentEvent e) {
		    warn();
		  }
		  public void insertUpdate(DocumentEvent e) {
		    warn();
		  }

		  public void warn() {
			  	inicializarDadosTableColaboradores();
		     }
		  }
		);
		
		this.checkBoxNaoLiberados = new JCheckBox("Nao liberados");
		this.checkBoxNaoLiberados.addItemListener(this);
		
		Panel panel = new Panel();
		panel.setLayout(new BorderLayout());
		
		panel.add(this.labelPesquisarColaborador, BorderLayout.LINE_START);
		panel.add(this.fieldColaborador, BorderLayout.CENTER);
		panel.add(this.checkBoxNaoLiberados, BorderLayout.LINE_END);
		
		this.add(panel, BorderLayout.PAGE_START);
	}
	
	private void inicializarTableColaboradores(){
		this.tableColaboradores = new JTable();
		this.tableScrollPane = new JScrollPane(this.tableColaboradores);
		this.tableColaboradores.setFillsViewportHeight(true);
		
		this.add(this.tableScrollPane, BorderLayout.CENTER);
	}
	
	private void inicializarDadosTableColaboradores(){
		
		if (this.checkBoxNaoLiberados.isSelected())
			this.colaboradores = this.colaboradorController.consultarInativos();
		else
			this.colaboradores = this.colaboradorController.consultar();
		
		if (this.fieldColaborador.getText() != ""){
			ArrayList<Colaborador> remover = new ArrayList<Colaborador>();
			
			for (Colaborador c : this.colaboradores){
				if (!c.getNome().matches("(?i).*" + this.fieldColaborador.getText() + ".*"))
					remover.add(c);
			}
			
			for (Colaborador c : remover){
				this.colaboradores.remove(c);
			}
		}
		
		String[][] dados = new String[colaboradores.size()][4];
		
		int i = 0;
		for (Colaborador c : this.colaboradores){
			dados[i++] = new String[] { c.getCpf(), c.getNome(), c.getEmail(), c.getAtivo() ? "Sim" : "N�o" };
		}
		
		String[] colunas = new String[] { "CPF", "Nome", "E-mail", "Liberado" }; 
		
		DefaultTableModel model = new CustomTableModel(dados, colunas);
		
		this.tableColaboradores.setModel(model);
	}
	
	private void inicializarBotoes(){
		this.buttonLiberar = new JButton("Liberar");
		this.buttonLiberar.addActionListener(this);
		this.add(this.buttonLiberar, BorderLayout.PAGE_END);
	}
	
	public void itemStateChanged(ItemEvent e) {
	    Object source = e.getItemSelectable();

	    if (source == this.checkBoxNaoLiberados) {
	        this.inicializarDadosTableColaboradores();
	    }
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.buttonLiberar){
			int index = this.tableColaboradores.getSelectedRow();
			
			if (index >= 0){
				Colaborador c = this.colaboradores.get(index);
				if (!c.getAtivo()){
					c.setAtivo(true);
					this.colaboradorController.liberar(c);
					this.inicializarDadosTableColaboradores();
					JOptionPane.showMessageDialog(this, "Colaborador liberado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(this, "Este colaborador já está liberado!", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(this, "Selecione um colaborador!", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
