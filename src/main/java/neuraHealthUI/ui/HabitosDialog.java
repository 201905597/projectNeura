package neuraHealthUI.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HabitosDialog extends JDialog
{
    private JVentana ventanaOwner;
    private String idConectado;
    ArrayList<JButton> botonesHabitos;
    JPanel pnlCentro;

    public HabitosDialog(JVentana ventanaOwner, boolean modal, String idConectado)
    {
        this.setModal(modal);
        this.setTitle("MIS HÁBITOS");
        this.idConectado = idConectado;
        this.ventanaOwner = ventanaOwner;
        this.botonesHabitos = new ArrayList<JButton>();

        this.setLayout(new BorderLayout());

        //NORTE
        JLabel lblMisHabitos = new JLabel("MIS HÁBITOS");
        this.add(lblMisHabitos, BorderLayout.NORTH);

        //PANEL CENTRO: muestra los hábitos de los que el usuario hace seguimiento
        pnlCentro = new JPanel();
        pnlCentro.setLayout(new GridLayout(5,2));
        JButton btnDeporte = new JButton("Deporte");
        JButton btnSueno = new JButton("Sueño");
        botonesHabitos.add(btnDeporte);
        botonesHabitos.add(btnSueno);
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
                HabitosDialog.this.addNuevoHabito(nombreHabito);
            }
        });
        pnlSur.add(lblAddHabitos);
        pnlSur.add(btnAddHabitos);
        this.add(pnlSur,BorderLayout.SOUTH);

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

        btnNuevo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                CalendarDialog calendarDlg = new CalendarDialog(ventanaOwner, true,btnNuevo.getText(), idConectado, btnNuevo.getText());
            }
        });
    }
}
