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
	private JMenu mCadastros;
	private JMenuItem miSair;
	private JMenuItem miCadColaborador;
	private JMenuItem miCadAcomodacao;

	private FrameCadastroColaborador cc;
	private FrameCadastroAcomodacao ca;
	
	/**
	 * Metodo que inicia os componentes
	 */
	public void initComponents(){
		this.setTitle("Calculadora campeonatos");
		
		this.desktopPane = new JDesktopPane();
		this.desktopPane.setSize(600, 400);
		
		mArquivo = new JMenu("Arquivo");
		miSair = new JMenuItem("Sair");
		miSair.addActionListener(this);
		mArquivo.add(miSair);
		
		mCadastros = new JMenu("Cadastros");
		miCadColaborador = new JMenuItem("Colaborador");
		miCadColaborador.addActionListener(this);
		mCadastros.add(miCadColaborador);
		miCadAcomodacao = new JMenuItem("Acomodação");
		miCadAcomodacao.addActionListener(this);
		mCadastros.add(miCadAcomodacao);

		menuBar = new JMenuBar();
		menuBar.add(mArquivo);
		menuBar.add(mCadastros);
		this.setJMenuBar(menuBar);
		
		this.getContentPane().add(this.desktopPane);
		this.setSize(1024, 900);

		cc = new FrameCadastroColaborador();
		ca = new FrameCadastroAcomodacao();
		
	}
	
	/**
	 * Metodo Action Performed que controla todos eventos da classe
	 * 
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == miSair) { 
			this.acaoSair();
		} else if(e.getSource() == miCadColaborador) {
			cc.setVisible(true);
			desktopPane.add(cc);
		} else if(e.getSource() == miCadAcomodacao) {
			ca.setVisible(true);
			desktopPane.add(ca);
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
