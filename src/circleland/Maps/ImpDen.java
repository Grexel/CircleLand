/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Maps;

import circleland.CircleClass;
import circleland.CircleMap;
import circleland.CircleMouse;
import circleland.CircleNPC;
import circleland.Display.Camera;
import circleland.Items.Portal;
import circleland.MapGenerator;
import circleland.NPCs.Alaira;

/**
 *
 * @author Jeff
 */
public class ImpDen extends CircleMap {
    public ImpDen(int w, int h) {
        super(w, h);
        name = "ImpDen";
    }
    public ImpDen(Camera c, CircleMouse mouse, CircleClass pl,int sizeX, int sizeY) {
        super(c, mouse,pl,sizeX,sizeY);
        name = "This is Act1 Home";
        MapGenerator.randomTerrain(this, 1000, 60, 30);
        itemsOnGround.add(new Portal("ImpLand",200,200));
        
    }
}
