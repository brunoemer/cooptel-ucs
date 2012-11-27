package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Usuario;
import model.Visitante;
import controller.UsuarioController;
import controller.VisitanteController;

public class FrameCadastroVisitante extends JInternalFrame implements ActionListener{
	private GridBagConstraints labelConstraints, fieldConstraints;
	private JLabel labelNome, labelPais, labelUsuario, labelSenha;
	private JTextField textNome, textPais, textUsuario;
	private JPasswordField fieldSenha;
	private JButton buttonOK;
	
	private VisitanteController visitanteController;
	private UsuarioController usuarioController;

	public FrameCadastroVisitante(){
		this.inicializar();
	}
	
	private void inicializar(){
		this.setTitle("Cadastro de Visitante");
		this.setSize(400, 240);
		this.setClosable(true);
		this.setMaximizable(true);
		this.setResizable(true);
		
		this.visitanteController = new VisitanteController();
		this.usuarioController = new UsuarioController();
		
		this.inicializarConstraints();
		this.setLayout(new GridBagLayout());
		this.inicializarCamposNome();
		this.inicializarCamposPais();
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
	
	private void inicializarCamposNome(){
		this.labelNome = new JLabel("Nome");
		this.add(this.labelNome, this.labelConstraints);
		this.labelConstraints.gridy++;
		
		this.textNome = new JTextField();
		this.add(this.textNome, this.fieldConstraints);
		this.fieldConstraints.gridy++;
	}
	
	private void inicializarCamposPais(){
		this.labelPais = new JLabel("Pais");
		this.add(this.labelPais, this.labelConstraints);
		this.labelConstraints.gridy++;
		
		this.textPais = new JTextField();
		this.add(this.textPais, this.fieldConstraints);
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
		this.buttonOK = new JButton("OK");
		this.buttonOK.addActionListener(this);
		panel.add(this.buttonOK);
		
		this.add(panel, this.fieldConstraints);
		this.fieldConstraints.fill = GridBagConstraints.HORIZONTAL;
		this.fieldConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
	}
	
	public void cadastrar() {
		Usuario u = new Usuario();
		u.setLogin(textUsuario.getText());
		u.setSenha(fieldSenha.getText());
		u.setTipo(Usuario.TIPO_VISITANTE);
		int resUsuario = this.usuarioController.cadastrar(u);
		
		Visitante v = new Visitante();
		v.setIdUsuario(u.getId());
		v.setNome(textNome.getText());
		v.setPaisOrigem(textPais.getText());
		int res = this.visitanteController.cadastrar(v);
		
		if(res == 0 || resUsuario == 0){
			JOptionPane.showMessageDialog(this, "Erro ao cadastrar", "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(this, "Cadastrado com sucesso", "", JOptionPane.PLAIN_MESSAGE);
			this.setVisible(false);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.buttonOK){
			this.cadastrar();
		}
	}
}
