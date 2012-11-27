package view;

import controller.AcomodacaoController;
import controller.ReservaController;
import controller.UsuarioController;
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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Acomodacao;
import model.Disponibilidade;
import model.Reserva;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

public class FrameReservaAcomodacao extends JInternalFrame implements ActionListener, MouseListener {

    private GridBagConstraints labelConstraints, fieldConstraints;
    private JLabel labelTipo, labelCafeDaManha, labelValorDaDiaria, labelValorDaDiariaAte, labelDisponibilidade, labelDisponibilidadeAte, labelEndereco, labelPeriodoReserva, labelReservaAte;
    private JTextField textValorDaDiariaInicial, textValorDaDiariaFinal, textEndereco;
    private JRadioButton radioSimples, radioDuplo, radioFamilia, radioTipoTodos, radioSim, radioNao, radioCafeTodos;
    private JDatePickerImpl dateInicioDisponibilidade, dateFimDisponibilidade, dateInicioReserva, dateFimReserva;
    private JButton buttonPesquisar, buttonReservar;
    private JTable tableColaboradores;
    private JScrollPane tableScrollPane;
    private List<Acomodacao> acomodacoes;
    private AcomodacaoController acomodacaoController;
    private ReservaController reservaController;

    public FrameReservaAcomodacao() {
        this.inicializar();
    }

    private void inicializar() {
        this.setTitle("Reserva de Acomodação");
        this.setSize(700, 470);
        this.setClosable(true);
        this.setMaximizable(true);
        this.setResizable(true);

        this.acomodacaoController = new AcomodacaoController();
        this.reservaController = new ReservaController();

        this.inicializarConstraints();
        this.setLayout(new GridBagLayout());
        this.inicializarCamposEndereco();
        this.inicializarCamposTipo();
        this.inicializarCamposCafeDaManha();
        this.inicializarCamposValorDaDiaria();
        this.inicializarCamposDisponbilidade();
        this.inicializarBotaoPesquisar();
        this.inicializarTableColaboradores();
        this.inicializarCamposDataReserva();
        this.inicializarBotaoReservar();
    }

    private void inicializarConstraints() {
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

    private void inicializarCamposEndereco() {
        this.labelEndereco = new JLabel("Endereço");
        this.add(this.labelEndereco, this.labelConstraints);
        this.labelConstraints.gridy++;

        this.textEndereco = new JTextField();
        this.add(this.textEndereco, this.fieldConstraints);
        this.fieldConstraints.gridy++;
    }

    private void inicializarCamposTipo() {
        this.labelTipo = new JLabel("Tipo");
        this.add(this.labelTipo, this.labelConstraints);
        this.labelConstraints.gridy++;

        Panel panel = new Panel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 0));

        this.radioSimples = new JRadioButton("Simples");
        this.radioDuplo = new JRadioButton("Duplo");
        this.radioFamilia = new JRadioButton("Família");
        this.radioTipoTodos = new JRadioButton("Todos");

        panel.add(this.radioSimples);
        panel.add(this.radioDuplo);
        panel.add(this.radioFamilia);
        panel.add(this.radioTipoTodos);

        ButtonGroup group = new ButtonGroup();
        group.add(this.radioSimples);
        group.add(this.radioDuplo);
        group.add(this.radioFamilia);
        group.add(this.radioTipoTodos);

        this.radioTipoTodos.setSelected(true);

