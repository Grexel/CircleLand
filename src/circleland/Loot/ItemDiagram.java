/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Loot;

import circleland.CircleItem;

/**
 *
 * @author Jeff
 */
public abstract class ItemDiagram implements Comparable<ItemDiagram> {
    private int itemLevel;
    private String name;

    public ItemDiagram(int itemLevel, String name) {
        this.itemLevel = itemLevel;
        this.name = name;
    }
    
    public abstract CircleItem buildItem();

    public int getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(int itemLevel) {
        this.itemLevel = itemLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }   
    
    @Override
    public int compareTo(ItemDiagram compareDiag) {
        int compareLevel = compareDiag.getItemLevel();
        return this.itemLevel-compareLevel;
    }
}
