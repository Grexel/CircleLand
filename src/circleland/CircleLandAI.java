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
public abstract class CircleLandAI {
    public static final Random rand = new Random();
    protected boolean hitEnemy;
    public boolean hitEnemy(){return hitEnemy;}
    public void hitEnemy(boolean ai){hitEnemy = ai;}
    
    public abstract void update(long deltaTime, CircleEntity owner, CircleMap world);
}
