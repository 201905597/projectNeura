package neuraHealthUI.ui;

import neuraHealthUI.dominio.DayPanel;

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

public class AnimoDialog extends JDialog
{
    private DayPanel diaOwner;
    private ButtonGroup btnGroup;
    private ArrayList<JToggleButton> arrayBtns;

    public AnimoDialog(JVentana ventanaOwner, boolean modal, DayPanel diaOwner)
    {
        this.setModal(modal);
        this.setLayout(new BorderLayout());
        this.diaOwner = diaOwner;
        arrayBtns = new ArrayList<JToggleButton>();

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
        tglFeliz.setBackground(new Color(255,153,0));
        tglFeliz.setSelected(true);

        JToggleButton tglTriste = new JToggleButton("Triste");
        tglTriste.setOpaque(true);
        tglTriste.setBackground(new Color(51,153,255));

        JToggleButton tglEstresado = new JToggleButton("Estresad@");
        tglEstresado.setOpaque(true);
        tglEstresado.setBackground(new Color(255,51,51));

        JToggleButton tglCansado = new JToggleButton("Cansad@");
        tglCansado.setOpaque(true);
        tglCansado.setBackground(new Color(102,20,153));

        JToggleButton tglProductivo = new JToggleButton("Productiv@");
        tglProductivo.setOpaque(true);
        tglProductivo.setBackground(new Color(0,204,0));

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
                    Color colorAnimo = btnSelected.getBackground();
                    diaOwner.setBtnColor(colorAnimo);
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
}