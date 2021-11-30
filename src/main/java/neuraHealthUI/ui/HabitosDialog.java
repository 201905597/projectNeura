package neuraHealthUI.ui;

import icai.dtc.isw.client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class HabitosDialog extends JDialog
{
    private JVentana ventanaOwner;
    private String idConectado;
    ArrayList<JButton> botonesHabitos;
    ArrayList<String> habitos;
    JPanel pnlCentro;

    public HabitosDialog(JVentana ventanaOwner, boolean modal, String idConectado)
    {
        this.setModal(modal);

        this.setTitle("MIS HÁBITOS");


        this.idConectado = idConectado;
        this.ventanaOwner = ventanaOwner;
        this.botonesHabitos = new ArrayList<JButton>();
        this.habitos = new ArrayList<String>();

        this.setLayout(new BorderLayout());

        //NORTE
        JLabel lblMisHabitos = new JLabel("MIS HÁBITOS",SwingConstants.CENTER);
        lblMisHabitos.setPreferredSize(new Dimension(50, 70));
        lblMisHabitos.setForeground(Color.GREEN.darker());
        this.add(lblMisHabitos, BorderLayout.NORTH);

        //PANEL CENTRO: muestra los hábitos de los que el usuario hace seguimiento
        pnlCentro = new JPanel();
        pnlCentro.setLayout(new GridLayout(5,2));
        JButton btnDeporte = new JButton("Deporte");
        JButton btnSueno = new JButton("Sueño");
        botonesHabitos.add(btnDeporte);
        botonesHabitos.add(btnSueno);
        habitos.add(btnDeporte.getText());
        habitos.add(btnSueno.getText());
        for (JButton boton : botonesHabitos)
        {
            pnlCentro.add(boton);
        }

        this.add(pnlCentro,BorderLayout.CENTER);
        for (JButton btn : botonesHabitos)
        {
            btn.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    CalendarDialog calendarDlg = new CalendarDialog(ventanaOwner, true,btn.getText(), idConectado, btn.getText());
                }
            });
        }


        //PANEL SUR: permite al usuario añadir nuevos hábitos para su seguimiento
        JPanel pnlSur = new JPanel();
        JLabel lblAddHabitos = new JLabel("Añade tus propios hábitos: ");
        JButton btnAddHabitos = new JButton("Añadir");
        btnAddHabitos.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String nombreHabito = JOptionPane.showInputDialog(null,"Escribe el nombre de tu nuevo hábito: ");
                if ((nombreHabito != null) && (nombreHabito.length()>0) && (!habitos.contains(nombreHabito)))
                {
                    HabitosDialog.this.addNuevoHabito(nombreHabito);
                    habitos.add(nombreHabito);
                    HabitosDialog.this.gestionarEventos();
                }
                else if (nombreHabito == null)
                {
                    System.out.println("nada");
                }
                else
                {
                    JOptionPane.showMessageDialog(HabitosDialog.this, "Por favor, elige un nombre válido para el hábito");
                }
            }
        });
        pnlSur.add(lblAddHabitos);
        pnlSur.add(btnAddHabitos);
        this.add(pnlSur,BorderLayout.SOUTH);

        //window opened falta (n botones con habitos)
        this.addWindowListener(new WindowAdapter()
        {
            public void windowOpened(WindowEvent e)
            {
                HashSet<String> habitosbbdd = new HashSet<String>();
                Client client = new Client();
                HashMap<String,Object> session=new HashMap<String, Object>();
                session.put("id",idConectado);
                client.metodoClient("/recuperacionNombreHabitos",session);
                habitosbbdd = (HashSet<String>) session.get("RespuestaRecNombreHabitos");
                if (!habitosbbdd.isEmpty())
                {
                    for (String habito : habitosbbdd)
                    {
                        if (habito != null)
                            HabitosDialog.this.addNuevoHabito(habito);
                    }
                }
            }
        });

        //this.gestionarEventos();

        this.pack();
        this.setSize(600,450);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void addNuevoHabito(String nombreHabito)
    {
        JButton btnNuevo = new JButton(nombreHabito);
        botonesHabitos.add(btnNuevo);
        pnlCentro.add(btnNuevo);
        pnlCentro.updateUI();
        HabitosDialog.this.gestionarEventos();
    }

    public void gestionarEventos()
    {
        for (JButton btn : botonesHabitos)
        {
            if (btn.getText() != "Deporte" && btn.getText() != "Sueño")
            {
                btn.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        CalendarDialog calendarDlg = new CalendarDialog(ventanaOwner, true,btn.getText(), idConectado, btn.getText());
                    }
                });
            }

        }
    }
}
