/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Jeff
 */
public class SpriteSheet {
    public BufferedImage image;
    public String nameOfImage;
    public Rectangle[] imageTiles;
    public BufferedImage[] subImages;
    public int tileWidth,tileHeight;
    public int numberOfSprites;
    public SpriteSheet(String imageName,int tWidth,int tHeight){
        tileWidth = tWidth;
        tileHeight = tHeight;
        try {
            image = ImageIO.read(new File("images/" + imageName));
            image = toCompatibleImage(image);
        } catch (Exception e) {
        }
        numberOfSprites = image.getWidth()/tWidth * image.getHeight()/tHeight;
        imageTiles = new Rectangle[numberOfSprites];
        subImages = new BufferedImage[numberOfSprites];
        int nextRect = 0;
        for(int y = 0; y < image.getHeight(); y+=tileHeight){
            for(int x = 0; x < image.getWidth(); x+= tileWidth){
                imageTiles[nextRect] = new Rectangle(x,y,tileWidth,tileHeight);
                subImages[nextRect] = createSubImage(image,imageTiles[nextRect]);
                System.out.println("tile:"+ nextRect + "x" + x + "y" + y + "w"+tileWidth+"h"+tileHeight);
                nextRect++;
            }
        }
    }
    public void draw(Graphics2D graphics, int xPos,int yPos, int tileNumber){
        if(tileNumber == 0)return;//tile#0 is empty
        graphics.drawImage(subImages[tileNumber-1],xPos,yPos,null);
        //Rectangle r = imageTiles[tileNumber-1];
        //graphics.drawImage(image.getSubimage(r.x, r.y, r.width, r.height),xPos,yPos,null);
    }
    
    private BufferedImage createSubImage(BufferedImage source, Rectangle subRect){
	GraphicsConfiguration gfx_config = GraphicsEnvironment.
		getLocalGraphicsEnvironment().getDefaultScreenDevice().
		getDefaultConfiguration();
        
	BufferedImage subImage = gfx_config.createCompatibleImage(
			subRect.width, subRect.height, image.getTransparency());

	// get the graphics context of the new image to draw the old image on
	Graphics2D g2d = (Graphics2D) subImage.getGraphics();

	// actually draw the image and dispose of context no longer needed
	g2d.drawImage(image.getSubimage(subRect.x, subRect.y, tileWidth, tileHeight), 0, 0, null);
	g2d.dispose();

	// return the new optimized image
	return subImage; 
        
    }
    //http://stackoverflow.com/questions/196890/java2d-performance-issues
    //answered by consty
    private BufferedImage toCompatibleImage(BufferedImage image)
    {
	// obtain the current system graphical settings
	GraphicsConfiguration gfx_config = GraphicsEnvironment.
		getLocalGraphicsEnvironment().getDefaultScreenDevice().
		getDefaultConfiguration();

	/*
	 * if image is already compatible and optimized for current system 
	 * settings, simply return it
	 */
	if (image.getColorModel().equals(gfx_config.getColorModel()))
		return image;

	// image is not optimized, so create a new image that is
	BufferedImage new_image = gfx_config.createCompatibleImage(
			image.getWidth(), image.getHeight(), image.getTransparency());

	// get the graphics context of the new image to draw the old image on
	Graphics2D g2d = (Graphics2D) new_image.getGraphics();

	// actually draw the image and dispose of context no longer needed
	g2d.drawImage(image, 0, 0, null);
	g2d.dispose();

	// return the new optimized image
	return new_image; 
    }
}
