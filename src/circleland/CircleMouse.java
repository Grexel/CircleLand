/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import circleland.Display.Camera;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 *
 * @author Jeff
 */
public class CircleMouse {
    private Point2D.Double position;
    public void position(Point2D.Double point){position = point;}
    public Point2D.Double position(){ return position;}
    
    private boolean leftMousePressed;
    public void mousePressed(boolean m){leftMousePressed = m;}
    public boolean mousePressed(){ return leftMousePressed;}
    
    private boolean mouseLeftClicked;
    public void mouseLeftClicked(boolean m){mouseLeftClicked = m;}
    public boolean mouseLeftClicked(){ return mouseLeftClicked;}
    
    private boolean mouseRightClicked;
    public void mouseRightClicked(boolean m){mouseRightClicked = m;}
    public boolean mouseRightClicked(){ return mouseRightClicked;}
    
    private CircleItem holding;
    public void holding(CircleItem m){holding = m;}
    public CircleItem holding(){ return holding;}
    
    private CircleItem hovering;
    public void hovering(CircleItem m){hovering = m;}
    public CircleItem hovering(){ return hovering;}
    
    private CircleItem hoveringDetails;
    public void hoveringDetails(CircleItem m){hoveringDetails = m;}
    public CircleItem hoveringDetails(){ return hoveringDetails;}
    
    private Camera camera;
    public void camera(Camera m){camera = m;}
    public Camera camera(){ return camera;}
    
    private Point2D.Double ingamePosition;
    
    private CircleClass player;
    public void player(CircleClass m){player = m;}
    public CircleClass player(){ return player;}
    
    public CircleMouse(Camera c, CircleClass pl){
        position = new Point2D.Double(0,0);
        leftMousePressed = false;
        mouseLeftClicked = false;
        mouseRightClicked = false;
        holding = null;
        hovering = null;
        hoveringDetails = null;
        camera = c;
        player = pl;
        ingamePosition = new Point2D.Double(0,0);
    }
    public void reset(){
        hovering = null;
        hoveringDetails = null;
    }
    public void update(long deltaTime, CircleMap world) {
       ingamePosition.x = position.x + camera.rect().x;
       ingamePosition.y = position.y + camera.rect().y;
        
        if(hoveringDetails != null && mouseLeftClicked){
                System.out.println("Picking up1");
            if(player.distanceBetweenPoints(ingamePosition, player.position()) <= player.viewRadius())
            {
                //player pickup
                System.out.println("Picking up2");
                if(player.pickUp(hoveringDetails,world)){
                    world.itemsOnGround().remove(hoveringDetails);
                }
            }
        }
        reset();
    }
    public void draw(Graphics2D graphics)
    {
        if(holding != null)
        {
            holding.draw(graphics, (int)position.x, (int)position.y);
        }
        if(hoveringDetails != null){
            int boxlength = graphics.getFontMetrics().stringWidth(hoveringDetails.name());
            graphics.setColor(Color.BLACK);
            graphics.fillRect((int)position.x-boxlength, (int)position.y-20, boxlength,20);
            graphics.setColor(hoveringDetails.rarityColor());
            graphics.drawString(hoveringDetails.name(), (int)position.x-boxlength, (int)position.y-5);
        }
    }
    public Point2D.Double returnIngamePoint(){
       return ingamePosition;
    }
}
