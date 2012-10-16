package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import model.Acomodacao;
import model.Colaborador;
import model.Disponibilidade;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import controller.AcomodacaoController;

public class FrameCadastroAcomodacao extends JInternalFrame implements ActionListener, MouseListener{
	private GridBagConstraints labelConstraints, fieldConstraints;
	private JLabel labelDescricao, labelEndereco, labelTipo, labelCafeDaManha, 
				   labelValorDaDiaria, labelDisponibilidade, labelDisponibilidadeAte, 
				   labelFotos, labelFotoVistaExterna, labelFotoVistaInterna1, labelFotoVistaInterna2,
				   labelMapa, labelImagemMapa;
	private JTextField textDescricao, textEndereco, textValorDaDiaria;
	private JRadioButton radioSimples, radioDuplo, radioFamilia, radioSim, radioNao;
	private JDatePickerImpl dateInicioDisponibilidade, dateFimDisponibilidade;
	private JButton buttonOK;
	
	private AcomodacaoController acomodacaoController;
	private String caminhoFotoVistaExterna;
	private String caminhoFotoVistaInterna1;
	private String caminhoFotoVistaInterna2;
	
	public FrameCadastroAcomodacao(){
		this.inicializar();
	}
	
	private void inicializar(){
		this.setTitle("Cadastro de Acomodação");
		this.setSize(700, 750);
		this.setClosable(true);
		this.setMaximizable(true);
		
		this.acomodacaoController = new AcomodacaoController();
		
		this.inicializarConstraints();
		this.setLayout(new GridBagLayout());
		this.inicializarCamposDescricao();
		this.inicializarCamposEndereco();
		this.inicializarCamposTipo();
		this.inicializarCamposCafeDaManha();
		this.inicializarCamposValorDaDiaria();
		this.inicializarCamposDisponbilidade();
		this.inicializarCamposFotos();
		this.inicializarCamposMapa();
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
	
	private void inicializarCamposDescricao(){
		this.labelDescricao = new JLabel("Descrição");
		this.add(this.labelDescricao, this.labelConstraints);
		this.labelConstraints.gridy++;
		
		this.textDescricao = new JTextField();
		this.add(this.textDescricao, this.fieldConstraints);
		this.fieldConstraints.gridy++;
	}
	
	private void inicializarCamposEndereco() {
		this.labelEndereco = new JLabel("Endereço");
		this.add(this.labelEndereco, this.labelConstraints);
		this.labelConstraints.gridy++;
		
		this.textEndereco = new JTextField();
		this.add(this.textEndereco, this.fieldConstraints);
		this.fieldConstraints.gridy++;
	}
	
	private void inicializarCamposTipo(){
		this.labelTipo = new JLabel("Tipo");
		this.add(this.labelTipo, this.labelConstraints);
		this.labelConstraints.gridy++;
		
		Panel panel = new Panel();
		panel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 0));
		
		this.radioSimples = new JRadioButton("Simples");
		this.radioDuplo = new JRadioButton("Duplo");
		this.radioFamilia = new JRadioButton("Família");
		
		panel.add(this.radioSimples);
		panel.add(this.radioDuplo);
		panel.add(this.radioFamilia);
		
		ButtonGroup group = new ButtonGroup();
		group.add(this.radioSimples);
		group.add(this.radioDuplo);
		group.add(this.radioFamilia);
		
