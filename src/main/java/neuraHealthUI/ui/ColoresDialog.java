package neuraHealthUI.ui;

import icai.dtc.isw.client.Client;
import neuraHealthUI.dominio.DayPanel;
import main.java.neuraHealthUI.dominio.AsociacionColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ColoresDialog extends JDialog
{
    private JVentana ventanaOwner;
    private DayPanel diaOwner;
    private String fecha;
    private String idConectado;
    private ArrayList<JToggleButton> arrayEmociones;
    private ArrayList<JToggleButton> arrayHabitos;
    private ButtonGroup btnEmoc;
    private ButtonGroup btnHab;
    //color coding
    private ArrayList<AsociacionColor> asociacionColores;


    public ColoresDialog(String fecha, JVentana ventanaOwner, boolean modal, DayPanel diaOwner, String idConectado, String tipoDia)
    {
        this.setModal(modal);
        this.fecha =fecha;
        this.setLayout(new BorderLayout());
        this.diaOwner = diaOwner;
        this.arrayEmociones = new ArrayList<JToggleButton>();
        this.arrayHabitos = new ArrayList<JToggleButton>();
        this.btnEmoc = new ButtonGroup();
        this.btnHab = new ButtonGroup();

        AsociacionColor feliz = new AsociacionColor("Feliz");
        AsociacionColor triste = new AsociacionColor("Triste");
        AsociacionColor estresado = new AsociacionColor("Estresad@");
        AsociacionColor cansado = new AsociacionColor("Cansad@");
        AsociacionColor productivo = new AsociacionColor("Productiv@");
        AsociacionColor hecho = new AsociacionColor("Hecho");
        AsociacionColor nohecho = new AsociacionColor("No hecho");

        this.asociacionColores = new ArrayList<AsociacionColor>();
        asociacionColores.add(feliz);
        asociacionColores.add(triste);
        asociacionColores.add(estresado);
        asociacionColores.add(cansado);
        asociacionColores.add(productivo);
        asociacionColores.add(hecho);
        asociacionColores.add(nohecho);

        this.idConectado=idConectado;
        this.ventanaOwner=ventanaOwner;

        //NORTE
        JLabel lblTitulo = new JLabel();
        if (tipoDia == "Animo")
        {
            lblTitulo.setText("¿Cómo te sientes hoy?");
        }
        else
        {
            lblTitulo.setText("¿Has completado el hábito " + tipoDia + "?");
        }
        lblTitulo.setFont(new Font("Courier", Font.BOLD, 14));
        this.add(lblTitulo, BorderLayout.NORTH);

        //CENTRO
        JPanel pnlCentro = new JPanel();
        pnlCentro.setLayout(new GridLayout(3,2));

        JToggleButton tglFeliz = new JToggleButton("Feliz");
        tglFeliz.setOpaque(true);
        tglFeliz.setBackground(feliz.getColor());
        tglFeliz.setSelected(true);
        btnEmoc.add(tglFeliz);

        JToggleButton tglTriste = new JToggleButton("Triste");
        tglTriste.setOpaque(true);
        tglTriste.setBackground(triste.getColor());
        btnEmoc.add(tglTriste);

        JToggleButton tglEstresado = new JToggleButton("Estresad@");
        tglEstresado.setOpaque(true);
        tglEstresado.setBackground(estresado.getColor());
        btnEmoc.add(tglEstresado);

        JToggleButton tglCansado = new JToggleButton("Cansad@");
        tglCansado.setOpaque(true);
        tglCansado.setBackground(cansado.getColor());
        btnEmoc.add(tglCansado);

        JToggleButton tglProductivo = new JToggleButton("Productiv@");
        tglProductivo.setOpaque(true);
        tglProductivo.setBackground(productivo.getColor());
        btnEmoc.add(tglProductivo);

        JToggleButton tglHecho = new JToggleButton("Hecho");
        tglHecho.setOpaque(true);
        tglHecho.setBackground(hecho.getColor());
        btnHab.add(tglHecho);

        JToggleButton tglNoHecho = new JToggleButton("No hecho");
        tglProductivo.setOpaque(true);
        tglNoHecho.setBackground(nohecho.getColor());
        btnHab.add(tglNoHecho);

        arrayEmociones.add(tglFeliz);
        arrayEmociones.add(tglTriste);
        arrayEmociones.add(tglEstresado);
        arrayEmociones.add(tglCansado);
        arrayEmociones.add(tglProductivo);

        arrayHabitos.add(tglHecho);
        arrayHabitos.add(tglNoHecho);

        if (tipoDia == "Animo")
        {
            pnlCentro.add(tglFeliz);
            pnlCentro.add(tglTriste);
            pnlCentro.add(tglEstresado);
            pnlCentro.add(tglCansado);
            pnlCentro.add(tglProductivo);
        }
        else
        {
            pnlCentro.add(tglHecho);
            pnlCentro.add(tglNoHecho);
        }

        this.add(pnlCentro, BorderLayout.CENTER);

        //SUR
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JToggleButton btnSelected = null;

                if (tipoDia == "Animo")
                {
                    for (JToggleButton tglBtn : arrayEmociones)
                    {
                        if (tglBtn.isSelected())
                            btnSelected = tglBtn;
                    }
                }
                else
                {
                    for (JToggleButton tglBtn : arrayHabitos)
                    {
                        if (tglBtn.isSelected())
                            btnSelected = tglBtn;
                    }
                }

                if (btnSelected != null)
                {
                    Client client = new Client();
                    HashMap<String,Object> session = new HashMap<String,Object>();
                    session.put("id",idConectado);
                    if (tipoDia == "Animo")
                    {
                        ventanaOwner.addFechaEmocion(fecha,btnSelected.getText());
                        session.put("hmFE",ventanaOwner.getHmFechaEmocion());
                        client.metodoClient("/animoUsuario",session);
                    }
                    else
                    {
                        ventanaOwner.addFechaHabito(fecha, tipoDia, btnSelected.getText());
                        session.put("hmFH",ventanaOwner.getHmFechaHabito());
                        client.metodoClient("/habitoUsuario",session);
                    }

                    Color colorAsociacion = btnSelected.getBackground();
                    diaOwner.setBtnColor(colorAsociacion);
                    for (AsociacionColor asociacion : asociacionColores)
                    {
                        if (asociacion.getColor() == colorAsociacion)
                        diaOwner.setAsociacion(asociacion.getAsociacion());
                    }
                }
                ColoresDialog.this.dispose();
            }
        });
        this.add(btnGuardar, BorderLayout.SOUTH);

        this.pack();
        this.setSize(300,200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
