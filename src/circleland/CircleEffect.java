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
public class CircleEffect {
    protected int life;
    public void life(int m){life = m;}
    public int life(){return life;}
    
    public CircleEffect(int lf){
        life = lf;
    }
    public void update(long deltaTime, CircleEntity owner){
        life -= deltaTime;
    }
    public void hitBy(CircleAttack attack,CircleEntity owner){
        
    }
    public void attacked(CircleEntity hit,CircleEntity owner, CircleAttack attack)
    {
        
    }
    public void draw(Graphics2D graphics, CircleEntity owner){
        
    }
}
