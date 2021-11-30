package neuraHealthUI.ui;

import icai.dtc.isw.client.Client;
import main.java.neuraHealthUI.dominio.Psicologo;
import main.java.neuraHealthUI.dominio.Usuario;
import main.java.neuraHealthUI.ui.ImagePanel;
import main.java.neuraHealthUI.ui.InfoPacienteDialog;

import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class JVentana extends JFrame
{
    public static void main(String[] args)
    {
        new JVentana();
    }

    private String idConectado;
    private HashMap<String,String> fechaYemocion;
    private HashMap<String,String> fechaYhabito;
    private String tipo;
    private Psicologo psicologo;
    JPanel pnlCentro;

    public JVentana()
    {
        super("Mental Health App");
        fechaYemocion = new HashMap<String,String>();
        fechaYhabito = new HashMap<String,String>();
        this.setLayout(new BorderLayout());

        //ventana de autenticacion
        this.addWindowListener(new WindowAdapter() {

            public void windowOpened(WindowEvent e) {
                UsernameDialog usernameDialog = new UsernameDialog(JVentana.this,true);

            }
        });

        //NORTE
        JPanel pnlNorte = new JPanel();
        pnlNorte.setBackground(Color.WHITE);
        pnlNorte.setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel lblTitulo = new JLabel("NeuraHealth", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Courier", Font.BOLD, 20));
        //JLabel lblLogo = new JLabel(new ImageIcon("images/logoo.png"));
        JLabel lblLogo = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("logoo.png")));
        //JLabel lblLogo2 = new JLabel(new ImageIcon("images/logoo.png"));
        JLabel lblLogo2 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("logoo.png")));
        pnlNorte.add(lblLogo);
        pnlNorte.add(lblTitulo);
        pnlNorte.add(lblLogo2);
        this.add(pnlNorte, BorderLayout.NORTH);
        //-------------
        //CENTRO
        //pnlCentro = new JPanel();
        //pnlCentro.setBackground(new Color(245,235,242));
        //pnlCentro = new ImagePanel("images/nubes.jpg");
        //this.add(pnlCentro, BorderLayout.CENTER);
        //-------------

        //this.setSize(550,700);
        this.setSize(800,700);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void setIdConectado(String idConectado)
    {
        this.idConectado = idConectado;
    }
    public String getIdConectado()
    {
        return idConectado;
    }

    public void addFechaEmocion(String fecha, String emocion)
    {
        fechaYemocion.put(fecha,emocion);
    }

    public void addFechaHabito(String fecha, String habito, String estadoHabito) //habito es el estado del mismo
    {
        fechaYhabito.put(fecha,habito+"#"+estadoHabito); //lo separo con un simbolo para poder partir luego el string
    }

    public HashMap<String,String> getHmFechaEmocion()
    {
        return fechaYemocion;
    }

    public HashMap<String,String> getHmFechaHabito()
    {
        return fechaYhabito;
    }

    public void setTipoUsuarioEntrante(String tipo)
    {
        this.tipo=tipo;
    }

    public String getTipoUsuarioEntrante(){return tipo;}

    public void gestionarEventos()
    {
        System.out.println(idConectado);
        switch (JVentana.this.getTipoUsuarioEntrante())
        {
            case "psicologo":
                System.out.println("VENTANA PSICOLOGO");
                Client client = new Client();
                HashMap<String,Object> session1=new HashMap<String, Object>();
                session1.put("id",idConectado);
                HashSet<Usuario> pacientes = new HashSet<Usuario>();
                client.metodoClient("/recuperacionPacientes",session1);
                HashMap<String,String> pacientesMap = (HashMap<String,String>) session1.get("RespuestaRecPacientes");
                for (Map.Entry<String, String> entry : pacientesMap.entrySet())
                {
                    Usuario usuario = new Usuario(entry.getKey(),entry.getValue());
                    pacientes.add(usuario);
                }
                psicologo.setPacientes(pacientes);

                ArrayList<JButton> btnPacientes = new ArrayList<JButton>();
                //PANEL CENTRO
                pnlCentro = new ImagePanel("images/cactus.jpg");
                JVentana.this.add(pnlCentro);
                pnlCentro.setLayout(new GridLayout(5,1));
                for (Usuario paciente : pacientes)
                {
                    System.out.println("nombre: "+paciente.getNombre());
                    JPanel pnlPac = new JPanel();
                    pnlPac.setOpaque(false);
                    JButton btnPac = new JButton(paciente.getNombre() + "-" + paciente.getId());
                    pnlPac.add(new JLabel("Ver seguimiento de: "));
                    pnlPac.add(btnPac);
                    btnPacientes.add(btnPac);
                    pnlCentro.add(pnlPac);
                }
                for (JButton btn : btnPacientes)
                {
                    btn.addActionListener(new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            InfoPacienteDialog infoPacDlg = new InfoPacienteDialog(JVentana.this,true, btn.getText());
                        }
                    });
                }
                pnlCentro.updateUI();

                //PANEL SUR (solo para psicólogo)
                JPanel pnlSur = new JPanel();
                pnlSur.setBackground(Color.WHITE);
                JButton btnGenerarInforme = new JButton("Generar informe de seguimiento");
                btnGenerarInforme.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        //abro dialog para elegir el paciente o los pacientes y a dónde enviar el informe
                        System.out.println("Se ha generado el informe correctamente");
                    }
                });
                pnlSur.add(btnGenerarInforme);
                JVentana.this.add(pnlSur,BorderLayout.SOUTH);

                break;

            case "usuario":
                System.out.println("VENTANA USUARIO");
                //CENTRO
                pnlCentro = new ImagePanel("images/ciudad.png");
                JVentana.this.add(pnlCentro);
                pnlCentro.setLayout(new BoxLayout(pnlCentro, BoxLayout.	Y_AXIS));

                //paneles para poder añadir label icons
                JPanel pnlCalendarAnimo = new JPanel();
                pnlCalendarAnimo.setOpaque(false);
                JLabel lblAnimo = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagen1.png")));
                pnlCalendarAnimo.add(lblAnimo);
                JButton btnCalendarAnimo = new JButton("Mi estado de ánimo");
                btnCalendarAnimo.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        CalendarDialog calendarDlg = new CalendarDialog(JVentana.this, true, "Mi estado de ánimo", idConectado, "Animo");
                    }
                });
                pnlCalendarAnimo.add(btnCalendarAnimo);
                pnlCentro.add(pnlCalendarAnimo);

                JPanel pnlCalendarSeg = new JPanel();
                pnlCalendarSeg.setOpaque(false);
                JLabel lblSeg = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagen2.png")));
                pnlCalendarSeg.add(lblSeg);
                JButton btnCalendarSeg = new JButton("Mis hábitos");
                btnCalendarSeg.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        HabitosDialog habitosDlg = new HabitosDialog(JVentana.this, true, idConectado);
                    }
                });
                pnlCalendarSeg.add(btnCalendarSeg);
                pnlCentro.add(pnlCalendarSeg);

                JPanel pnlInfo = new JPanel();
                pnlInfo.setOpaque(false);
                JLabel lblInfo = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagen3.png")));
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
                pnlActividades.setOpaque(false);
                JLabel lblActividades = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagen4.png")));
                pnlActividades.add(lblActividades);
                JButton btnActividades = new JButton("Actividades");
                pnlActividades.add(btnActividades);
                pnlCentro.add(pnlActividades);

                JPanel pnlJuego = new JPanel();
                pnlJuego.setOpaque(false);
                JLabel lblJuego = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagen5.png")));
                pnlJuego.add(lblJuego);
                JButton btnJuego = new JButton("Juego");
                pnlJuego.add(btnJuego);
                pnlCentro.add(pnlJuego);
                btnJuego.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        JuegoDialog juegoDialog = new JuegoDialog(true);
                    }
                });


                JPanel pnlNotificaciones = new JPanel();
                pnlNotificaciones.setOpaque(false);
                JLabel lblNotificaciones = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Imagen6.png")));
                pnlNotificaciones.add(lblNotificaciones);
                JButton btnNotif = new JButton("Notificaciones");
                pnlNotificaciones.add(btnNotif);
                pnlCentro.add(pnlNotificaciones);
                btnNotif.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        NotificDialog infoDlg = new NotificDialog(JVentana.this, true,idConectado);
                    }
                });

                break;
            default:
                System.out.println("error");
                break;
        }
    }

    public void setPsicologo(String id, String nombre, String centro)
    {
        this.psicologo = new Psicologo(id,nombre,centro);
    }
}