/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Quests;

import circleland.*;

/**
 *
 * @author Jeff
 */
public abstract class Quest {
    
    protected boolean completed;
    public boolean completed(){return completed;}
    public void completed(boolean l){completed = l;}
    
    public Quest(){
        completed = false;
    }
    public abstract void update(CircleClass player,CircleMap cM);
}