		this.add(panel, this.fieldConstraints);
		this.fieldConstraints.gridy++;
	}

	private void inicializarCamposCafeDaManha(){
		this.labelCafeDaManha = new JLabel("Café da Manhã");
		this.add(this.labelCafeDaManha, this.labelConstraints);
		this.labelConstraints.gridy++;
		
		Panel panel = new Panel();
		panel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 0));
		
		this.radioSim = new JRadioButton("Sim");
		this.radioNao = new JRadioButton("Nao");
		
		panel.add(this.radioSim);
		panel.add(this.radioNao);
		
		ButtonGroup group = new ButtonGroup();
		group.add(this.radioSim);
		group.add(this.radioNao);
		
		this.add(panel, this.fieldConstraints);
		this.fieldConstraints.gridy++;
	}

	private void inicializarCamposValorDaDiaria(){
		this.labelValorDaDiaria = new JLabel("Valor da Diária");
		this.add(this.labelValorDaDiaria, this.labelConstraints);
		this.labelConstraints.gridy++;
		
		this.fieldConstraints.fill = GridBagConstraints.NONE;
		this.fieldConstraints.ipadx = 100;
		this.textValorDaDiaria = new JTextField();
		this.add(this.textValorDaDiaria, this.fieldConstraints);
		this.fieldConstraints.fill = GridBagConstraints.HORIZONTAL;
		this.fieldConstraints.ipadx = 0;
		this.fieldConstraints.gridy++;
	}
	
	private void inicializarCamposDisponbilidade(){
		this.labelDisponibilidade = new JLabel("Disponibilidade");
		this.add(this.labelDisponibilidade, this.labelConstraints);
		this.labelConstraints.gridy++;
		
		Panel panel = new Panel();
		panel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 0));
		
		this.dateInicioDisponibilidade = new JDatePickerImpl(new JDatePanelImpl(null));
		this.labelDisponibilidadeAte = new JLabel("até");
		this.dateFimDisponibilidade = new JDatePickerImpl(new JDatePanelImpl(null));
		
		panel.add(dateInicioDisponibilidade, this.labelConstraints);
		panel.add(this.labelDisponibilidadeAte);
		panel.add(dateFimDisponibilidade, this.labelConstraints);
		
		this.add(panel, this.fieldConstraints);
		this.fieldConstraints.gridy++;
	}
	
	private void inicializarCamposFotos(){
		this.labelFotos = new JLabel("Fotos");
		this.add(this.labelFotos, this.labelConstraints);
		this.labelConstraints.gridy++;
		
		Panel panel = new Panel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.ipady = 100;
		constraints.insets = new Insets(0, 0, 10, 0);
		constraints.fill = GridBagConstraints.BOTH;
		
		this.labelFotoVistaExterna = new JLabel();
		this.labelFotoVistaExterna.setBorder(new LineBorder(Color.BLACK));
		this.labelFotoVistaExterna.addMouseListener(this);
		this.labelFotoVistaInterna1 = new JLabel();
		this.labelFotoVistaInterna1.setBorder(new LineBorder(Color.BLACK));
		this.labelFotoVistaInterna1.addMouseListener(this);
		this.labelFotoVistaInterna2 = new JLabel();
		this.labelFotoVistaInterna2.setBorder(new LineBorder(Color.BLACK));
		this.labelFotoVistaInterna2.addMouseListener(this);
		
		constraints.gridwidth = 2;
		panel.add(this.labelFotoVistaExterna, constraints);
		constraints.insets = new Insets(0, 0, 10, 10);
		constraints.gridwidth = 1;
		constraints.gridy++; 
		panel.add(this.labelFotoVistaInterna1, constraints);
		constraints.insets = new Insets(0, 0, 10, 0);
		constraints.gridx++;
		panel.add(this.labelFotoVistaInterna2, constraints);
		
		this.add(panel, this.fieldConstraints);
		this.fieldConstraints.gridy++;
	}

	private void inicializarCamposMapa(){
		this.labelMapa = new JLabel("Mapa");
		this.add(this.labelMapa, this.labelConstraints);
		this.labelConstraints.gridy++;
		
		this.fieldConstraints.fill = GridBagConstraints.NONE;
		ImageIcon icon = new ImageIcon("Mapa.png");
		this.labelImagemMapa = new JLabel(icon);
		this.labelImagemMapa.setBorder(new LineBorder(Color.BLACK));
		this.add(this.labelImagemMapa, this.fieldConstraints);
		this.fieldConstraints.fill = GridBagConstraints.HORIZONTAL;
		this.fieldConstraints.gridy++;
	}
	
	private void inicializarBotoes(){
		this.fieldConstraints.fill = GridBagConstraints.NONE;
		this.fieldConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
		this.buttonOK = new JButton("OK");
		this.buttonOK.addActionListener(this);
		this.add(this.buttonOK, this.fieldConstraints);
		this.fieldConstraints.fill = GridBagConstraints.HORIZONTAL;
		this.fieldConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
	}
	
	public void cadastrar() {
		Acomodacao a = new Acomodacao();
		a.setIdColaborador(1); // VER COMO FAZER
		int tipo = 0;
		if(this.radioSimples.isSelected()){
			tipo = 0;
		}else if(this.radioDuplo.isSelected()){
			tipo = 1;
		}else if(this.radioFamilia.isSelected()){
			tipo = 2;
		}
		a.setTipo(tipo);
		a.setCafe(this.radioSim.isSelected());
		a.setEndereco(textEndereco.getText());
		a.setLatitude(0); // VER
		a.setLongitude(0); // VER
		a.setFoto1(""); // VER
		a.setFoto2(""); // VER
		a.setValorDiario(Float.parseFloat(textValorDaDiaria.getText()));
		a.setDescricao(textDescricao.getText());
		GregorianCalendar data = (GregorianCalendar)this.dateInicioDisponibilidade.getJFormattedTextField().getValue();
		Date dataInicio = new Date(data.get(GregorianCalendar.YEAR) - 1900, data.get(GregorianCalendar.MONTH), data.get(GregorianCalendar.DATE));
		data = (GregorianCalendar)this.dateFimDisponibilidade.getJFormattedTextField().getValue();
		Date dataFim = new Date(data.get(GregorianCalendar.YEAR) - 1900, data.get(GregorianCalendar.MONTH), data.get(GregorianCalendar.DATE));
		List<Disponibilidade> disponibilidades = new ArrayList<Disponibilidade>();
		disponibilidades.add(new Disponibilidade(dataInicio, dataFim, 0));
		a.setDisponibilidades(disponibilidades);
		int sucess = this.acomodacaoController.cadastrar(a);
		if(sucess == 0){
			JOptionPane.showMessageDialog(this, "Erro ao cadastrar", "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(this, "Cadastrado com sucesso", "", JOptionPane.PLAIN_MESSAGE);
			this.setVisible(false);
		}
	}

	public void pesquisar() {

	}

	public void detalhar(Acomodacao a) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.buttonOK){
			this.cadastrar();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
	public void mouseReleased(MouseEvent e) {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            ImageIcon icon = new ImageIcon(file.getAbsolutePath());
            ((JLabel)e.getSource()).setIcon(icon);
            if (e.getSource() == this.labelFotoVistaExterna)
            	this.caminhoFotoVistaExterna = file.getAbsolutePath();
            else if (e.getSource() == this.labelFotoVistaInterna1)
            	this.caminhoFotoVistaInterna1 = file.getAbsolutePath();
            else
        		this.caminhoFotoVistaInterna2 = file.getAbsolutePath();
        }
	}

}
