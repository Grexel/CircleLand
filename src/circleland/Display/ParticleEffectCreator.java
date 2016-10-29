/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Display;

import circleland.CircleMap;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Jeff
 */
public class ParticleEffectCreator {
    private static final Random rand = new Random();
    public static void
        createParticleExplosion(CircleMap world,int numParticles,int x, int y,
            double speed, int lf,int minSize,int maxSize, Color[] colorScheme){
            ArrayList<ParticleDisplayObject> particles = new ArrayList<>();
            for(int i = 0; i < numParticles; i++){
                double heading = Math.random()*2*Math.PI;
                double velX = Math.cos(heading) * speed;
                double velY = Math.sin(heading) * speed;
                int life = rand.nextInt(lf-200)+200;
                int size = rand.nextInt(maxSize-minSize)+minSize;
                Color c = colorScheme[rand.nextInt(colorScheme.length)];
                particles.add(new ParticleDisplayObject(x,y,velX,velY,life,size,c));
    //public ParticleDisplayObject(int x,int y, double velX, double velY,int lf,int size,Color c){
            }
            world.particleDisplay().addAll(particles);
    }
    public static void
        createParticleBlowOut(CircleMap world,int numParticles,int x, int y,
            double spd,double direction,int lf,int minSize,int maxSize, Color[] colorScheme){
                ArrayList<ParticleDisplayObject> particles = new ArrayList<>();
                for(int i = 0; i < numParticles; i++){
                double heading = (Math.random()-.5)*Math.PI/2 +direction;
                double speed = Math.random()*spd/2 + spd/2;
                double velX = Math.cos(heading) * speed;
                double velY = Math.sin(heading) * speed;
                int life = rand.nextInt(lf-200)+200;
                int size = rand.nextInt(maxSize-minSize)+minSize;
                Color c = colorScheme[rand.nextInt(colorScheme.length)];
                particles.add(new ParticleDisplayObject(x,y,velX,velY,life,size,c));
    //public ParticleDisplayObject(int x,int y, double velX, double velY,int lf,int size,Color c){
            }
            world.particleDisplay().addAll(particles);
    }
}
