/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Maps;

import circleland.Display.Camera;
import circleland.CircleClass;
import circleland.CircleEntity;
import circleland.CircleMap;
import circleland.CircleMouse;
import circleland.CircleNPC;
import circleland.Items.Portal;
import circleland.MapGenerator;
import circleland.NPCs.Alaira;

/**
 *  http://fantasynamegenerators.com/town_names.php
 *  Lunaris     Aquarin     MyreFall    GoldenLeaf  Frosa
 *  BlackHallows    WillowVale  SilverKeep  Perlshore
 * @author Jeff
 */
public class Act1HomeMap extends CircleMap {
    private CircleNPC Alaira;
    public Act1HomeMap(int w, int h) {
        super(w, h);
        name = "This is Act1 Home";
    }
    public Act1HomeMap(Camera c, CircleMouse mouse, CircleClass pl,int sizeX, int sizeY) {
        super(c, mouse,pl,sizeX,sizeY);
        name = "This is Act1 Home";
        MapGenerator.randomTerrain(this, 1000, 60, 30);
        itemsOnGround.add(new Portal("ImpLand",200,200));
        
        Alaira = new Alaira();
        Alaira.position().x = 200;
        Alaira.position().y = 400;
        Alaira.focusedEntity(pl);
        players.add(Alaira);
    }
}
