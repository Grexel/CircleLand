/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Maps;

import circleland.CircleClass;
import circleland.CircleLandUtility;
import circleland.CircleMap;
import circleland.CircleMouse;
import circleland.Display.Camera;
import circleland.LootGenerator;
import circleland.SpriteSheet;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Jeff
 */
public class ImpLands extends CircleMap{
    
    public ImpLands(int w, int h)
    {
        super(w,h);
        spriteSheet = new SpriteSheet("images/terrain_atlas.png",CircleLandUtility.TILE_WIDTH,CircleLandUtility.TILE_HEIGHT);
        //randomize layerData
        //add monsters
    }
    
    public ImpLands(Camera c, CircleMouse mouse, CircleClass pl,int sizeX, int sizeY)
    {
        super(c,mouse,pl,sizeX,sizeY);
    }
    
}
