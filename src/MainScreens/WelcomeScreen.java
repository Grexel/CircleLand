/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainScreens;

import static circleland.DrawingPanel.PANEL_HEIGHT;
import static circleland.DrawingPanel.PANEL_WIDTH;
import java.awt.BorderLayout;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Jeff
 */
public class WelcomeScreen extends JPanel{
    JLabel label;
    BufferedImage image;
    public WelcomeScreen(){
        super();
        try{
            image =  ImageIO.read(new File("images/WelcomeCC.png"));
            label = new JLabel(new ImageIcon(image));
        }catch(Exception e){
            System.out.println("Failed to load welcome screen image.");
        }

        this.setOpaque(true);
        this.setLayout(new BorderLayout());
        this.add(label, BorderLayout.CENTER);

    }
}
