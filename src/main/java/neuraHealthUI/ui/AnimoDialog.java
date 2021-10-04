package neuraHealthUI.ui;

import neuraHealthUI.dominio.DayPanel;
import icai.dtc.isw.dao.CustomerDAO;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnimoDialog extends JDialog
{
    private DayPanel diaOwner;
    private ButtonGroup btnGroup;
    private ArrayList<JToggleButton> arrayBtns;
    //color coding
    private HashMap<String,Color> colorEmocion;
    String idConectado;

    private JVentana ventanaOwner;
    private String fecha;


    public AnimoDialog(String fecha, JVentana ventanaOwner, boolean modal, DayPanel diaOwner, String idConectado)
    {
        this.setModal(modal);
        this.fecha=fecha;
        this.setLayout(new BorderLayout());
        this.diaOwner = diaOwner;
        this.arrayBtns = new ArrayList<JToggleButton>();
        this.colorEmocion = new HashMap<String, Color>();
        colorEmocion.put("Feliz",new Color(255,153,0));
        colorEmocion.put("Triste",new Color(51,153,255));
        colorEmocion.put("Estresad@",new Color(255,51,51));
        colorEmocion.put("Cansad@",new Color(102,20,153));
        colorEmocion.put("Productiv@",new Color(0,204,0));
        this.idConectado=idConectado;

        this.ventanaOwner=ventanaOwner;

        //NORTE
        JLabel lblTitulo = new JLabel("¿Cómo te sientes hoy?");
        lblTitulo.setFont(new Font("Courier", Font.BOLD, 14));
        this.add(lblTitulo, BorderLayout.NORTH);

        //CENTRO
        JPanel pnlCentro = new JPanel();
        pnlCentro.setLayout(new GridLayout(3,2));
        btnGroup = new ButtonGroup();

        JToggleButton tglFeliz = new JToggleButton("Feliz");
        tglFeliz.setOpaque(true);
        tglFeliz.setBackground(colorEmocion.get(tglFeliz.getText()));
        tglFeliz.setSelected(true);

        JToggleButton tglTriste = new JToggleButton("Triste");
        tglTriste.setOpaque(true);
        tglTriste.setBackground(colorEmocion.get(tglTriste.getText()));

        JToggleButton tglEstresado = new JToggleButton("Estresad@");
        tglEstresado.setOpaque(true);
        tglEstresado.setBackground(colorEmocion.get(tglEstresado.getText()));

        JToggleButton tglCansado = new JToggleButton("Cansad@");
        tglCansado.setOpaque(true);
        tglCansado.setBackground(colorEmocion.get(tglCansado.getText()));

        JToggleButton tglProductivo = new JToggleButton("Productiv@");
        tglProductivo.setOpaque(true);
        tglProductivo.setBackground(colorEmocion.get(tglProductivo.getText()));

        btnGroup.add(tglFeliz);
        btnGroup.add(tglTriste);
        btnGroup.add(tglEstresado);
        btnGroup.add(tglCansado);
        btnGroup.add(tglProductivo);

        arrayBtns.add(tglFeliz);
        arrayBtns.add(tglTriste);
        arrayBtns.add(tglEstresado);
        arrayBtns.add(tglCansado);
        arrayBtns.add(tglProductivo);

        pnlCentro.add(tglFeliz);
        pnlCentro.add(tglTriste);
        pnlCentro.add(tglEstresado);
        pnlCentro.add(tglCansado);
        pnlCentro.add(tglProductivo);

        this.add(pnlCentro, BorderLayout.CENTER);

        //SUR (falta btn cancelar)
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JToggleButton btnSelected = null;
                for (JToggleButton tglBtn : arrayBtns)
                {
                    if (tglBtn.isSelected())
                        btnSelected = tglBtn;
                }
                if (btnSelected != null)
                {

                    ventanaOwner.addFechaEmocion(fecha,btnSelected.getText());
                    CustomerDAO customerDao = new CustomerDAO();
                    customerDao.rellenarAnimo(idConectado,ventanaOwner.getHmFechaEmocion());
                    Color colorAnimo = btnSelected.getBackground();
                    diaOwner.setBtnColor(colorAnimo);
                    for (Map.Entry<String,Color> entry : colorEmocion.entrySet()) {
                        if (colorAnimo == entry.getValue())
                            diaOwner.setEmocion(entry.getKey());
                    }
                }
                AnimoDialog.this.dispose();


            }
        });
        this.add(btnGuardar, BorderLayout.SOUTH);

        this.pack();
        this.setSize(300,200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public Color getColorEmocion(String emocion)
    {
        return colorEmocion.get(emocion);
    }
}