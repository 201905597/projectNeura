package main.java.neuraHealthUI.ui;

import icai.dtc.isw.client.Client;
import neuraHealthUI.ui.JVentana;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.HashSet;

public class InfoPacienteDialog extends JDialog
{
    private String idPaciente;
    private String nombrePaciente;

    public InfoPacienteDialog(JVentana ventanaOwner,boolean modal,String nombreId)
    {
        this.setTitle("Seguimiento de paciente");
        this.setModal(modal);
        this.setLayout(new BorderLayout());
        this.idPaciente = nombreId.substring(nombreId.indexOf("#")+1);
        this.nombrePaciente = nombreId.substring(0,nombreId.indexOf("#"));

        //NORTE
        JLabel lblPaciente = new JLabel("Seguimiento de " + nombrePaciente);
        this.add(lblPaciente,BorderLayout.NORTH);

        /**
         * Cuando se abre este JDialog, se recuperan los datos del paciente en cuestión (sus ánimos y hábitos) de la bbdd
         */
        this.addWindowListener(new WindowAdapter()
        {
            public void windowOpened(WindowEvent e)
            {
                Client client = new Client();
                HashMap<String,Object> session=new HashMap<String, Object>();
                session.put("id",idPaciente);

                //Recuperación de ánimos
                client.metodoClient("/recuperacionAnimo",session);
                HashSet<neuraHealthUI.dominio.MonthPanel> respuestaHSetAnimos = new HashSet<neuraHealthUI.dominio.MonthPanel>();
                respuestaHSetAnimos = (HashSet<neuraHealthUI.dominio.MonthPanel>) session.get("RespuestaRecAnimos");

                //Recuperación de hábitos
                HashSet<neuraHealthUI.dominio.MonthPanel> respuestaHSetHabitos = new HashSet<neuraHealthUI.dominio.MonthPanel>();
                client.metodoClient("/recuperacionHabito",session);
                respuestaHSetHabitos = (HashSet<neuraHealthUI.dominio.MonthPanel>) session.get("RespuestaRecHabitos");


            }
        });

        this.pack();
        this.setSize(250,200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
