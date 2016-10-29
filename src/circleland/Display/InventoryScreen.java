/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Display;

import circleland.CircleEntity;
import circleland.CircleItem;
import circleland.CircleMouse;
import circleland.CircleWeapon;
import circleland.RectangleObject;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Jeff
 */
public class InventoryScreen extends RectangleObject{
    private Rectangle2D.Double equippedWeapon;
    public Rectangle2D.Double equippedWeapon(){return equippedWeapon;}
    private ArrayList<Rectangle2D.Double> slots;
    private CircleEntity player;
    public CircleEntity player(){return player;}
    public void player(CircleEntity pl){
        player = pl;
    }
    
    
    public InventoryScreen(Rectangle2D.Double r, CircleEntity pl){
        super("Inventory Screen", r);
        player = pl;
        slots = new ArrayList<>();
        //total number of slots need to be CircleLandUtility.MAX_INVENTORY size = 20
        equippedWeapon = new Rectangle2D.Double(rect.x + rect.width/2,rect.y + 25,25,25);
        for(int i = 1; i <= 4; i+= 1) {
            for(int j = 1; j <= 5; j += 1) {
                slots.add(new Rectangle2D.Double(rect.x + i * 25,rect.y + j * 25 + 50,25,25));
            }
        }
    }
    public void click(CircleMouse mouse){
        if(equippedWeapon.contains(mouse.position())) {
            if(mouse.holding() == null) {
                mouse.holding(player.equippedWeapon());
                player.equippedWeapon(null);
            }
            else if(mouse.holding() instanceof CircleWeapon)
            {
                CircleWeapon placeHolder = player.equippedWeapon();
                player.equippedWeapon((CircleWeapon)mouse.holding());
                mouse.holding(null);
                if(placeHolder != null)
                    player.inventory().add(placeHolder);
            }
        }
        else {
            boolean itemSwitched = false;
            Iterator<CircleItem> itemIterator = player.inventory().iterator();
            Iterator<Rectangle2D.Double> rectIterator = slots.iterator();
            while(itemIterator.hasNext() && rectIterator.hasNext())
            {
                CircleItem item = itemIterator.next();
                Rectangle2D.Double rect = rectIterator.next();
                if(rect.contains(mouse.position()))
                {
                    if(mouse.holding() == null) {
                        mouse.holding(item);
                        itemIterator.remove();
                    }
                    else {
                        CircleItem itemPlaceHolder = item;
                        item = mouse.holding();
                        mouse.holding(item);
                    }
                    itemSwitched = true;
                }
            }
            if(!itemSwitched){
                if(mouse.holding() != null) {
                    player.inventory().add(mouse.holding());
                    mouse.holding(null);
                }
            }
        }
    }
    public void hover(CircleMouse mouse){
        if(equippedWeapon.contains(mouse.position())) {
            mouse.hovering(player.equippedWeapon());
            
        }
        else {
            Iterator<CircleItem> itemIterator = player.inventory().iterator();
            Iterator<Rectangle2D.Double> rectIterator = slots.iterator();
            while(itemIterator.hasNext() && rectIterator.hasNext())
            {
                CircleItem item = itemIterator.next();
                Rectangle2D.Double rect = rectIterator.next();
                if(rect.contains(mouse.position()))
                {
                    mouse.hovering(item);
                }
            }
        }
    }
    public void draw(Graphics2D graphics){
        //clear background
        graphics.setColor(Color.WHITE);
        graphics.setStroke(new BasicStroke(4));
        graphics.drawRect((int)rect.x, (int)rect.y, (int)rect.width, (int)rect.height);
        graphics.setColor(Color.BLACK);
        graphics.fillRect((int)rect.x, (int)rect.y, (int)rect.width, (int)rect.height);
        //draw equipped slot
        graphics.setColor(Color.WHITE);
        graphics.drawRect((int)equippedWeapon.x, (int)equippedWeapon.y, (int)equippedWeapon.width, (int)equippedWeapon.height);
        graphics.setColor(Color.BLACK);
        graphics.fillRect((int)equippedWeapon.x, (int)equippedWeapon.y, (int)equippedWeapon.width, (int)equippedWeapon.height);
        if(player.equippedWeapon() != null)
            player.equippedWeapon().draw(graphics,
                    (int)(equippedWeapon.x + equippedWeapon.width/2) , (int)(equippedWeapon.y + equippedWeapon.height/2));
        
        Iterator<CircleItem> itemIterator = player.inventory().iterator();
        Iterator<Rectangle2D.Double> rectIterator = slots.iterator();
        while(rectIterator.hasNext())
        {
            Rectangle2D.Double rect = rectIterator.next();
            graphics.setColor(Color.WHITE);
            graphics.drawRect((int)rect.x, (int)rect.y, (int)rect.width, (int)rect.height);
            graphics.setColor(Color.BLACK);
            graphics.fillRect((int)rect.x, (int)rect.y, (int)rect.width, (int)rect.height);
            if(itemIterator.hasNext())
            {
                CircleItem item = itemIterator.next();
                item.draw(graphics, (int)(rect.x + rect.width/2), (int)(rect.y + rect.height/2));
                //draw item in slot
            }
        }
    }
}
