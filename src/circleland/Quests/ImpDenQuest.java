/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Quests;

import circleland.CircleClass;
import circleland.CircleMap;

/**
 *
 * @author Jeff
 */
public class ImpDenQuest extends Quest{
    private boolean firstEntry;
    public ImpDenQuest(){
        super();
        firstEntry = true;
    }
    @Override
    public void update(CircleClass player, CircleMap cM) {
        if(cM.name().equalsIgnoreCase("ImpDen")){
            if(cM.monsters().size() > 0){
                firstEntry = false;
            }
            if(cM.monsters().isEmpty()){
                if(!firstEntry){
                    completed = true;
                }
            }
        }
    }
    
}
