package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import persistence.ConnectionSingleton;

public class Desktop extends JFrame implements ActionListener {
	private JDesktopPane desktopPane;

	private JMenuBar menuBar;
	private JMenu mArquivo;
	private JMenuItem miSair;
	private JMenu mColaborador;
	private JMenuItem miColaboradorCad;
	private JMenuItem miColaboradorLiberarAcesso;
	private JMenu mAcomodacao;
	private JMenuItem miAcomodacaoCad;

	private FrameCadastroColaborador cc;
	private FrameCadastroAcomodacao ca;
	private FrameLiberacaoAcesso la;
	
	/**
	 * Metodo que inicia os componentes
	 */
	public void initComponents(){
		this.setTitle("Cooptel");
		
		this.desktopPane = new JDesktopPane();
		this.desktopPane.setSize(600, 400);
		
		this.mArquivo = new JMenu("Arquivo");
		this.miSair = new JMenuItem("Sair");
		this.miSair.addActionListener(this);
		this.mArquivo.add(this.miSair);
		
		this.mColaborador = new JMenu("Colaborador");
		this.miColaboradorCad = new JMenuItem("Cadastro");
		this.miColaboradorCad.addActionListener(this);
		this.mColaborador.add(this.miColaboradorCad);
		this.miColaboradorLiberarAcesso = new JMenuItem("Liberar Acesso");
		this.miColaboradorLiberarAcesso.addActionListener(this);
		this.mColaborador.add(this.miColaboradorLiberarAcesso);
		
		this.mAcomodacao = new JMenu("Acomodação");
		this.miAcomodacaoCad = new JMenuItem("Cadastro");
		this.miAcomodacaoCad.addActionListener(this);
		this.mAcomodacao.add(this.miAcomodacaoCad);
		
		this.menuBar = new JMenuBar();
		this.menuBar.add(this.mArquivo);
		this.menuBar.add(this.mColaborador);
		this.menuBar.add(this.mAcomodacao);
		this.setJMenuBar(this.menuBar);
		
		this.getContentPane().add(this.desktopPane);
		this.setSize(1024, 900);
	}
	
	/**
	 * Metodo Action Performed que controla todos eventos da classe
	 * 
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.miSair) { 
			this.acaoSair();
		} else if(e.getSource() == this.miColaboradorCad) {
			this.cc = new FrameCadastroColaborador();
			this.cc.setVisible(true);
			desktopPane.add(this.cc);
		} else if(e.getSource() == this.miAcomodacaoCad) {
			this.ca = new FrameCadastroAcomodacao();
			this.ca.setVisible(true);
			desktopPane.add(this.ca);
		} else if (e.getSource() == this.miColaboradorLiberarAcesso){
			this.la = new FrameLiberacaoAcesso();
			this.la.setVisible(true);
			desktopPane.add(this.la);
		}
	}
	
	/**
	 * Metodo SingleTin para encerrar a conecção com o BD
	 */
	public void acaoSair(){
		ConnectionSingleton conn = ConnectionSingleton.getInstance();
		try {
			if(conn != null){
				conn.getConnnection().close();
			}
		} catch (SQLException e) {
			
		}
		System.exit(0);
	}
	
}
