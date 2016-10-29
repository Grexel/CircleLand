/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Display;

import circleland.CircleMap;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 *
 * @author Jeff
 */
public class ParticleEmitter {
    private static final Random rand = new Random();
    private Point2D.Double position;
    private Color[] colorScheme;
    private int emitterTimer, emitterDelay;
    private int minSize, maxSize;
    private int minLife, maxLife;
    private int maxOffset;
    
    public ParticleEmitter(Point2D.Double pos){
        position = pos;
        emitterTimer = 0;
        emitterDelay = 10;
        minSize = 6; maxSize = 12;
        minLife = 200; maxLife = 300;
        maxOffset = 10;
        colorScheme = ColorSchematic.HIGHWAY_COLORSCHEME;
    }
    public ParticleEmitter(Point2D.Double pos, int delay,int minS,int maxS, int minL, int maxL,int mOff,Color[] scheme){
        position = pos;
        emitterTimer = 0;
        emitterDelay = delay;
        minSize = minS;
        maxSize = maxS;
        minLife = minL;
        maxLife = maxL;
        maxOffset = mOff;
        colorScheme = scheme;
    }
    public void update(long deltaTime, CircleMap world){
        emitterTimer -= deltaTime;
        if(emitterTimer <= 0){
            emitParticles(world);
            emitterTimer += emitterDelay;
        }
    }
    public void emitParticles(CircleMap world){
        int posX = (int)(position.x + rand.nextInt(maxOffset)-maxOffset/2);
        int posY = (int)(position.y + rand.nextInt(maxOffset)-maxOffset/2);
        double velX = 0;
        double velY = 0;
        int size = rand.nextInt(maxSize - minSize) + minSize;
        int life = rand.nextInt(maxLife - minLife) + minLife;
        Color c = colorScheme[rand.nextInt(colorScheme.length)];
        ParticleDisplayObject particle = 
                new ParticleDisplayObject(posX,posY,velX,velY,life,size,c);
        world.particleDisplay().add(particle);
    }
    
}
