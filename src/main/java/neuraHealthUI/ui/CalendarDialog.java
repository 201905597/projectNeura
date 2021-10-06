package neuraHealthUI.ui;

import icai.dtc.isw.dao.CustomerDAO;
import neuraHealthUI.dominio.MonthPanel;
import neuraHealthUI.dominio.DayPanel;
import icai.dtc.isw.client.Client;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashSet;

public class CalendarDialog extends JDialog
{
    private String strTitulo;
    private JVentana ventanaOwner;
    private HashMap<String,MonthPanel> hmMeses; // Septiembre2021, MonthPanel
    private JComboBox cmbMesesSeg;
    private JComboBox cmbNuevosMeses;
    private JButton btnAnadirMes;
    private JTextField txtAnio;
    private String idConectado;
    //private ArrayList<MonthPanel> mesesArray;
    private HashSet<MonthPanel> mesesHSet;
    JPanel pnlCentro;

    private JButton btnVerMeses;
    String cmbDefault;

    String meses[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

    public CalendarDialog()
    {

    }
    public CalendarDialog(JVentana ventanaOwner, boolean modal, String title, String idConectado)
    {
        this.setModal(modal);
        this.setTitle(title);
        this.strTitulo = title;
        this.ventanaOwner = ventanaOwner;
        this.hmMeses = new HashMap<String,MonthPanel>();
        this.idConectado = idConectado;
        //mesesArray = new ArrayList<MonthPanel>();
        mesesHSet = new HashSet<MonthPanel>();

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
                int anioNuevo=2021;
                try{
                     anioNuevo = Integer.parseInt(txtAnio.getText());
                    String nombreMesNuevo = String.valueOf(cmbNuevosMeses.getSelectedItem());
                    if (nombreMesNuevo != null && nombreMesNuevo != cmbDefault)
                    {
                        MonthPanel mesNuevo = new MonthPanel(nombreMesNuevo, anioNuevo, ventanaOwner);
                        CalendarDialog.this.addMonthPnl(mesNuevo);
                    }
                }
                catch(NumberFormatException nfe )
                {
                    JOptionPane.showMessageDialog(CalendarDialog.this, "El año debe ser un numero");
                }


            }
        });


        this.add(pnlSur, BorderLayout.SOUTH);

        //añadido 2 oct
        this.addWindowListener(new WindowAdapter()
        {


            public void windowClosing(WindowEvent e)
            {
                for (MonthPanel mes : hmMeses.values())
                if (mes != null)
                {
                    for (DayPanel dia : mes.getDayArray())
                    {
                        if (dia.isColoreado())
                            ventanaOwner.addFechaEmocion(dia.getFecha(),dia.getEmocion());
                    }
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e)
            {
                System.out.println("calendar dialog abierto");
                ArrayList<String> mesesAdded = new ArrayList<String>();
                Client client = new Client();
                HashMap<String,Object> session=new HashMap<String, Object>();
                session.put("id",idConectado);
                session.put("ventana",ventanaOwner);
                client.metodoClient("/recuperacionAnimo",session);
                HashSet<MonthPanel> respuestaHSet = new HashSet<MonthPanel>();
                respuestaHSet = (HashSet<MonthPanel>) session.get("RespuestaRecAnimos");

                for (MonthPanel mes : respuestaHSet)
                {
                    if (!mesesAdded.contains(mes.getMesYAnio()))
                    {
                        mesesAdded.add(mes.getMesYAnio());
                        CalendarDialog.this.addMonthPnl(mes);
                    }

                }
            }
        });

        this.pack();
        this.setSize(700,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void addMonthPnl(MonthPanel mesNuevo)
    {
        hmMeses.put(mesNuevo.getMesYAnio(), mesNuevo);
        cmbMesesSeg.addItem(mesNuevo.getMesYAnio());
        cmbMesesSeg.removeItem(cmbDefault);
        pnlCentro.add(mesNuevo);
        pnlCentro.updateUI();
    }
}