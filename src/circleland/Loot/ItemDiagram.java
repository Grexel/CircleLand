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
    int rarity;
    public ItemDiagram(int itemLevel, String name, int rarity) {
        this.itemLevel = itemLevel;
        this.name = name;
        this.rarity = rarity;
    }
    
    public abstract CircleItem buildItem();
    public abstract String codeItem(CircleItem item);
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
