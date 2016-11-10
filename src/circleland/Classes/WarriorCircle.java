/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Classes;

import circleland.Skills.WarriorSkills.RallySkill;
import circleland.Skills.WarriorSkills.ChargeSkill;
import circleland.Skills.WarriorSkills.StunSkill;
import circleland.Skills.WarriorSkills.CorpseLootSkill;
import circleland.Skills.WarriorSkills.MetalSkinSkill;
import circleland.Skills.WarriorSkills.TauntSkill;
import circleland.Skills.WarriorSkills.MeleeMasterySkill;
import circleland.Skills.WarriorSkills.FortitudeSkill;
import circleland.Skills.WarriorSkills.BashSkill;
import circleland.CircleEntity;
import circleland.CircleMap;
import circleland.*;
import circleland.Weapons.*;
import circleland.Weapons.Guns.*;
import circleland.Weapons.Swords.*;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Jeff
 */
public class WarriorCircle extends CircleClass{
    public WarriorCircle(){
        super();
        baseStrength = 30;
        baseDexterity = 20;
        baseMagic = 10;
        baseFortitude = 20;
        statPoints = 100;
        color = Color.GRAY;
        addAllSkills();
        equippedSkill = new StunSkill();
        skills.add(new BashSkill());
        skills.add(new ChargeSkill());
        skills.add(new TauntSkill());
        skills.add(new RallySkill());
        skills.add(new CorpseLootSkill());
        skills.add(new FortitudeSkill());
        skills.add(new MeleeMasterySkill());
        skills.add(new MetalSkinSkill());
        equippedWeapon = new SWShieldBullet();
        inventory.add(new HandCannonBullet());
        inventory.add(new FlintPistolBullet());
        inventory.add(new RevolverBullet());
        inventory.add(new GlockBullet());
        inventory.add(new Colt45Bullet());
    }
    public void addAllSkills(){
        allSkills.add(new BashSkill());
        allSkills.add(new ChargeSkill());
        allSkills.add(new StunSkill());
        allSkills.add(new TauntSkill());
        allSkills.add(new RallySkill());
        allSkills.add(new CorpseLootSkill());
        allSkills.add(new FortitudeSkill());
        allSkills.add(new MeleeMasterySkill());
        allSkills.add(new MetalSkinSkill());
    }
}
