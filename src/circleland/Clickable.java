/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import java.awt.Graphics2D;

/**
 *
 * @author Jeff
 */
public interface Clickable {
    public void clicked();
    public void drawHover(Graphics2D graphics);
    public void drawHover(Graphics2D graphics,int xPos,int yPos);
}