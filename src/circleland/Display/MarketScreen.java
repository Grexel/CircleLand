/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Display;

import circleland.CircleEntity;
import circleland.CircleItem;
import circleland.CircleLandUtility;
import circleland.CircleMouse;
import circleland.CircleWeapon;
import circleland.RectangleObject;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Jeff
 */
public class MarketScreen extends RectangleObject{
    private Rectangle2D.Double equippedWeapon;
    public Rectangle2D.Double equippedWeapon(){return equippedWeapon;}
    private ArrayList<Rectangle2D.Double> slots;
    public ArrayList<Rectangle2D.Double> slots(){return slots;}
    private ArrayList<CircleItem> items;
    public ArrayList<CircleItem> items(){return items;}
    private CircleEntity player;
    public CircleEntity player(){return player;}
    public void player(CircleEntity pl){
        player = pl;
    }
    
    
    public MarketScreen(Rectangle2D.Double r, CircleEntity pl){
        super("Market Screen", r);
        slots = new ArrayList<>();
        items = new ArrayList<>();
        player= pl;
        //total number of slots need to be CircleLandUtility.MAX_INVENTORY size = 20
        for(int i = 1; i <= 4; i+= 1) {
            for(int j = 1; j <= 5; j += 1) {
                slots.add(new Rectangle2D.Double(rect.x + i * 25,rect.y + j * 25 + 50,25,25));
            }
        }
    }
    public void click(CircleMouse mouse){
        if(mouse.holding() != null) {
            //sell item to market
            //calculate gold to give
            if(items.size() < slots.size()){
                items.add(mouse.holding());
                mouse.holding(null);
                player.gold(player.gold() + 5);
            }
            else{
                mouse.holding(null);
                player.gold(player.gold() + 5);
            }
        }
        else if(player.inventory().size() < CircleLandUtility.MAX_INVENTORY){
            boolean itemSwitched = false;
            Iterator<CircleItem> itemIterator = items.iterator();
            Iterator<Rectangle2D.Double> rectIterator = slots.iterator();
            while(itemIterator.hasNext())
            {
                CircleItem item = itemIterator.next();
                Rectangle2D.Double rect = rectIterator.next();
                if(rect.contains(mouse.position()))
                {
                    player.inventory().add(item);
                    itemIterator.remove();
                }
            }
        }
    }
    public void hover(CircleMouse mouse){
        Iterator<CircleItem> itemIterator = items.iterator();
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
    public void draw(Graphics2D graphics){
        //clear background
        graphics.setColor(Color.WHITE);
        graphics.setStroke(new BasicStroke(4));
        graphics.drawRect((int)rect.x, (int)rect.y, (int)rect.width, (int)rect.height);
        graphics.setColor(Color.BLACK);
        graphics.fillRect((int)rect.x, (int)rect.y, (int)rect.width, (int)rect.height);
        
        Iterator<CircleItem> itemIterator = items.iterator();
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