        this.add(panel, this.fieldConstraints);
        this.fieldConstraints.gridy++;
    }

    private void inicializarCamposCafeDaManha() {
        this.labelCafeDaManha = new JLabel("Café da Manhã");
        this.add(this.labelCafeDaManha, this.labelConstraints);
        this.labelConstraints.gridy++;

        Panel panel = new Panel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 0));

        this.radioSim = new JRadioButton("Sim");
        this.radioNao = new JRadioButton("Não");
        this.radioCafeTodos = new JRadioButton("Todos");

        panel.add(this.radioSim);
        panel.add(this.radioNao);
        panel.add(this.radioCafeTodos);

        ButtonGroup group = new ButtonGroup();
        group.add(this.radioSim);
        group.add(this.radioNao);
        group.add(this.radioCafeTodos);
        this.radioCafeTodos.setSelected(true);

        this.add(panel, this.fieldConstraints);
        this.fieldConstraints.gridy++;
    }

    private void inicializarCamposValorDaDiaria() {
        this.labelValorDaDiaria = new JLabel("Valor da Diária");
        this.add(this.labelValorDaDiaria, this.labelConstraints);
        this.labelConstraints.gridy++;

        Panel panel = new Panel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 0));

        this.textValorDaDiariaInicial = new JTextField();
        this.textValorDaDiariaInicial.setPreferredSize(new Dimension(100, 25));
        this.labelValorDaDiariaAte = new JLabel("até");
        this.textValorDaDiariaFinal = new JTextField();
        this.textValorDaDiariaFinal.setPreferredSize(new Dimension(100, 25));

        panel.add(this.textValorDaDiariaInicial);
        panel.add(this.labelValorDaDiariaAte);
        panel.add(this.textValorDaDiariaFinal);

        this.add(panel, this.fieldConstraints);
        this.fieldConstraints.gridy++;
    }

    private void inicializarCamposDisponbilidade() {
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

    private void inicializarBotaoPesquisar() {
        this.fieldConstraints.fill = GridBagConstraints.NONE;
        this.fieldConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
        this.buttonPesquisar = new JButton("Pesquisar");
        this.buttonPesquisar.addActionListener(this);
        this.add(this.buttonPesquisar, this.fieldConstraints);
        this.fieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        this.fieldConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        this.fieldConstraints.gridy++;
    }

    private void inicializarTableColaboradores() {
        this.tableColaboradores = new JTable();
        this.tableScrollPane = new JScrollPane(this.tableColaboradores);
        this.tableColaboradores.setFillsViewportHeight(true);
        this.tableColaboradores.addMouseListener(this);

        this.labelConstraints.gridwidth = 2;
        this.labelConstraints.gridy++;
        this.labelConstraints.ipady = 100;
        this.fieldConstraints.gridy++;
        this.add(this.tableScrollPane, this.labelConstraints);
        this.labelConstraints.gridwidth = 1;
        this.labelConstraints.gridy++;
        this.labelConstraints.ipady = 0;
    }

    private void inicializarCamposDataReserva() {
        this.labelPeriodoReserva = new JLabel("Período da Reserva");
        this.add(this.labelPeriodoReserva, this.labelConstraints);
        this.labelConstraints.gridy++;

        Panel panel = new Panel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 0));

        this.dateInicioReserva = new JDatePickerImpl(new JDatePanelImpl(null));
        this.labelReservaAte = new JLabel("até");
        this.dateFimReserva = new JDatePickerImpl(new JDatePanelImpl(null));

        panel.add(dateInicioReserva, this.labelConstraints);
        panel.add(this.labelReservaAte);
        panel.add(dateFimReserva, this.labelConstraints);

        this.add(panel, this.fieldConstraints);
        this.fieldConstraints.gridy++;
    }

    private void inicializarBotaoReservar() {
        this.fieldConstraints.fill = GridBagConstraints.NONE;
        this.fieldConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
        this.buttonReservar = new JButton("Reservar");
        this.buttonReservar.addActionListener(this);
        this.add(this.buttonReservar, this.fieldConstraints);
        this.fieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        this.fieldConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        this.fieldConstraints.gridy++;
    }

    private void pesquisar() {

        String endereco = this.textEndereco.getText();
        int tipo = -1;
        if (this.radioSimples.isSelected()) {
            tipo = 0;
        } else if (this.radioDuplo.isSelected()) {
            tipo = 1;
        } else if (this.radioFamilia.isSelected()) {
            tipo = 2;
        }


        int cafe = -1;
        if (this.radioSim.isSelected()) {
            cafe = 1;
        } else if (this.radioNao.isSelected()) {
            cafe = 0;
        }

        float valorDaDiariaInicial;
        try {
            valorDaDiariaInicial = Float.parseFloat(textValorDaDiariaInicial.getText());
        } catch (NumberFormatException e) {
            valorDaDiariaInicial = 0;
        }

        float valorDaDiariaFinal;
        try {
            valorDaDiariaFinal = Float.parseFloat(textValorDaDiariaFinal.getText());
        } catch (NumberFormatException e) {
            valorDaDiariaFinal = 0;
        }

        Date dataInicio = null, dataFim = null;
        GregorianCalendar data = (GregorianCalendar) this.dateInicioDisponibilidade.getJFormattedTextField().getValue();
        if (data != null) {
            dataInicio = new Date(data.get(GregorianCalendar.YEAR) - 1900, data.get(GregorianCalendar.MONTH), data.get(GregorianCalendar.DATE));
        }
        data = (GregorianCalendar) this.dateFimDisponibilidade.getJFormattedTextField().getValue();
        if (data != null) {
            dataFim = new Date(data.get(GregorianCalendar.YEAR) - 1900, data.get(GregorianCalendar.MONTH), data.get(GregorianCalendar.DATE));
        }

        Disponibilidade disponibilidade = null;
        if (dataInicio != null && dataFim != null) {
            disponibilidade = new Disponibilidade(dataInicio, dataFim, 0);
        }

        List<Acomodacao> acomodacoes = this.acomodacaoController.consultar(endereco, tipo, cafe, valorDaDiariaInicial, valorDaDiariaFinal, disponibilidade);

        this.inicializarDadosTableAcomodacoes(acomodacoes);
    }

    private void inicializarDadosTableAcomodacoes(List<Acomodacao> acomodacoes) {

        String[][] dados = new String[acomodacoes.size()][3];

        int i = 0;
        for (Acomodacao a : acomodacoes) {
            dados[i++] = new String[]{a.getDescricao(), a.getEndereco(), String.valueOf(a.getValorDiario())};
        }

        String[] colunas = new String[]{"Descrição", "Endereço", "Valor da Diária"};

        DefaultTableModel model = new CustomTableModel(dados, colunas);

        this.tableColaboradores.setModel(model);

        this.acomodacoes = acomodacoes;
    }

    private boolean validarCampos() {

        if (this.dateInicioReserva.getJFormattedTextField().getValue() == null) {
            JOptionPane.showMessageDialog(this, "Favor informar a data de início da reserva!", "Dados incompletos", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (this.dateFimReserva.getJFormattedTextField().getValue() == null) {
            JOptionPane.showMessageDialog(this, "Favor informar a data do fim da reserva!", "Dados incompletos", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void reservar() {

        int index = this.tableColaboradores.getSelectedRow();

        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma acomodação!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!this.validarCampos()) {
            return;
        }

        Acomodacao a = this.acomodacoes.get(index);

        Date dataInicio = null, dataFim = null;
        GregorianCalendar data = (GregorianCalendar) this.dateInicioReserva.getJFormattedTextField().getValue();
        if (data != null) {
            dataInicio = new Date(data.get(GregorianCalendar.YEAR) - 1900, data.get(GregorianCalendar.MONTH), data.get(GregorianCalendar.DATE));
        }
        data = (GregorianCalendar) this.dateFimReserva.getJFormattedTextField().getValue();
        if (data != null) {
            dataFim = new Date(data.get(GregorianCalendar.YEAR) - 1900, data.get(GregorianCalendar.MONTH), data.get(GregorianCalendar.DATE));
        }

        if (dataInicio.compareTo(dataFim) > 0) {
            JOptionPane.showMessageDialog(this, "A data de início da reserva deve ser menor ou igual que a data do fim da reserva!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Disponibilidade periodoValido = null;
        for (Disponibilidade d : a.getDisponibilidades()) {
            if (d.getDataInicio().compareTo(dataInicio) <= 0 && d.getDataFim().compareTo(dataFim) >= 0) {
                periodoValido = d;
                break;
            }
        }

        if (periodoValido == null) {
            JOptionPane.showMessageDialog(this, "Não existe disponibilidade para acomodação no período de reserva selecionado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Calendar cal = Calendar.getInstance();

        long milisegundos = dataInicio.getTime() - periodoValido.getDataInicio().getTime();
        int dias = (int) Math.round(milisegundos / ((double) 24 * 3600 * 1000));

        if (dias > 0) {
            cal.setTime(dataInicio);
            cal.add(Calendar.DATE, -1);
            Date novaDisponibilidadeFim = cal.getTime();

            a.getDisponibilidades().add(new Disponibilidade(periodoValido.getDataInicio(), novaDisponibilidadeFim, a.getId()));
        }

        milisegundos = periodoValido.getDataFim().getTime() - dataFim.getTime();
        dias = (int) Math.round(milisegundos / ((double) 24 * 3600 * 1000));

        if (dias > 0) {
            cal.setTime(dataFim);
            cal.add(Calendar.DATE, 1);
            Date novaDisponibilidadeInicio = cal.getTime();

            a.getDisponibilidades().add(new Disponibilidade(novaDisponibilidadeInicio, periodoValido.getDataFim(), a.getId()));
        }

        a.getDisponibilidades().remove(periodoValido);

        milisegundos = dataFim.getTime() - dataInicio.getTime();
        dias = (int) Math.round(milisegundos / ((double) 24 * 3600 * 1000));
        dias++;

        Reserva r = new Reserva(0, a.getId(), UsuarioController.getUsuarioLogado().getId(), dataInicio, dataFim, a.getValorDiario() * dias);
        
        this.acomodacaoController.alterar(a);
        this.reservaController.cadastrar(r);

        JOptionPane.showMessageDialog(this, "Acomodação reservada com sucesso!\nValor Total: R$" + r.getValorTotal(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        
        this.dateInicioReserva.getJFormattedTextField().setValue(null);
        this.dateFimReserva.getJFormattedTextField().setValue(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.buttonPesquisar) {
            this.pesquisar();
        } else if (e.getSource() == this.buttonReservar) {
            this.reservar();
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getClickCount() == 2) {
            int index = this.tableColaboradores.getSelectedRow();
            if (index >= 0) {
                Acomodacao a = this.acomodacoes.get(index);
                FormDetalhesAcomodacao da = new FormDetalhesAcomodacao(a);
                this.getParent().add(da);
                da.setVisible(true);

            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
