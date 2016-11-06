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
import circleland.CircleWeapon;
import circleland.Equipment.CircleAmulet;
import circleland.Equipment.CircleArmor;
import circleland.Equipment.CircleBoots;
import circleland.Equipment.CircleGloves;
import circleland.Equipment.CircleHelmet;
import circleland.Equipment.CircleRing;
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
    
    private Rectangle2D.Double equippedArmor;
    public Rectangle2D.Double equippedArmor(){return equippedArmor;}
    private Rectangle2D.Double equippedHelmet;
    public Rectangle2D.Double equippedHelmet(){return equippedHelmet;}
    private Rectangle2D.Double equippedGloves;
    public Rectangle2D.Double equippedGloves(){return equippedGloves;}
    private Rectangle2D.Double equippedBoots;
    public Rectangle2D.Double equippedBoots(){return equippedBoots;}
    private Rectangle2D.Double equippedRing1;
    public Rectangle2D.Double equippedRing1(){return equippedRing1;}
    private Rectangle2D.Double equippedRing2;
    public Rectangle2D.Double equippedRing2(){return equippedRing2;}
    private Rectangle2D.Double equippedAmulet;
    public Rectangle2D.Double equippedAmulet(){return equippedAmulet;}
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
        equippedWeapon = new Rectangle2D.Double(rect.x + 5,rect.y + 10,25,25);
        equippedArmor = new Rectangle2D.Double(rect.x + 35,rect.y + 10,25,25);
        equippedHelmet = new Rectangle2D.Double(rect.x + 65,rect.y + 10,25,25);
        equippedGloves = new Rectangle2D.Double(rect.x + 95,rect.y + 10,25,25);
        equippedBoots = new Rectangle2D.Double(rect.x + 5,rect.y + 40,25,25);
        equippedRing1 = new Rectangle2D.Double(rect.x + 35,rect.y + 40,25,25);
        equippedRing2 = new Rectangle2D.Double(rect.x + 65,rect.y + 40,25,25);
        equippedAmulet = new Rectangle2D.Double(rect.x + 95,rect.y + 40,25,25);
        for(int i = 1; i <= 4; i+= 1) {
            for(int j = 1; j <= 5; j += 1) {
                slots.add(new Rectangle2D.Double(rect.x + i * 25,rect.y + j * 25 + 50,25,25));
            }
        }
    }
    public void click(CircleMouse mouse){
        if(equippedWeapon.contains(mouse.position())) {
            clickWeapon(mouse);
        }
        else if(equippedArmor.contains(mouse.position())) {
            clickArmor(mouse);
        }
        else if(equippedHelmet.contains(mouse.position())) {
            clickHelmet(mouse);
        }
        else if(equippedGloves.contains(mouse.position())) {
            clickGloves(mouse);
        }
        else if(equippedBoots.contains(mouse.position())) {
            clickBoots(mouse);
        }
        else if(equippedRing1.contains(mouse.position())) {
            clickRing1(mouse);
        }
        else if(equippedRing2.contains(mouse.position())) {
            clickRing2(mouse);
        }
        else if(equippedAmulet.contains(mouse.position())) {
            clickAmulet(mouse);
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
        else if(equippedArmor.contains(mouse.position())) {
            mouse.hovering(player.equippedArmor());
        }
        else if(equippedHelmet.contains(mouse.position())) {
            mouse.hovering(player.equippedHelmet());
        }
        else if(equippedGloves.contains(mouse.position())) {
            mouse.hovering(player.equippedGloves());
        }
        else if(equippedBoots.contains(mouse.position())) {
            mouse.hovering(player.equippedBoots());
        }
        else if(equippedRing1.contains(mouse.position())) {
            mouse.hovering(player.equippedRing1());
        }
        else if(equippedRing2.contains(mouse.position())) {
            mouse.hovering(player.equippedRing2());
        }
        else if(equippedAmulet.contains(mouse.position())) {
            mouse.hovering(player.equippedAmulet());
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
    public void clickWeapon(CircleMouse mouse){
        
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
    public void clickArmor(CircleMouse mouse){
        
            if(mouse.holding() == null) {
                mouse.holding(player.equippedArmor());
                player.equippedArmor(null);
            }
            else if(mouse.holding() instanceof CircleArmor)
            {
                CircleArmor placeHolder = player.equippedArmor();
                player.equippedArmor((CircleArmor)mouse.holding());
                mouse.holding(null);
                if(placeHolder != null)
                    player.inventory().add(placeHolder);
            }
    }
    public void clickHelmet(CircleMouse mouse){
        
            if(mouse.holding() == null) {
                mouse.holding(player.equippedHelmet());
                player.equippedHelmet(null);
            }
            else if(mouse.holding() instanceof CircleHelmet)
            {
                CircleHelmet placeHolder = player.equippedHelmet();
                player.equippedHelmet((CircleHelmet)mouse.holding());
                mouse.holding(null);
                if(placeHolder != null)
                    player.inventory().add(placeHolder);
            }
    }
    public void clickGloves(CircleMouse mouse){
        
            if(mouse.holding() == null) {
                mouse.holding(player.equippedGloves());
                player.equippedGloves(null);
            }
            else if(mouse.holding() instanceof CircleGloves)
            {
                CircleGloves placeHolder = player.equippedGloves();
                player.equippedGloves((CircleGloves)mouse.holding());
                mouse.holding(null);
                if(placeHolder != null)
                    player.inventory().add(placeHolder);
            }
    }
    public void clickBoots(CircleMouse mouse){
        
            if(mouse.holding() == null) {
                mouse.holding(player.equippedBoots());
                player.equippedBoots(null);
            }
            else if(mouse.holding() instanceof CircleBoots)
            {
                CircleBoots placeHolder = player.equippedBoots();
                player.equippedBoots((CircleBoots)mouse.holding());
                mouse.holding(null);
                if(placeHolder != null)
                    player.inventory().add(placeHolder);
            }
    }
    public void clickRing1(CircleMouse mouse){
        
            if(mouse.holding() == null) {
                mouse.holding(player.equippedRing1());
                player.equippedRing1(null);
            }
            else if(mouse.holding() instanceof CircleRing)
            {
                CircleRing placeHolder = player.equippedRing1();
                player.equippedRing1((CircleRing)mouse.holding());
                mouse.holding(null);
                if(placeHolder != null)
                    player.inventory().add(placeHolder);
            }
    }
    public void clickRing2(CircleMouse mouse){
        
            if(mouse.holding() == null) {
                mouse.holding(player.equippedRing2());
                player.equippedRing2(null);
            }
            else if(mouse.holding() instanceof CircleRing)
            {
                CircleRing placeHolder = player.equippedRing2();
                player.equippedRing2((CircleRing)mouse.holding());
                mouse.holding(null);
                if(placeHolder != null)
                    player.inventory().add(placeHolder);
            }
    }
    public void clickAmulet(CircleMouse mouse){
        
            if(mouse.holding() == null) {
                mouse.holding(player.equippedAmulet());
                player.equippedAmulet(null);
            }
            else if(mouse.holding() instanceof CircleAmulet)
            {
                CircleAmulet placeHolder = player.equippedAmulet();
                player.equippedAmulet((CircleAmulet)mouse.holding());
                mouse.holding(null);
                if(placeHolder != null)
                    player.inventory().add(placeHolder);
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
        //draw Armor
        graphics.setColor(Color.WHITE);
        graphics.drawRect((int)equippedArmor.x, (int)equippedArmor.y, (int)equippedArmor.width, (int)equippedArmor.height);
        graphics.setColor(Color.BLACK);
        graphics.fillRect((int)equippedArmor.x, (int)equippedArmor.y, (int)equippedArmor.width, (int)equippedArmor.height);
        if(player.equippedArmor() != null)
            player.equippedArmor().draw(graphics,
                    (int)(equippedArmor.x + equippedArmor.width/2) , (int)(equippedArmor.y + equippedArmor.height/2));
        //draw Helmet
        graphics.setColor(Color.WHITE);
        graphics.drawRect((int)equippedHelmet.x, (int)equippedHelmet.y, (int)equippedHelmet.width, (int)equippedHelmet.height);
        graphics.setColor(Color.BLACK);
        graphics.fillRect((int)equippedHelmet.x, (int)equippedHelmet.y, (int)equippedHelmet.width, (int)equippedHelmet.height);
        if(player.equippedHelmet() != null)
            player.equippedHelmet().draw(graphics,
                    (int)(equippedHelmet.x + equippedHelmet.width/2) , (int)(equippedHelmet.y + equippedHelmet.height/2));
        //draw Gloves
        graphics.setColor(Color.WHITE);
        graphics.drawRect((int)equippedGloves.x, (int)equippedGloves.y, (int)equippedGloves.width, (int)equippedGloves.height);
        graphics.setColor(Color.BLACK);
        graphics.fillRect((int)equippedGloves.x, (int)equippedGloves.y, (int)equippedGloves.width, (int)equippedGloves.height);
        if(player.equippedGloves() != null)
            player.equippedGloves().draw(graphics,
                    (int)(equippedGloves.x + equippedGloves.width/2) , (int)(equippedGloves.y + equippedGloves.height/2));
        //draw Boots
        graphics.setColor(Color.WHITE);
        graphics.drawRect((int)equippedBoots.x, (int)equippedBoots.y, (int)equippedBoots.width, (int)equippedBoots.height);
        graphics.setColor(Color.BLACK);
        graphics.fillRect((int)equippedBoots.x, (int)equippedBoots.y, (int)equippedBoots.width, (int)equippedBoots.height);
        if(player.equippedBoots() != null)
            player.equippedBoots().draw(graphics,
                    (int)(equippedBoots.x + equippedBoots.width/2) , (int)(equippedBoots.y + equippedBoots.height/2));
         //draw Ring1
        graphics.setColor(Color.WHITE);
        graphics.drawRect((int)equippedRing1.x, (int)equippedRing1.y, (int)equippedRing1.width, (int)equippedRing1.height);
        graphics.setColor(Color.BLACK);
        graphics.fillRect((int)equippedRing1.x, (int)equippedRing1.y, (int)equippedRing1.width, (int)equippedRing1.height);
        if(player.equippedRing1() != null)
            player.equippedRing1().draw(graphics,
                    (int)(equippedRing1.x + equippedRing1.width/2) , (int)(equippedRing1.y + equippedRing1.height/2));
         //draw Ring2
        graphics.setColor(Color.WHITE);
        graphics.drawRect((int)equippedRing2.x, (int)equippedRing2.y, (int)equippedRing2.width, (int)equippedRing2.height);
        graphics.setColor(Color.BLACK);
        graphics.fillRect((int)equippedRing2.x, (int)equippedRing2.y, (int)equippedRing2.width, (int)equippedRing2.height);
        if(player.equippedRing2() != null)
            player.equippedRing2().draw(graphics,
                    (int)(equippedRing2.x + equippedRing2.width/2) , (int)(equippedRing2.y + equippedRing2.height/2));
         //draw Amulet
        graphics.setColor(Color.WHITE);
        graphics.drawRect((int)equippedAmulet.x, (int)equippedAmulet.y, (int)equippedAmulet.width, (int)equippedAmulet.height);
        graphics.setColor(Color.BLACK);
        graphics.fillRect((int)equippedAmulet.x, (int)equippedAmulet.y, (int)equippedAmulet.width, (int)equippedAmulet.height);
        if(player.equippedAmulet() != null)
            player.equippedAmulet().draw(graphics,
                    (int)(equippedAmulet.x + equippedAmulet.width/2) , (int)(equippedAmulet.y + equippedAmulet.height/2));

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
