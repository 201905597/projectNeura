package main.java.neuraHealthUI.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.FlowLayout;
import javax.swing.JPanel;

public class ImagePanel extends JPanel
{
    private BufferedImage imagen;

    public ImagePanel(String ruta)
    {
        try
        {
            imagen = javax.imageio.ImageIO.read(new java.io.File(ruta));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        this.setLayout(new FlowLayout());
    }

    public int getAncho()
    {
        return imagen.getWidth();
    }

    public int getAlto()
    {
        return imagen.getHeight();
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(imagen, 0, 0, this);
    }
}
