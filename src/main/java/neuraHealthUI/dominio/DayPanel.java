package neuraHealthUI.dominio;

import neuraHealthUI.ui.ColoresDialog;
import neuraHealthUI.ui.JVentana;


import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Color;
import java.io.Serializable;

public class DayPanel extends JPanel
{
    JVentana ventanaOwner;
    MonthPanel mes;
    String tipoDia;
    String diaDosDigitos;
    JButton btnDia;
    String asociacion;
    int coloreado;

    public DayPanel(int numero, MonthPanel mes, int anio, JVentana ventanaOwner, String tipoDia)
    {
        this.ventanaOwner = ventanaOwner;
        this.setTipoDia(tipoDia);
        this.mes = mes;
        this.setAsociacion(" ");
        this.setColoreado(0);

        //si solo tiene un digito, añadir un 0 delante
        if (numero<10)
            diaDosDigitos = "0" + String.valueOf(numero);
        else
            diaDosDigitos = String.valueOf(numero);

        btnDia = new JButton(diaDosDigitos);

        /*btnDia.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("ENTRO EN EL ACTION PERFORMED");
                ColoresDialog coloresDlg = new ColoresDialog(DayPanel.this.getFecha(),ventanaOwner,true, DayPanel.this,ventanaOwner.getIdConectado(), tipoDia);
            }
        });*/
        this.add(btnDia);

        this.setVisible(true);
    }

    public void setBtnColor(Color color)
    {
        this.btnDia.setBackground(color);
        DayPanel.this.updateUI();
        DayPanel.this.setColoreado(1);
    }

    public void setTipoDia(String tipoDia)
    {
        this.tipoDia = tipoDia;
    }

    public String getFecha()
    {
        return diaDosDigitos + mes.getMesYAnio();
    }

    public void setAsociacion(String asociacion)
    {
        this.asociacion = asociacion;
    }
    public String getAsociacion()
    {
        return asociacion;
    }

    public String getDiaDosDigitos()
    {
        return diaDosDigitos;
    }

    /**
     * @return true si se ha rellenado el día (con emoción o hábito)
     */
    public boolean isColoreado()
    {
        if (coloreado == 1)
            return true;
        else
            return false;
    }

    /**
     * @return objeto String del mes y el año concatenados "Enero2022"
     */
    public String getMesYAnio()
    {
        return mes.getMesYAnio();
    }

    public void setColoreado(int coloreado)
    {
        this.coloreado = coloreado;
    }

    //prueba
    public void setEnabledBtn()
    {
        this.btnDia.setEnabled(true);
    }

    public JButton getBtnDia()
    {
        return btnDia;
    }
}










