/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Loot;

import circleland.CircleItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Jeff
 */
public class DiagramList {
    public static Random rand = new Random();
    ArrayList<ItemDiagram> diagramList;
    private boolean sorted;

    public DiagramList() {
        diagramList = new ArrayList<>();
        sorted = false;
    }
    
    private void sortList(){
        Collections.sort(diagramList);
        sorted = true;
    }
    public void addDiagram(ItemDiagram iDiag){
        diagramList.add(iDiag);
        sorted = false;
    }
    public ItemDiagram getRandomDiagram(int startLevel, int endLevel){
        if(!sorted)sortList();
        int startIndex = 0;
        int endIndex = diagramList.size();
        for(int i = 0; i < diagramList.size(); i++){
            if(diagramList.get(i).getItemLevel() == startLevel)
                startIndex = i;
            else if( diagramList.get(i).getItemLevel() == endLevel+1)
                endIndex = i;
        }
        int range = endIndex - startIndex;
        if(range <= 0) return diagramList.get(startIndex);
        return diagramList.get(rand.nextInt(range)+startIndex);
    }
    public CircleItem getItem(int startLevel, int endLevel){
        ItemDiagram diagram = getRandomDiagram(startLevel, endLevel);
        return diagram.buildItem();
    }
    public CircleItem getItem(String name){
        for(ItemDiagram itemDiag : diagramList){
            if(itemDiag.getName().equalsIgnoreCase(name))
                return itemDiag.buildItem();
        }
        return null;
    }
}
