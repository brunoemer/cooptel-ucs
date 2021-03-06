/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FormDetalhesAcomodacao.java
 *
 * Created on 16/10/2012, 13:20:53
 */
package view;

import controller.ColaboradorController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;

import javax.swing.JInternalFrame;

import javax.swing.JOptionPane;
import model.Acomodacao;
import model.Colaborador;
import model.Disponibilidade;

/**
 *
 * @author glauberc
 */
public class FormDetalhesAcomodacao extends JInternalFrame {

    private Acomodacao acomodacao;

    /**
     * Creates new form FormDetalhesAcomodacao
     */
    public FormDetalhesAcomodacao(Acomodacao acomodacao) {
        initComponents();
        this.acomodacao = acomodacao;
        this.populateText();
        this.requestFocus();
    }

    private void populateText() {
        this.setTitle("Detalhes de Acomodação");
        this.setSize(700, 750);
        this.setClosable(true);
        this.setMaximizable(true);
        this.setResizable(true);

        ColaboradorController cc = new ColaboradorController();
        Colaborador colaborador = new Colaborador();
        colaborador.setId(this.acomodacao.getIdColaborador());
        colaborador = cc.detalhar(colaborador);


        String NEW_LINE = System.getProperty("line.separator");


        this.txtDetalhes.append("Nome do Responsável: " + colaborador.getNome() + NEW_LINE);
        this.txtDetalhes.append("Email do Responsável: " + colaborador.getEmail() + NEW_LINE + NEW_LINE);

        this.txtDetalhes.append("Acomodação:" + NEW_LINE + NEW_LINE);
        String simNao = this.acomodacao.isCafe() ? "Sim" : "Não";
        this.txtDetalhes.append("Café da manhã incluso?: " + simNao + NEW_LINE);
        this.txtDetalhes.append("Endereço: " + this.acomodacao.getEndereco() + NEW_LINE);
        this.txtDetalhes.append("Valor da Diária: " + this.acomodacao.getValorDiario() + NEW_LINE);
        this.txtDetalhes.append("Descrição: " + this.acomodacao.getDescricao() + NEW_LINE);

        this.txtDetalhes.append("Disponibilidades: " + NEW_LINE);
        if (this.acomodacao.getDisponibilidades().size() == 0) {
            this.txtDetalhes.append("Nenhuma disponibilidade!");
        } else {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            for (Disponibilidade d : this.acomodacao.getDisponibilidades()) {
                this.txtDetalhes.append(format.format(d.getDataInicio()));
                this.txtDetalhes.append(" até ");
                this.txtDetalhes.append(format.format(d.getDataFim()));
                this.txtDetalhes.append(NEW_LINE);
            }
        }

        this.GoogleMaps.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        this.linkFoto1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        this.linkFoto2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        this.linkFoto3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        this.GoogleMaps.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
                try {
                    URI uri = new URI("http://maps.google.com.br/maps?hl=pt-BR&ll=" + acomodacao.getLatitude() + "," + acomodacao.getLongitude());
                    desktop.browse(uri);
                } catch (Exception exc) {
                }
            }
        });

        this.linkFoto1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    File f = new File(acomodacao.getFotoExterna());
                    java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
                    desktop.open(f);
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, "Não há foto externa");
                }
            }
        });

        this.linkFoto2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    File f = new File(acomodacao.getFoto1());
                    java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
                    desktop.open(f);
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, "Não há foto 1");
                }
            }
        });

        this.linkFoto3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    File f = new File(acomodacao.getFoto2());
                    java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
                    desktop.open(f);
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, "Não há foto 2");
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPanelTxtDetalhes = new javax.swing.JScrollPane();
        txtDetalhes = new javax.swing.JTextArea();
        GoogleMaps = new javax.swing.JLabel();
        lblFotos = new javax.swing.JLabel();
        linkFoto1 = new javax.swing.JLabel();
        linkFoto2 = new javax.swing.JLabel();
        linkFoto3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtDetalhes.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        txtDetalhes.setColumns(20);
        txtDetalhes.setEditable(false);
        txtDetalhes.setFont(new java.awt.Font("Tahoma", 1, 13));
        txtDetalhes.setLineWrap(true);
        txtDetalhes.setRows(5);
        scrollPanelTxtDetalhes.setViewportView(txtDetalhes);

        GoogleMaps.setForeground(java.awt.Color.blue);
        GoogleMaps.setText("Clique aqui para ver a localização");

        lblFotos.setText("Fotos:");

        linkFoto1.setForeground(java.awt.Color.blue);
        linkFoto1.setText("(Foto externa)");

        linkFoto2.setForeground(java.awt.Color.blue);
        linkFoto2.setText("(Foto interna 1)");

        linkFoto3.setForeground(java.awt.Color.blue);
        linkFoto3.setText("(Foto interna 2)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPanelTxtDetalhes, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GoogleMaps)
                .addContainerGap(231, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFotos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linkFoto1)
                .addGap(18, 18, 18)
                .addComponent(linkFoto2)
                .addGap(18, 18, 18)
                .addComponent(linkFoto3)
                .addContainerGap(275, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrollPanelTxtDetalhes, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GoogleMaps)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFotos)
                    .addComponent(linkFoto1)
                    .addComponent(linkFoto2)
                    .addComponent(linkFoto3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel GoogleMaps;
    private javax.swing.JLabel lblFotos;
    private javax.swing.JLabel linkFoto1;
    private javax.swing.JLabel linkFoto2;
    private javax.swing.JLabel linkFoto3;
    private javax.swing.JScrollPane scrollPanelTxtDetalhes;
    private javax.swing.JTextArea txtDetalhes;
    // End of variables declaration//GEN-END:variables
}
