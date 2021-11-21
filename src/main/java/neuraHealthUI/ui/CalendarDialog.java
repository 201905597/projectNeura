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

/**
 * Calendario para la funcionalidad de calendario de estados de ánimo y calendario de hábitos.
 * Es un JDialog que permite al usuario ver el seguimiento de sus hábitos o emociones y rellenarlo cada día.
 */
public class CalendarDialog extends JDialog
{
    private String strTitulo;
    private String tipoCalendar;
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

    /**
     * Constructor del CalendarDialog
     * @param ventanaOwner la JVentana principal, JFrame padre de este JDialog
     * @param modal true si lo es, false si no
     * @param title título del CalendarDialog
     * @param idConectado id del usuario/psicólogo que ha iniciado sesión
     * @param tipoCalendar indica si el calendario es de ánimos o de hábitos (puede ser cualquier hábito)
     */
    public CalendarDialog(JVentana ventanaOwner, boolean modal, String title, String idConectado, String tipoCalendar)
    {
        this.setModal(modal);
        this.setTitle(title);
        this.strTitulo = title;
        this.ventanaOwner = ventanaOwner;
        this.hmMeses = new HashMap<String,MonthPanel>();
        this.idConectado = idConectado;
        this.setTipoCalendar(tipoCalendar);
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
                if (mesAnioSel != null && mesAnioSel != "No hay seguimiento aún")
                {
                    for(Map.Entry<String,MonthPanel> entry : hmMeses.entrySet())
                    {
                        String mes = entry.getKey();
                        if (mes != null)
                        {
                            if (mes.equals(mesAnioSel))
                            {
                                System.out.println("HOLA");
                                MonthPanel mesPnl = entry.getValue();

                                for (DayPanel day : mesPnl.getDayArray())
                                {
                                    day.getBtnDia().addActionListener(new ActionListener()
                                    {
                                        @Override
                                        public void actionPerformed(ActionEvent e)
                                        {
                                            System.out.println("ENTRO EN EL ACTION PERFORMED aqui");
                                            neuraHealthUI.ui.ColoresDialog coloresDlg = new neuraHealthUI.ui.ColoresDialog(day.getFecha(),ventanaOwner,true, day,ventanaOwner.getIdConectado(), tipoCalendar);
                                        }
                                    });
                                }
                                mesPnl.setVisible(true);
                                mesPnl.revalidate();
                                mesPnl.updateUI();
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
                        MonthPanel mesNuevo = new MonthPanel(nombreMesNuevo, anioNuevo, ventanaOwner, tipoCalendar);
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
                            {
                                if (tipoCalendar == "Animo")
                                    ventanaOwner.addFechaEmocion(dia.getFecha(),dia.getAsociacion());
                                else
                                {
                                    ventanaOwner.addFechaHabito(dia.getFecha(),tipoCalendar,dia.getAsociacion());
                                }

                            }

                        }
                    }
            }
        });

        /**
         * Cuando se abre el CalendarDialog se recuperan los datos guardados de días previos de la base de datos
         */
        this.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e)
            {
                System.out.println("calendar dialog abierto");
                ArrayList<String> mesesAdded = new ArrayList<String>();
                Client client = new Client();
                HashMap<String,Object> session=new HashMap<String, Object>();
                session.put("id",idConectado);
                session.put("habito",tipoCalendar);
                session.put("ventana",ventanaOwner);

                HashSet<MonthPanel> respuestaHSet = new HashSet<MonthPanel>();
                if (tipoCalendar == "Animo")
                {
                    client.metodoClient("/recuperacionAnimo",session);
                    respuestaHSet = (HashSet<MonthPanel>) session.get("RespuestaRecAnimos");
                }
                else
                {
                    client.metodoClient("/recuperacionHabito",session);
                    respuestaHSet = (HashSet<MonthPanel>) session.get("RespuestaRecHabitos");
                }

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

    /**
     * Método para añadir meses nuevos al seguimiento
     * @param mesNuevo
     */
    public void addMonthPnl(MonthPanel mesNuevo)
    {
        hmMeses.put(mesNuevo.getMesYAnio(), mesNuevo);
        cmbMesesSeg.addItem(mesNuevo.getMesYAnio());
        if (cmbMesesSeg.getSelectedItem().equals(cmbDefault))
            cmbMesesSeg.removeItem(cmbDefault);
        pnlCentro.add(mesNuevo);
        pnlCentro.updateUI();
    }

    /**
     * Setter del tipo de calendario (ánimo o hábitos)
     * @param tipoCalendar
     */
    public void setTipoCalendar(String tipoCalendar)
    {
        this.tipoCalendar = tipoCalendar;
    }
}