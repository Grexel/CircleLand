/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Classes;

import circleland.Skills.NecromancerSkills.*;
import circleland.CircleClass;
import circleland.CircleEntity;
import circleland.CircleMap;
import circleland.CircleSkill;
import circleland.Weapons.BiteWeapon;
import circleland.Weapons.BulletWeapon;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Skills: summon skeleton, summon golem, raise skeleton Ranged
 * boneshield boneblast, bonewall
 * Life sap - damage done to monster heals attack, Weaken - slow monsters and reduce damage, Thorns - damage done to others hurts self
 *
 * @author Jeff
 */
public class NecromancerCircle extends CircleClass{
    public NecromancerCircle(){
        super();
        color = Color.BLACK;
        outlineColor = Color.GRAY;
        addAllSkills();
        equippedSkill = new SummonSkeletonSkill();
        skills.add(new SummonGolemSkill());
        skills.add(new SummonSkeletonArcherSkill());
        skills.add(new BoneShieldSkill());
        skills.add(new BoneBlastSkill());
        skills.add(new BoneWallSkill());
        skills.add(new WeakenCurseSkill());
        skills.add(new LeechLifeCurseSkill());
        skills.add(new ThornsCurseSkill());
        
        equippedWeapon = new BulletWeapon();
    }
    public void addAllSkills(){
        allSkills.add(new SummonSkeletonSkill());
    }
}