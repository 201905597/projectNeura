package neuraHealthUI.ui;

import neuraHealthUI.dominio.MonthPanel;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class CalendarDialog extends JDialog
{
    private String strTitulo;
    private JVentana ventanaOwner;
    private HashMap<String,MonthPanel> hmMeses; // Septiembre2021, MonthPanel
    private JComboBox cmbMesesSeg;
    private JComboBox cmbNuevosMeses;
    private JButton btnAnadirMes;
    private JTextField txtAnio;
    JPanel pnlCentro;

    private JButton btnVerMeses;
    String cmbDefault;

    String meses[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

    public CalendarDialog(JVentana ventanaOwner, boolean modal, String title)
    {
        this.setModal(modal);
        this.setTitle(title);
        this.strTitulo = title;
        this.ventanaOwner = ventanaOwner;
        this.hmMeses = new HashMap<String,MonthPanel>();

        //PANEL NORTE
        JPanel pnlNorte = new JPanel();
        JLabel lblTitulo = new JLabel(strTitulo.toUpperCase());
        pnlNorte.add(lblTitulo);
        cmbMesesSeg = new JComboBox();
        cmbDefault = "No hay seguimiento aún";
        cmbMesesSeg.addItem(cmbDefault);
        pnlNorte.add(cmbMesesSeg);
        btnVerMeses = new JButton("Ver mes");
        pnlNorte.add(btnVerMeses);
        btnVerMeses.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String mesAnioSel = String.valueOf(cmbMesesSeg.getSelectedItem());
                if (mesAnioSel != null)
                {
                    for(Map.Entry<String,MonthPanel> entry : hmMeses.entrySet())
                    {
                        if (entry.getKey() != null)
                        {
                            if (entry.getKey().equals(mesAnioSel))
                            {
                                entry.getValue().setVisible(true);
                                pnlCentro.updateUI();
                            }
                            else
                                entry.getValue().setVisible(false);
                        }
                    }
                }
            }
        });
        this.add(pnlNorte, BorderLayout.NORTH);

        //PANEL CENTRO
        pnlCentro = new JPanel();
        this.add(pnlCentro, BorderLayout.CENTER);

        //PANEL SUR
        JPanel pnlSur = new JPanel();
        JLabel lblAnadir = new JLabel("Nuevo seguimiento de ");
        cmbNuevosMeses = new JComboBox(meses);
        JLabel lblAnio = new JLabel(" del año ");
        txtAnio = new JTextField("2021"); //faltan exceptions
        btnAnadirMes = new JButton("Añadir");


        pnlSur.add(lblAnadir);
        pnlSur.add(cmbNuevosMeses);
        pnlSur.add(lblAnio);
        pnlSur.add(txtAnio);
        pnlSur.add(btnAnadirMes);
        btnAnadirMes.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int anioNuevo = Integer.parseInt(txtAnio.getText());
                String nombreMesNuevo = String.valueOf(cmbNuevosMeses.getSelectedItem());
                if (nombreMesNuevo != null && nombreMesNuevo != cmbDefault)
                {
                    MonthPanel mesNuevo = new MonthPanel(nombreMesNuevo, anioNuevo, ventanaOwner);
                    hmMeses.put(mesNuevo.getMesYAnio(), mesNuevo);
                    cmbMesesSeg.addItem(mesNuevo.getMesYAnio());
                    cmbMesesSeg.removeItem(cmbDefault);
                    pnlCentro.add(mesNuevo);
                    pnlCentro.updateUI();
                }
            }
        });



        this.add(pnlSur, BorderLayout.SOUTH);

        this.pack();
        this.setSize(700,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}