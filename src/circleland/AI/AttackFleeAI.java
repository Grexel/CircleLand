/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.AI;

import circleland.CircleEntity;
import circleland.CircleLandAI;
import static circleland.CircleLandAI.rand;
import circleland.CircleLandUtility;
import circleland.CircleMap;
import circleland.CircleObject;

/**
 *
 * @author Jeff
 */
public class AttackFleeAI extends CircleLandAI{
    private int moveChangeTimer;
    private int moveChangeDelay;
    private int fleeTimer;
    private int fleeDelay;
    private int stayAwayRange;
    public AttackFleeAI(){
        moveChangeTimer = 0;
        moveChangeDelay = 500;
        fleeTimer = 0;
        fleeDelay = 1000;
        stayAwayRange = 25;
    }
    @Override
    public void update(long deltaTime, CircleEntity owner, CircleMap world) {
        moveChangeTimer -= deltaTime;
        fleeTimer -= deltaTime;
        //move towards enemy.
        if(owner.focusedEntity() != null) {
            //if not fleeing, move toward entity
            if(fleeTimer <=0){
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
            }
            //fleeing, move away from entity
            if(fleeTimer > 0){
                if (owner.focusedEntity().position().x > owner.position().x){
                    owner.moveRight(false);
                    owner.moveLeft(true);
                } else if(owner.focusedEntity().position().x < owner.position().x) {
                    owner.moveLeft(false);
                    owner.moveRight(true);
                } else {
                    owner.moveLeft(false);
                    owner.moveRight(false);
                }
                //move up and down
                if (owner.focusedEntity().position().y > owner.position().y){
                    owner.moveDown(false);
                    owner.moveUp(true);
                } else if(owner.focusedEntity().position().y < owner.position().y) {
                    owner.moveUp(false);
                    owner.moveDown(true);
                } else {
                    owner.moveUp(false);
                    owner.moveDown(false);
                }
            }
            //face toward focused entity
            owner.aim().x = owner.focusedEntity().position().x;
            owner.aim().y = owner.focusedEntity().position().y;
            
            //if fleeing, don't attack
            if(fleeTimer > 0)
                owner.doAttack(false);
            
            //but if in range of focusedEntity do attack
            if(Math.abs(owner.focusedEntity().position().y - owner.position().y) < stayAwayRange){
                if(Math.abs(owner.focusedEntity().position().x - owner.position().x) < stayAwayRange){
                    if(fleeTimer < 0){
                        owner.doAttack(true);
                        fleeTimer = fleeDelay;
                    }
                }
            }
            //forget focus if focused entity is out of view.
            if(CircleObject.distanceBetweenPoints(owner.focusedEntity().position(), owner.position()) > owner.viewRadius() * 1.5)
            {
                owner.focusedEntity(null);
                owner.doAttack(false);
            }
        }
        //if no focus, wander aimlessly
        /*
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
        */
         //check for nearby enemies to focus.
        else {
            owner.moveLeft(false);
            owner.moveRight(false);
            owner.moveUp(false);
            owner.moveDown(false);
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

