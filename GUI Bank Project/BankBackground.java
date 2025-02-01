import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.*;

public class BankBackground extends JPanel{
    Image BankImage;
    BankBackground()
    {
        this.setPreferredSize(new Dimension(1900,1000));
        BankImage = new ImageIcon("background.jpeg").getImage();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        g2D.drawImage(BankImage , 0 , 0 , getWidth() , getHeight() , this);
    }
}


class SubMenu extends JPanel{
    Image BankImage;
    SubMenu()
    {
        this.setPreferredSize(new Dimension(1900,1000));
        BankImage = new ImageIcon("bank.jpeg").getImage();
        if (BankImage == null) {
            System.out.println("Error: Image not loaded!");
        }
        
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        g2D.drawImage(BankImage , 0 , 0 , getWidth() , getHeight() , this);
    }
}

