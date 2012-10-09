package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FrameCadastroColaborador extends JFrame{
	private GridBagConstraints labelConstraints, fieldConstraints;
	private JLabel labelCpf, labelNome, labelEmail, labelEndereco, labelUsuario, labelSenha;
	private JTextField textCpf, textNome, textEmail, textEndereco, textUsuario;
	private JPasswordField fieldSenha;
	private JButton buttonOK, buttonAcomodacoes;
	
	public FrameCadastroColaborador(){
		this.inicializar();
	}
	
	private void inicializar(){
		this.setTitle("Cadastro de Colaborador");
		this.setSize(400, 240);
		
		this.inicializarConstraints();
		this.setLayout(new GridBagLayout());
		this.InicializarCamposCpf();
		this.InicializarCamposNome();
		this.InicializarCamposEmail();
		this.InicializarCamposEndereco();
		this.inicializarCamposUsuarioSenha();
		this.inicializarBotoes();
	}
	
	private void inicializarConstraints(){
		this.labelConstraints = new GridBagConstraints();
		this.labelConstraints.fill = GridBagConstraints.HORIZONTAL;
		this.labelConstraints.gridx = 0;
		this.labelConstraints.gridy = 0;
		this.labelConstraints.insets = new Insets(10, 5, 0, 10);
		this.labelConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		
		this.fieldConstraints = new GridBagConstraints();
		this.fieldConstraints.fill = GridBagConstraints.HORIZONTAL;
		this.fieldConstraints.gridx = 1;
		this.fieldConstraints.gridy = 0;
		this.fieldConstraints.weightx = 1.0;
		this.fieldConstraints.insets = new Insets(10, 5, 0, 10);
		this.fieldConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
	}
	
	private void InicializarCamposCpf(){
		this.labelCpf = new JLabel("CPF");
		this.add(this.labelCpf, this.labelConstraints);
		this.labelConstraints.gridy++;
		
		this.textCpf = new JTextField();
		this.add(this.textCpf, this.fieldConstraints);
		this.fieldConstraints.gridy++;
	}
	
	private void InicializarCamposNome(){
		this.labelNome = new JLabel("Nome");
		this.add(this.labelNome, this.labelConstraints);
		this.labelConstraints.gridy++;
		
		this.textNome = new JTextField();
		this.add(this.textNome, this.fieldConstraints);
		this.fieldConstraints.gridy++;
	}
	
	private void InicializarCamposEmail(){
		this.labelEmail = new JLabel("E-mail");
		this.add(this.labelEmail, this.labelConstraints);
		this.labelConstraints.gridy++;
		
		this.textEmail = new JTextField();
		this.add(this.textEmail, this.fieldConstraints);
		this.fieldConstraints.gridy++;
	}
	
	private void InicializarCamposEndereco(){
		this.labelEndereco = new JLabel("Endereço");
		this.add(this.labelEndereco, this.labelConstraints);
		this.labelConstraints.gridy++;
		
		this.textEndereco = new JTextField();
		this.add(this.textEndereco, this.fieldConstraints);
		this.fieldConstraints.gridy++;
	}
	
	private void inicializarCamposUsuarioSenha(){
		Panel panel = new Panel();
		panel.setLayout(new FlowLayout(FlowLayout.LEADING, 15, 0));
		
		this.labelUsuario = new JLabel("Usuário");
		this.textUsuario = new JTextField();
		this.textUsuario.setPreferredSize(new Dimension(100, 20));
		this.labelSenha = new JLabel("Senha");
		this.fieldSenha = new JPasswordField();
		this.fieldSenha.setPreferredSize(new Dimension(100, 20));
		
		panel.add(this.labelUsuario);
		panel.add(this.textUsuario);
		panel.add(this.labelSenha);
		panel.add(this.fieldSenha);
		
		this.labelConstraints.gridwidth = 2;
		this.add(panel, this.labelConstraints);
		this.labelConstraints.gridwidth = 1;
		this.labelConstraints.gridy++;
		this.fieldConstraints.gridy++;
	}
	
	private void inicializarBotoes(){
		this.fieldConstraints.fill = GridBagConstraints.BOTH;
		this.fieldConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
		
		Panel panel = new Panel();
		panel.setLayout(new FlowLayout(FlowLayout.TRAILING));
		this.buttonAcomodacoes = new JButton("Acomodações");
		this.buttonOK = new JButton("OK");
		panel.add(this.buttonAcomodacoes);
		panel.add(this.buttonOK);
		
		this.add(panel, this.fieldConstraints);
		this.fieldConstraints.fill = GridBagConstraints.HORIZONTAL;
		this.fieldConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
	}
}
