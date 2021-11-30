package main.java.neuraHealthUI.ui;

import icai.dtc.isw.client.Client;
import neuraHealthUI.ui.JVentana;
import neuraHealthUI.dominio.MonthPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.time.Month;
import java.util.HashMap;
import java.util.HashSet;

public class InfoPacienteDialog extends JDialog implements Serializable
{
    private String idPaciente;
    private String nombrePaciente;
    private JVentana ventanaOwner;
    JScrollPane scrpCentro;
    JPanel pnlCentro;

    public InfoPacienteDialog(JVentana ventanaOwner,boolean modal,String nombreId)
    {
        this.setTitle("Seguimiento de paciente");
        this.setModal(modal);
        this.setLayout(new BorderLayout());
        this.idPaciente = nombreId.substring(nombreId.indexOf("-")+1);
        this.nombrePaciente = nombreId.substring(0,nombreId.indexOf("-"));
        this.ventanaOwner = ventanaOwner;

        //NORTE
        JLabel lblPaciente = new JLabel("Seguimiento de " + nombrePaciente);
        this.add(lblPaciente,BorderLayout.NORTH);

        //CENTRO: scroll del seguimiento del paciente
        scrpCentro = new JScrollPane();
        scrpCentro.getViewport().setBackground(Color.WHITE);
        scrpCentro.setOpaque(true);
        scrpCentro.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrpCentro.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pnlCentro = new JPanel();
        pnlCentro.setOpaque(true);
        pnlCentro.setBackground(Color.WHITE);
        pnlCentro.setLayout(new BoxLayout(pnlCentro, BoxLayout.Y_AXIS));
        this.add(scrpCentro, BorderLayout.CENTER);

        /**
         * Cuando se abre este JDialog, se recuperan los datos del paciente en cuestión (sus ánimos y hábitos) de la bbdd
         */

        this.addWindowListener(new WindowAdapter()
        {
            public void windowOpened(WindowEvent e)
            {
                Client client = new Client();
                HashMap<String,Object> session=new HashMap<String, Object>();
                session = new HashMap();
                session.put("id",idPaciente);

                //Recuperación de ánimos
                client.metodoClient("/recuperacionAnimo",session);
                HashSet<neuraHealthUI.dominio.MonthPanel> respuestaHSetAnimos = new HashSet<neuraHealthUI.dominio.MonthPanel>();
                respuestaHSetAnimos = (HashSet<MonthPanel>) session.get("RespuestaRecAnimos");
                for (MonthPanel mes : respuestaHSetAnimos)
                {
                    JTextArea txtSeguimiento = new JTextArea(mes.toString());
                    JScrollPane scrpNuevo = new JScrollPane(txtSeguimiento);
                    pnlCentro.add(scrpNuevo);
                    scrpCentro.getViewport().add(pnlCentro,null);
                }

                //Recuperación de hábitos
                client.metodoClient("/recuperacionNombreHabitos",session);
                HashSet<String> habitosbbdd = (HashSet<String>) session.get("RespuestaRecNombreHabitos");
                for (String habito : habitosbbdd)
                {
                    if (habito != null)
                    {
                        System.out.println(habito);
                        Client client2 = new Client();
                        HashMap<String,Object> session2=new HashMap<String, Object>();
                        session2.put("id",idPaciente);
                        session2.put("habito",habito);
                        session2.put("ventana",ventanaOwner);
                        HashSet<MonthPanel> respuestaHSetHabitos = new HashSet<MonthPanel>();
                        client2.metodoClient("/recuperacionHabito",session2);
                        respuestaHSetHabitos = (HashSet<MonthPanel>) session2.get("RespuestaRecHabitos");
                        for (MonthPanel mes : respuestaHSetHabitos)
                        {
                            JTextArea txtSeguimiento = new JTextArea(mes.toString());
                            JScrollPane scrpNuevo = new JScrollPane(txtSeguimiento);
                            pnlCentro.add(scrpNuevo);
                            scrpCentro.getViewport().add(pnlCentro,null);
                        }
                    }
                }
            }
        });

        this.pack();
        this.setSize(500,300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
