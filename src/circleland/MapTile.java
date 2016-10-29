/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import java.awt.image.BufferedImage;

/**
 *
 * @author Jeff
 */
public class MapTile {
    public static int tileWidth = 50;
    public static int tileHeight= 50;
    protected boolean solid;
    public void solid(boolean b){solid = b;}
    public boolean solid(){return solid;}
    
    protected BufferedImage image;
    public void image(BufferedImage b){image = b;}
    public BufferedImage image(){return image;}
    
    public MapTile(){
        solid = false;
        image = null;
    }
}
