/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import java.util.Random;

/**
 *
 * @author Jeff
 */
public abstract class CircleAffix {
    public static final Random rand = new Random();
    
    protected double value;
    public double value(){return value;}
    protected double minValue,maxValue;
    protected String prefix;
    public String prefix(){return prefix;}
    protected String suffix;
    public String suffix(){return suffix;}
    
    public abstract void addBonus(CircleEntity entity);
    public abstract String getDetails();
}
