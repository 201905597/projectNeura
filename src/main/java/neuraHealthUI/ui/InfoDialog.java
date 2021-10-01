package neuraHealthUI.ui;

import neuraHealthUI.io.IOutilities;

import javax.swing.JDialog;
import javax.swing.SwingConstants;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import java.awt.Font;

public class InfoDialog extends JDialog
{
    public InfoDialog(JVentana ventanaOwner, boolean modal)
    {
        this.setModal(modal);
        this.setTitle("Información");
        this.setLayout(new BorderLayout());

        //NORTE
        JLabel lblTitulo = new JLabel("INFORMACIÓN",SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Courier", Font.BOLD, 20));
        this.add(lblTitulo, BorderLayout.NORTH);

        //CENTRO (text pane)
        JPanel pnlCentro = new JPanel();
        JTextArea txtArea = new JTextArea(20,70);
        IOutilities.TextFromFile(txtArea);
        JScrollPane scroll = new JScrollPane(txtArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        pnlCentro.add(scroll);
        this.add(pnlCentro, BorderLayout.CENTER);

        this.pack();
        this.setSize(800,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}