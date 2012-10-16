package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

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

import model.Acomodacao;
import model.Colaborador;

import controller.AcomodacaoController;
import controller.ColaboradorController;

public class FrameListaAcomodacao extends JInternalFrame implements ActionListener, MouseListener {
	private JLabel labelPesquisar;
	private JTextField fieldPesquisar;
	private JTable table;
	private JScrollPane tableScrollPane;
	private JButton buttonLiberar;
	
	private AcomodacaoController acomodacaoController;
	private List<Acomodacao> acomodacoes;
	
	public FrameListaAcomodacao(){
		this.inicializar();
	}
	
	private void inicializar(){
		this.setTitle("Lista acomodação");
		this.setSize(700, 750);
		this.setClosable(true);
		this.setMaximizable(true);

		this.acomodacaoController = new AcomodacaoController();
		
		this.setLayout(new BorderLayout());
		this.inicializarCamposBusca();
		this.inicializarTable();
		this.inicializarDadosTable();
	}
	
	private void inicializarCamposBusca(){
		ImageIcon icon = new ImageIcon("Lupa.png");
		this.labelPesquisar = new JLabel(icon);
		
		this.fieldPesquisar = new JTextField();
		this.fieldPesquisar.setPreferredSize(new Dimension(300, 20));

		this.fieldPesquisar.getDocument().addDocumentListener(new DocumentListener() {
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
			  	inicializarDadosTable();
		     }
		  }
		);
		
		Panel panel = new Panel();
		panel.setLayout(new BorderLayout());
		
		panel.add(this.labelPesquisar, BorderLayout.LINE_START);
		panel.add(this.fieldPesquisar, BorderLayout.CENTER);
		
		this.add(panel, BorderLayout.PAGE_START);
	}
	
	private void inicializarTable(){
		this.table = new JTable();
		this.tableScrollPane = new JScrollPane(this.table);
		this.table.setFillsViewportHeight(true);
		this.table.addMouseListener(this);
		
		this.add(this.tableScrollPane, BorderLayout.CENTER);
	}
	
	private void inicializarDadosTable(){
		this.acomodacoes = this.acomodacaoController.consultar();
		
		if (this.fieldPesquisar.getText() != ""){
			ArrayList<Acomodacao> remover = new ArrayList<Acomodacao>();
			
			for (Acomodacao a : this.acomodacoes){
				if (!a.getEndereco().matches("(?i).*" + this.fieldPesquisar.getText() + ".*"))
					remover.add(a);
			}
			
			for (Acomodacao a : remover){
				this.acomodacoes.remove(a);
			}
		}
		
		String[][] dados = new String[this.acomodacoes.size()][4];
		
		int i = 0;
		for (Acomodacao a : this.acomodacoes){
			dados[i++] = new String[] { a.getDescricao(), a.getEndereco() };
		}
		
		String[] colunas = new String[] { "Descrição", "Endereço" }; 
		
		DefaultTableModel model = new CustomTableModel(dados, colunas);
		
		this.table.setModel(model);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int index = this.table.getSelectedRow();
		if (index >= 0){
			Acomodacao a = this.acomodacoes.get(index);
			FormDetalhesAcomodacao da = new FormDetalhesAcomodacao(a);
			da.setVisible(true);
			this.getParent().add(da);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
