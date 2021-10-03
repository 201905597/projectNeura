package neuraHealthUI.ui;


import icai.dtc.isw.dao.CustomerDAO;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class UsernameDialog extends JDialog {
    private JVentana ventanaOwner;
    JTextField txtId;

    public UsernameDialog(JVentana ventanaOwner, boolean modal) {
        this.setTitle("Log In / Acceder");
        this.setModal(modal);
        this.setOwner(ventanaOwner);


        this.setLayout(new GridLayout(4, 2));

        JLabel lblUser = new JLabel("Usuario: ");
        JTextField txtUser = new JTextField(12);
        txtUser.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    txtId.requestFocus();
            }
        });

        this.add(lblUser);
        this.add(txtUser);

        JLabel lblId = new JLabel("Id: ");
        txtId = new JTextField(12);
        txtId.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    txtId.requestFocus();
            }
        });
        this.add(lblId);
        this.add(txtId);


        JButton btnAcceder = new JButton("Acceder");
        btnAcceder.setBackground(new Color(255, 102, 102));
        btnAcceder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    String nombre = txtUser.getText();
                    String id = txtId.getText();
                    CustomerDAO customerDao = new CustomerDAO();
                    int respuesta= customerDao.autenticar(id, nombre);
                    if (respuesta==1)
                    {
                        ventanaOwner.setIdConectado(id); //añadido 2 oct
                        (UsernameDialog.this).dispose();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(UsernameDialog.this, "No se encuentra el nombre o el id en la base de datos");
                    }
            }
        });


        this.add(btnAcceder);
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(51,204,255));
        btnCancelar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                (UsernameDialog.this).dispose();
                ventanaOwner.dispose();
            }
        });
        //-------------

        this.add(btnCancelar);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(300, 200);
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }
    public void setOwner(JVentana ventanaOwner)
    {
        this.ventanaOwner = ventanaOwner;
    }

}
