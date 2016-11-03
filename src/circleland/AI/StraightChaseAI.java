/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.AI;

import circleland.CircleEntity;
import circleland.CircleLandAI;
import circleland.CircleLandUtility;
import circleland.CircleMap;
import circleland.CircleObject;

/**
 *
 * @author Jeff
 */
public class StraightChaseAI extends CircleLandAI{
    private int moveChangeTimer;
    private int moveChangeDelay;
    private int stayAwayRange;
    public StraightChaseAI(){
        moveChangeTimer = 0;
        moveChangeDelay = 500;
        stayAwayRange = 25;
    }
    @Override
    public void update(long deltaTime, CircleEntity owner, CircleMap world) {
        moveChangeTimer -= deltaTime;
        //move towards enemy.
        if(owner.focusedEntity() != null) {
            //move left and right
            if (owner.focusedEntity().position().x > owner.position().x + stayAwayRange){
                owner.moveRight(true);
                owner.moveLeft(false);
            } else if(owner.focusedEntity().position().x < owner.position().x - stayAwayRange) {
                owner.moveLeft(true);
                owner.moveRight(false);
            } else {
                owner.moveLeft(false);
                owner.moveRight(false);
            }
            //move up and down
            if (owner.focusedEntity().position().y > owner.position().y + stayAwayRange){
                owner.moveDown(true);
                owner.moveUp(false);
            } else if(owner.focusedEntity().position().y < owner.position().y - stayAwayRange) {
                owner.moveUp(true);
                owner.moveDown(false);
            } else {
                owner.moveUp(false);
                owner.moveDown(false);
            }
            //face toward focused entity
            owner.aim().x = owner.focusedEntity().position().x;
            owner.aim().y = owner.focusedEntity().position().y;
            owner.doAttack(true);
            
            //forget focus if focused entity is out of view.
            if(CircleObject.distanceBetweenPoints(owner.focusedEntity().position(), owner.position()) > owner.viewRadius() * 1.5)
            {
                owner.focusedEntity(null);
                owner.doAttack(false);
            }
        }
        //if no focus, wander aimlessly
        else if(moveChangeTimer <= 0) {
            int moveChange = rand.nextInt(4);
            switch(moveChange){
                case 0: owner.moveLeft(!owner.moveLeft());break;
                case 1: owner.moveRight(!owner.moveRight());break;
                case 2: owner.moveUp(!owner.moveUp());break;
                case 3: owner.moveDown(!owner.moveDown());break;
            }
            moveChangeTimer = moveChangeDelay;
        }
         //check for nearby enemies to focus.
        else {
            owner.doAttack(false);
            boolean firstEntity = true;
            CircleEntity closestEntity = null;
            double distance;
            
            for(CircleEntity cE : (owner.team() == CircleLandUtility.MONSTER_SIDE) ? world.players() : world.monsters()) {
                if(cE.intersectsCircle(owner.position(), owner.viewRadius()))
                {
                    //default focus is first entity.
                    if(firstEntity){
                        closestEntity = cE;
                        distance = CircleObject.distanceBetweenPoints(cE.position(), owner.position());
                        firstEntity = false;
                        continue;
                    }
                    //else compare distance of other entity in viewRadius.
                    double cEDistance = distance = CircleObject.distanceBetweenPoints(cE.position(), owner.position());
                    //
                    if(cEDistance < distance){
                        closestEntity = cE;
                        distance = cEDistance;
                    }
                }
            }
            owner.focusedEntity(closestEntity);
        }
    }//end update
}
