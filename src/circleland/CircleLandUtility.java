/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import java.awt.Color;

/**
 *
 * @author Jeff
 */
public class CircleLandUtility {
    public static final int MONSTER_SIDE = 0;
    public static final int PLAYER_SIDE = 1;
    public static final int MAX_INVENTORY = 20;
    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = 32;
/**
 * Get a random color
 * 
 * @return a random Color
 * @author Jeffrey Miller
 */
    public static Color randomColor()
    {
        
        return new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
    }
    
}
