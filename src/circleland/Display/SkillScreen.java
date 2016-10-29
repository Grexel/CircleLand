/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Display;

import circleland.CircleEntity;
import circleland.CircleItem;
import circleland.CircleMouse;
import circleland.CircleSkill;
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
public class SkillScreen extends RectangleObject{
    private Rectangle2D.Double equippedSkill;
    public Rectangle2D.Double equippedSkill(){return equippedSkill;}
    private ArrayList<Rectangle2D.Double> slots;
    private CircleEntity player;
    public CircleEntity player(){return player;}
    public void player(CircleEntity pl){
        player = pl;
    }
    
    
    public SkillScreen(Rectangle2D.Double r, CircleEntity pl){
        super("Skill Screen",r);
        rect = r;
        player = pl;
        slots = new ArrayList<>();
        //total number of slots need to be CircleLandUtility.MAX_INVENTORY size = 20
        equippedSkill = new Rectangle2D.Double(rect.x + rect.width/2,rect.y + 25,25,25);
        for(int i = 1; i <= 4; i+= 1) {
            for(int j = 1; j <= 5; j += 1) {
                slots.add(new Rectangle2D.Double(rect.x + i * 25,rect.y + j * 25 + 50,25,25));
            }
        }
    }
    public void click(CircleMouse mouse){
        if(equippedSkill.contains(mouse.position())) {
            if(mouse.holding() == null) {
                mouse.holding(player.equippedSkill());
                player.equippedSkill(null);
            }
            else if(mouse.holding() instanceof CircleSkill)
            {
                CircleSkill placeHolder = player.equippedSkill();
                player.equippedSkill((CircleSkill)mouse.holding());
                mouse.holding(placeHolder);
            }
        }
        else {
            boolean itemSwitched = false;
            Iterator<CircleSkill> itemIterator = player.skills().iterator();
            Iterator<Rectangle2D.Double> rectIterator = slots.iterator();
            while(itemIterator.hasNext())
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
                    player.skills().add((CircleSkill)mouse.holding());
                    mouse.holding(null);
                }
            }
        }
    }
    public void hover(CircleMouse mouse){
        if(equippedSkill.contains(mouse.position())) {
            mouse.hovering(player.equippedSkill());
        }
        else {
            Iterator<CircleSkill> itemIterator = player.skills().iterator();
            Iterator<Rectangle2D.Double> rectIterator = slots.iterator();
            while(itemIterator.hasNext())
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
        graphics.drawString("Skills Points Left: " + player.skillPoints(), (int)rect.x + 10, (int)rect.y+10);
        graphics.drawRect((int)equippedSkill.x, (int)equippedSkill.y, (int)equippedSkill.width, (int)equippedSkill.height);
        graphics.setColor(Color.BLACK);
        graphics.fillRect((int)equippedSkill.x, (int)equippedSkill.y, (int)equippedSkill.width, (int)equippedSkill.height);
        if(player.equippedSkill() != null)
            player.equippedSkill().draw(graphics,
                    (int)(equippedSkill.x + equippedSkill.width/2) , (int)(equippedSkill.y + equippedSkill.height/2));
        
        Iterator<CircleSkill> itemIterator = player.skills().iterator();
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
