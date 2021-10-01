package neuraHealthUI.dominio;

import neuraHealthUI.ui.AnimoDialog;
import neuraHealthUI.ui.JVentana;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Color;

public class DayPanel extends JPanel
{
    JVentana ventanaOwner;
    JButton btnDia;
    //String emocionDia;

    public DayPanel(int numero, MonthPanel mes, int anio, JVentana ventanaOwner)
    {
        this.ventanaOwner = ventanaOwner;
        btnDia = new JButton(String.valueOf(numero)); //si solo tiene un digito, añadir un 0 delante
        btnDia.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                AnimoDialog animoDlg = new AnimoDialog(ventanaOwner,true, DayPanel.this);
                //System.out.println("funciona el boton");
            }
        });
        this.add(btnDia);

        this.setVisible(true);
    }

    public void setBtnColor(Color color)
    {
        this.btnDia.setBackground(color);
        DayPanel.this.updateUI();
        //emocionDia = ;
    }
}