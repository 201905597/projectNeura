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
    MonthPanel mes;
    String diaDosDigitos;
    JButton btnDia;
    String emocion;
    int coloreado;

    public DayPanel(int numero, MonthPanel mes, int anio, JVentana ventanaOwner)
    {
        this.ventanaOwner = ventanaOwner;
        this.mes = mes;
        this.setEmocion(" ");
        coloreado = 0;

        //si solo tiene un digito, añadir un 0 delante
        if (numero<10)
            diaDosDigitos = "0" + String.valueOf(numero);
        else
            diaDosDigitos = String.valueOf(numero);

        btnDia = new JButton(diaDosDigitos);

        btnDia.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                AnimoDialog animoDlg = new AnimoDialog(ventanaOwner,true, DayPanel.this);
            }
        });
        this.add(btnDia);

        this.setVisible(true);
    }

    public void setBtnColor(Color color)
    {
        this.btnDia.setBackground(color);
        DayPanel.this.updateUI();
        coloreado = 1;
    }

    //añadido 2 oct
    public String getFecha()
    {
        return diaDosDigitos + mes.getMesYAnio();
    }

    public void setEmocion(String emocion)
    {
        this.emocion = emocion;
    }
    public String getEmocion()
    {
        return emocion;
    }

    public String getDiaDosDigitos()
    {
        return diaDosDigitos;
    }

    public boolean isColoreado()
    {
        if (coloreado == 1)
            return true;
        else
            return false;
    }

    public String getMesYAnio()
    {
        return mes.getMesYAnio();
    }
    //-----------
}