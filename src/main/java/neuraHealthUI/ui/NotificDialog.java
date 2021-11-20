package neuraHealthUI.ui;


import icai.dtc.isw.client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class NotificDialog extends JDialog {

    JVentana ventanaOwner;
    String id;
    ArrayList<JLabel> jlabels;

    public NotificDialog(JVentana ventanaOwner, boolean modal, String id) {
        this.setModal(modal);
        this.setTitle("NOTIFICACIONES");
        this.ventanaOwner = ventanaOwner;
        this.setId(id);
        this.jlabels = new ArrayList<JLabel>();

        //NORTE
        JLabel lblMisNotif = new JLabel("MIS NOTIFICACIONES",SwingConstants.CENTER);
        lblMisNotif.setPreferredSize(new Dimension(40, 30));
        lblMisNotif.setForeground(Color.RED.darker());
        this.add(lblMisNotif, BorderLayout.NORTH);

        JPanel pnlCentro = new JPanel();
        pnlCentro.setLayout(new GridLayout(7,1));


        Client client = new Client();
        HashMap<String,Object> session=new HashMap<String, Object>();
        session.put("id",id);
        session.put("ventana",ventanaOwner);

        HashSet<neuraHealthUI.dominio.MonthPanel> respuestaHSet1 = new HashSet<neuraHealthUI.dominio.MonthPanel>();
        HashSet<neuraHealthUI.dominio.MonthPanel> respuestaHSet2 = new HashSet<neuraHealthUI.dominio.MonthPanel>();

        client.metodoClient("/recuperacionAnimo",session);
        respuestaHSet1 = (HashSet<neuraHealthUI.dominio.MonthPanel>) session.get("RespuestaRecAnimos");

        client.metodoClient("/recuperacionHabito",session);
        respuestaHSet2 = (HashSet<neuraHealthUI.dominio.MonthPanel>) session.get("RespuestaRecHabitos");

        //ANIMOS
        for (neuraHealthUI.dominio.MonthPanel mes : respuestaHSet1)
        {
            if (mes.getDias("Feliz")>=10)
            {
                JLabel jlbl1 = new JLabel("¡ENHORABUENA!. Has conseguido estar feliz en 10 días",SwingConstants.CENTER);
                jlabels.add(jlbl1);

            }

        }


        //HABITOS
        for (neuraHealthUI.dominio.MonthPanel mes : respuestaHSet2)
        {
            if (mes.getDias("Hecho")==3)
            {
                JLabel jlbl2 = new JLabel("¡ENHORABUENA!. Has conseguido hacer deporte más de 10 días",SwingConstants.CENTER);
                jlabels.add(jlbl2);

            }
            if (mes.getDias("Hecho")==1)
            {
                JLabel jlbl3 = new JLabel("¡ENHORABUENA!. Has conseguido dormir 8 horas más de 10 días",SwingConstants.CENTER);
                jlabels.add(jlbl3);

            }

        }


        for (JLabel jlbl : jlabels)
        {
            jlbl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            pnlCentro.add(jlbl);

        }
        NotificDialog.this.add(pnlCentro,BorderLayout.CENTER);






        this.pack();
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
    public void setId(String id)
    {
        this.id=id;
    }

}

