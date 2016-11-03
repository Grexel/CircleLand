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
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Jeff
 */
public class CharacterScreen extends RectangleObject{
    private Rectangle2D.Double equippedWeapon;
    public Rectangle2D.Double equippedWeapon(){return equippedWeapon;}
    private Rectangle2D.Double increaseStrengthSlot;
    private Rectangle2D.Double increaseDexteritySlot;
    private Rectangle2D.Double increaseMagicSlot;
    private Rectangle2D.Double increaseFortitudeSlot;
    private CircleEntity player;
    public CircleEntity player(){return player;}
    public void player(CircleEntity pl){
        player = pl;
    }
    
    
    public CharacterScreen(Rectangle2D.Double r, CircleEntity pl){
        super("Character Screen", r);
        player = pl;
        increaseStrengthSlot = new Rectangle2D.Double(rect.x ,rect.y + 100,10,10);
        increaseDexteritySlot = new Rectangle2D.Double(rect.x ,rect.y + 110,10,10);
        increaseMagicSlot = new Rectangle2D.Double(rect.x ,rect.y + 120,10,10);
        increaseFortitudeSlot = new Rectangle2D.Double(rect.x ,rect.y + 130,10,10);
    }
    public void click(CircleMouse mouse){
        if(player.statPoints() > 0){
            if(increaseStrengthSlot.contains(mouse.position())){
                player.baseStrength(player.baseStrength() + 1);
                player.statPoints(player.statPoints() - 1);
                //add stat point to strength;
            }
            if(increaseDexteritySlot.contains(mouse.position())){
                player.baseDexterity(player.baseDexterity() + 1);
                player.statPoints(player.statPoints() - 1);
                //add stat point to strength;
            }
            if(increaseMagicSlot.contains(mouse.position())){
                player.baseMagic(player.baseMagic() + 1);
                player.statPoints(player.statPoints() - 1);
                //add stat point to strength;
            }
            if(increaseFortitudeSlot.contains(mouse.position())){
                player.baseFortitude(player.baseFortitude() + 1);
                player.statPoints(player.statPoints() - 1);
                //add stat point to strength;
            }
        }
    }
    public void hover(CircleMouse mouse){
        //if stats points and on +stat slot, show how it will increase character stats.
    }
    public void draw(Graphics2D graphics){
        //clear background
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, (int)rect().y, 200, 200);
        graphics.setColor(Color.WHITE);
        graphics.drawString("Level: " + (int)player.level(), (int)rect.x + 10, (int)rect().y + 10);
        graphics.drawString("Health: " + (int)player.health() + "/" + (int)player.maxHealth(), (int)rect.x + 10, (int)rect().y + 30);
        graphics.drawString("Mana: " + (int)player.mana() + "/" + (int)player.maxMana(), (int)rect.x + 10, (int)rect().y + 40);
        graphics.drawString("EXP: " + player.experience() + "/" + 
                player.experienceToNextLevel(), (int)rect.x + 10, (int)rect().y + 50);
        graphics.drawString("Damage: " + player.minDamage() + " - " + player.maxDamage(), (int)rect.x + 10, (int)rect().y + 60);
        graphics.drawString("MagicDamage: " + player.magicDamage(),(int)rect.x + 10,(int)rect().y + 70);
        graphics.drawString("AttackDefense: " + player.attackDefense(),(int)rect.x +  10, (int)rect().y + 80);
        graphics.drawString("MagicDefense: " + player.magicDefense(),(int)rect.x + 10, (int)rect().y + 90);
        graphics.drawString("Precision: " + player.precision(), (int)rect.x + 10, (int)rect().y + 100);
        graphics.drawString("Strength: " + player.strength(), (int)rect.x + 10, (int)rect().y + 110);
        graphics.drawString("Dexterity: " + player.dexterity(), (int)rect.x + 10, (int)rect().y + 120);
        graphics.drawString("Magic: " + player.magic(), (int)rect.x + 10, (int)rect().y + 130);
        graphics.drawString("Fortitude: " + player.fortitude(), (int)rect.x + 10, (int)rect().y + 140);
        graphics.drawString("Gold: " + player.gold(), (int)rect.x + 10,(int)rect().y + 150);
        if(player.statPoints() > 0 ){
            graphics.setColor(Color.RED);
            graphics.fillRect((int)increaseStrengthSlot.x, (int)increaseStrengthSlot.y, (int)increaseStrengthSlot.width, (int)increaseStrengthSlot.height);
            graphics.fillRect((int)increaseDexteritySlot.x, (int)increaseDexteritySlot.y, (int)increaseDexteritySlot.width, (int)increaseDexteritySlot.height);
            graphics.fillRect((int)increaseMagicSlot.x, (int)increaseMagicSlot.y, (int)increaseMagicSlot.width, (int)increaseMagicSlot.height);
            graphics.fillRect((int)increaseFortitudeSlot.x, (int)increaseFortitudeSlot.y, (int)increaseFortitudeSlot.width, (int)increaseFortitudeSlot.height);

        }
        
    }
    
}
