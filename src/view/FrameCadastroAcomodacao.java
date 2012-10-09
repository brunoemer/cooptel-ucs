package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import net.sourceforge.jdatepicker.DateModel;
import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

public class FrameCadastroAcomodacao extends JFrame{
	private GridBagConstraints labelConstraints, fieldConstraints;
	private JLabel labelDescricao, labelEndereco, labelTipo, labelCafeDaManha, 
				   labelValorDaDiaria, labelDisponibilidade, labelDisponibilidadeAte, 
				   labelFotos, labelFotoVistaExterna, labelFotoVistaInterna1, labelFotoVistaInterna2,
				   labelMapa, labelImagemMapa;
	private JTextField textDescricao, textEndereco, textValorDaDiaria;
	private JRadioButton radioSimples, radioDuplo, radioFamilia, radioSim, radioNao;
	private JDatePickerImpl dateInicioDisponibilidade, dateFimDisponibilidade;
	private JButton buttonOK;
	
	public FrameCadastroAcomodacao(){
		this.inicializar();
	}
	
	private void inicializar(){
		this.setTitle("Cadastro de Acomodação");
		this.setSize(700, 950);
		
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
		constraints.ipady = 200;
		constraints.insets = new Insets(0, 0, 10, 0);
		constraints.fill = GridBagConstraints.BOTH;
		
		this.labelFotoVistaExterna = new JLabel();
		this.labelFotoVistaExterna.setBorder(new LineBorder(Color.BLACK));
		this.labelFotoVistaInterna1 = new JLabel();
		this.labelFotoVistaInterna1.setBorder(new LineBorder(Color.BLACK));
		this.labelFotoVistaInterna2 = new JLabel();
		this.labelFotoVistaInterna2.setBorder(new LineBorder(Color.BLACK));
		
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
		this.add(this.buttonOK, this.fieldConstraints);
		this.fieldConstraints.fill = GridBagConstraints.HORIZONTAL;
		this.fieldConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
	}
}
