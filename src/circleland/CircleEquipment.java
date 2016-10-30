/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Jeff
 */
public abstract class CircleEquipment extends CircleItem{
    protected ArrayList<CircleAffix> affixes;
    public void affixes(ArrayList<CircleAffix> aff){affixes = aff;}
    public ArrayList<CircleAffix> affixes(){return affixes;}
    
    protected int itemLevel;
    public void itemLevel(int i){itemLevel = i;}
    public int itemLevel(){return itemLevel;}
    
    public CircleEquipment()
    {
        super();
        affixes = new ArrayList<>();
        itemLevel = 1;
    }
    public abstract void addBonus(CircleEntity entity);
}
