package neuraHealthUI.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.awt.*;

/**
 *Es un JDialog que permite al usuario jugar a distintos juegos, haciendo uso de la clase URLabel para abrir hipervinculos en el navegador del usuario.
 */
public class JuegoDialog extends JDialog {


    public JuegoDialog(boolean modal) {

        this.setTitle("Juegos");
        this.setModal(modal);

        JLabel lblJuegos = new JLabel("MIS JUEGOS",SwingConstants.CENTER);
        lblJuegos.setPreferredSize(new Dimension(20, 20));
        lblJuegos.setForeground(Color.BLUE.darker());
        this.add(lblJuegos, BorderLayout.NORTH);

        //JUEGOS RELAJANTES
        /*JPanel pnlTitulo1=new JPanel();
        pnlTitulo1.setBorder(javax.swing.BorderFactory.createTitledBorder("Titulo"));
        JLabel lblJuegosRelajantes = new JLabel("Juegos relajantes",SwingConstants.CENTER);
        lblJuegosRelajantes.setPreferredSize(new Dimension(40, 20));
        lblJuegosRelajantes.setForeground(Color.BLUE.darker());
        pnlTitulo1.add(lblJuegosRelajantes);
        this.add(pnlTitulo1);
        */



        JPanel pnlJuegosRelaj = new JPanel();
        pnlJuegosRelaj.setBorder(javax.swing.BorderFactory.createTitledBorder("Juegos relajantes"));
        pnlJuegosRelaj.setLayout(new GridLayout(2,3));



        JPanel pnlJuegosRelaj1= new JPanel();
        JPanel pnlJuegosRelaj2 = new JPanel();
        JPanel pnlJuegosRelaj3= new JPanel();
        JPanel pnlJuegosRelaj4 = new JPanel();
        JPanel pnlJuegosRelaj5= new JPanel();
        JPanel pnlJuegosRelaj6 = new JPanel();

        JLabel lblImagen1 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Juego1.jpg")));
        pnlJuegosRelaj1.add(lblImagen1);

        JLabel lblImagen2 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Juego2.jpeg")));
        pnlJuegosRelaj2.add(lblImagen2);

        JLabel lblImagen3 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Juego3.jpg")));
        pnlJuegosRelaj3.add(lblImagen3);

        URLabel label1 = new URLabel();
        label1.setURL("https://www.juegosdiarios.com/juegos/helix-jump-online.html");
        label1.setText("Jugar al Helix Jump");
        pnlJuegosRelaj4.add(label1);

        URLabel label2 = new URLabel();
        label2.setURL("https://poki.com/es/g/one-more-bounce");
        label2.setText("Jugar a One more bounce");
        pnlJuegosRelaj5.add(label2);

        URLabel label3 = new URLabel();
        label3.setURL("https://www.1001juegos.com/juego/blue");
        label3.setText("Jugar a Blue");
        pnlJuegosRelaj6.add(label3);


        pnlJuegosRelaj.add(pnlJuegosRelaj1);
        pnlJuegosRelaj.add(pnlJuegosRelaj2);
        pnlJuegosRelaj.add(pnlJuegosRelaj3);
        pnlJuegosRelaj.add(pnlJuegosRelaj4);
        pnlJuegosRelaj.add(pnlJuegosRelaj5);
        pnlJuegosRelaj.add(pnlJuegosRelaj6);

        this.add(pnlJuegosRelaj,BorderLayout.CENTER);


        //JUEGOS PARA EJERCITAR LA MENTE
        /*JLabel lblJuegosEjercMente = new JLabel("Juegos para ejercitar la mente",SwingConstants.CENTER);
        lblJuegosEjercMente.setPreferredSize(new Dimension(40, 20));
        lblJuegosEjercMente.setForeground(Color.BLUE.darker());
        this.add(lblJuegosEjercMente,BorderLayout.CENTER);
        */




        JPanel pnlJuegosEjercMente = new JPanel();
        pnlJuegosEjercMente.setBorder(javax.swing.BorderFactory.createTitledBorder("Juegos para ejercitar la mente"));
        pnlJuegosEjercMente.setLayout(new GridLayout(2,3));
        JPanel pnlJuegosEjercMente1= new JPanel();
        JPanel pnlJuegosEjercMente2 = new JPanel();
        JPanel pnlJuegosEjercMente3= new JPanel();
        JPanel pnlJuegosEjercMente4 = new JPanel();
        JPanel pnlJuegosEjercMente5= new JPanel();
        JPanel pnlJuegosEjercMente6 = new JPanel();

        JLabel lblImagen4 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Juego4.png")));
        pnlJuegosEjercMente1.add(lblImagen4);

        JLabel lblImagen5 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Juego5.JPG")));
        pnlJuegosEjercMente2.add(lblImagen5);

        JLabel lblImagen6 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("Juego6.png")));
        pnlJuegosEjercMente3.add(lblImagen6);

        URLabel label4 = new URLabel();
        label4.setURL("https://www.cognifit.com/es/juegos-mentales/buscapalabras#");
        label4.setText("Jugar al buscapalabras");
        pnlJuegosEjercMente4.add(label4);

        URLabel label5 = new URLabel();
        label5.setURL("https://www.ajedrezonline.com/");
        label5.setText("Jugar al ajedrez");
        pnlJuegosEjercMente5.add(label5);

        URLabel label6 = new URLabel();
        label6.setURL("https://www.cognifit.com/es/juegos-mentales/sudoku");
        label6.setText("Jugar al sudoku");
        pnlJuegosEjercMente6.add(label6);


        pnlJuegosEjercMente.add(pnlJuegosEjercMente1);
        pnlJuegosEjercMente.add(pnlJuegosEjercMente2);
        pnlJuegosEjercMente.add(pnlJuegosEjercMente3);
        pnlJuegosEjercMente.add(pnlJuegosEjercMente4);
        pnlJuegosEjercMente.add(pnlJuegosEjercMente5);
        pnlJuegosEjercMente.add(pnlJuegosEjercMente6);

        this.add(pnlJuegosEjercMente,BorderLayout.SOUTH);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(600, 600);
        this.setResizable(false);
        this.setVisible(true);

    }




}
