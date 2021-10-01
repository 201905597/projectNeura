package neuraHealthUI.ui;

import icai.dtc.isw.dao.CustomerDAO;


import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JVentana extends JFrame
{
    public static void main(String[] args)
    {
        new JVentana();
    }

    public JVentana()
    {
        super("Mental Health App");

        this.setLayout(new BorderLayout());

        //ventana de autenticacion
        this.addWindowListener(new WindowAdapter() {

            public void windowOpened(WindowEvent e) {
                UsernameDialog usernameDialog = new UsernameDialog(this,true);

            }
        });


        //NORTE
        JPanel pnlNorte = new JPanel();
        JLabel lblTitulo = new JLabel("NeuraHealth", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Courier", Font.BOLD, 20));
        JLabel lblLogo = new JLabel(new ImageIcon("images/logoo.png"));
        JLabel lblLogo2 = new JLabel(new ImageIcon("images/logoo.png"));
        pnlNorte.add(lblLogo);
        pnlNorte.add(lblTitulo);
        pnlNorte.add(lblLogo2);
        this.add(pnlNorte, BorderLayout.NORTH);

        //CENTRO
        JPanel pnlCentro = new JPanel();
        pnlCentro.setLayout(new BoxLayout(pnlCentro, BoxLayout.	Y_AXIS));

        //paneles para poder añadir label icons
        JPanel pnlCalendarAnimo = new JPanel();
        JLabel lblAnimo = new JLabel(new ImageIcon("images/Imagen1.png"));
        pnlCalendarAnimo.add(lblAnimo);
        JButton btnCalendarAnimo = new JButton("Mi estado de ánimo");
        btnCalendarAnimo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                CalendarDialog calendarDlg = new CalendarDialog(JVentana.this, true, "Mi estado de ánimo");
            }
        });
        pnlCalendarAnimo.add(btnCalendarAnimo);
        pnlCentro.add(pnlCalendarAnimo);

        JPanel pnlCalendarSeg = new JPanel();
        JLabel lblSeg = new JLabel(new ImageIcon("images/Imagen2.png"));
        pnlCalendarSeg.add(lblSeg);
        JButton btnCalendarSeg = new JButton("Mis hábitos");
        pnlCalendarSeg.add(btnCalendarSeg);
        pnlCentro.add(pnlCalendarSeg);

        JPanel pnlInfo = new JPanel();
        JLabel lblInfo = new JLabel(new ImageIcon("images/Imagen3.png"));
        pnlInfo.add(lblInfo);
        JButton btnInfo = new JButton("Información");
        btnInfo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                InfoDialog infoDlg = new InfoDialog(JVentana.this, true);
            }
        });
        pnlInfo.add(btnInfo);
        pnlCentro.add(pnlInfo);

        JPanel pnlActividades = new JPanel();
        JLabel lblActividades = new JLabel(new ImageIcon("images/Imagen4.png"));
        pnlActividades.add(lblActividades);
        JButton btnActividades = new JButton("Actividades");
        pnlActividades.add(btnActividades);
        pnlCentro.add(pnlActividades);

        JPanel pnlJuego = new JPanel();
        JLabel lblJuego = new JLabel(new ImageIcon("images/Imagen5.png"));
        pnlJuego.add(lblJuego);
        JButton btnJuego = new JButton("Juego");
        pnlJuego.add(btnJuego);
        pnlCentro.add(pnlJuego);

        this.add(pnlCentro, BorderLayout.CENTER);

        this.setSize(550,550);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}