/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import circleland.Classes.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jeff
 */
public class CircleLoader {
    public static final String savePath = "saves/";
    /**
    *   Design patter for player save/load
    * <name>theLegend27</name>
    * <class>Warrior</class>
    * <skills>
    *   <skill name="Bash">1</skill>
    *   <skill name="Stun">1</skill>
    * </skills>
    * <stats>
    *   <level>5</level>
    *   <experience>2750</experience>
    *   <nextLevelExperience>6250</nextLevelExperience>
    *   <gold>250</gold>
    *   <statPoints>5</statPoints>
    *   <skillPoints>1</skillPoints>
    *   <baseMagicFind>0.05</baseMagicFind>
    *   <baseMaxHealth>100</baseMaxHealth>
    *   <baseMaxMana>100</baseMaxMana>
    *   <baseStrength>5</baseStrength>
    *   <baseDexterity>5</baseDexterity>
    *   <baseMagic>5</baseMagic>
    *   <baseFortitude>5</baseFortitude>
    *   <baseHealthRegeneration>5</baseHealthRegeneration>
    *   <baseMaxMana>5</baseMaxMana>
    *   <baseManaRegeneration>5</baseManaRegeneration>
    *   <baseMoveSpeed>5</baseMoveSpeed>
    *   <baseAttackSpeed>>5</baseAttackSpeed>
    *   <baseCastSpeed>5</baseCastSpeed>
    *   //no need<baseMinDamage>5</baseMinDamage>
    *   //no need<baseMaxDamage>5</baseMaxDamage>
    *   //need to refactor out<baseAttackDamage>5</baseAttackDamage>
    *   //need to refactor out<baseMagicDamage>5</baseMagicDamage>
    *   <baseAttackDefense>5</baseAttackDefense>
    *   <baseMagicDefense>5</baseMagicDefense>
    *   <basePrecision>5</basePrecision>
    * </stats>
    * <inventory>
    *    <weapon type="Rapier" level="1" equipped="true">
    *       <name>Sabre</name>
    *       <size>100</size>
    *       <moveSpeed>200</moveSpeed>
    *       <attackSpeed>500</attackSpeed>
    *       <attackLife>500</attackLife>
    *       <color>0x808080</color>
    *       <minDamage>3</minDamage>
    *       <maxDamage>7</maxDamage>
    *       <pierce>1</pierce>
    *   </weapon>
    *    <weapon type="Rapier" level="1" equipped="true">
    *       <name>Sabre</name>
    *       <size>100</size>
    *       <moveSpeed>200</moveSpeed>
    *       <attackSpeed>500</attackSpeed>
    *       <attackLife>500</attackLife>
    *       <color>0x808080</color>
    *       <minDamage>3</minDamage>
    *       <maxDamage>7</maxDamage>
    *       <pierce>1</pierce>
    *   </weapon>
    * </inventory>
    */
    
    public static List<CircleClass> listCharacters(){
        ArrayList<CircleClass> circles = new ArrayList<>();
        File directory = new File(savePath);
        for(File f : directory.listFiles()){
            CircleClass newCircle = loadCharacter(f);
            if(newCircle != null){
                circles.add(newCircle);
            }
        }
        return circles;
    }
    
    //Save and load Characters
    public static void saveCharacter(CircleClass circle){
        File newFile = new File(savePath + circle.name());
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
            writer.append("<name>" + circle.name() + "</name>"); writer.newLine();
            writer.append("<class>" + circle.className() + "</class>"); writer.newLine();
            
            writer.append("<stats>"); writer.newLine();
            writer.append("<level>" + circle.level() + "</level>"); writer.newLine();
            writer.append("<experience>" + circle.experience()+ "</experience>"); writer.newLine();
            writer.append("<nextLevelExperience>" + circle.experienceToNextLevel()+ "</nextLevelExperience>"); writer.newLine();
            writer.append("<gold>" + circle.gold() + "</gold>"); writer.newLine();
            writer.append("<statPoints>" + circle.statPoints() + "</statPoints>"); writer.newLine();
            writer.append("<skillPoints>" + circle.skillPoints() + "</skillPoints>"); writer.newLine();
            writer.append("<baseMagicFind>" + circle.baseMagicFind() + "</baseMagicFind>"); writer.newLine();
            writer.append("<baseMaxHealth>" + circle.baseMaxHealth() + "</baseMaxHealth>"); writer.newLine();
            writer.append("<baseHealthRegeneration>" + circle.baseHealthRegeneration() + "</baseHealthRegeneration>"); writer.newLine();
            writer.append("<baseMaxMana>" + circle.baseMaxMana() + "</baseMaxMana>"); writer.newLine();
            writer.append("<baseManaRegeneration>" + circle.baseManaRegeneration() + "</baseManaRegeneration>"); writer.newLine();
            writer.append("<baseStrength>" + circle.baseStrength() + "</baseStrength>"); writer.newLine();
            writer.append("<baseDexterity>" + circle.baseDexterity() + "</baseDexterity>"); writer.newLine();
            writer.append("<baseMagic>" + circle.baseMagic() + "</baseMagic>"); writer.newLine();
            writer.append("<baseFortitude>" + circle.baseFortitude() + "</baseFortitude>"); writer.newLine();
            writer.append("<baseManaRegeneration>" + circle.baseManaRegeneration() + "</baseManaRegeneration>"); writer.newLine();
            writer.append("<baseMoveSpeed>" + circle.baseMoveSpeed() + "</baseMoveSpeed>"); writer.newLine();
            writer.append("<baseAttackSpeed>" + circle.baseAttackSpeed() + "</baseAttackSpeed>"); writer.newLine();
            writer.append("<baseCastSpeed>" + circle.baseCastSpeed() + "</baseCastSpeed>"); writer.newLine();
            writer.append("<basePrecision>" + circle.basePrecision() + "</basePrecision>"); writer.newLine();
            writer.append("<class>" + circle.className() + "</class>"); writer.newLine();
            writer.append("<class>" + circle.className() + "</class>"); writer.newLine();
            writer.append("<class>" + circle.className() + "</class>"); writer.newLine();
            writer.append("</stats>"); writer.newLine();
            
        }catch(Exception e){
            e.printStackTrace(System.err);
        }
    }
    public static CircleClass loadCharacter(File file){
        try{
            Scanner sc = new Scanner(file);
            CircleClass circle;
            String name = sc.nextLine();
            String circleClass = sc.nextLine();
            if(circleClass.equalsIgnoreCase("Warrior")){
                circle = new WarriorCircle();
            }
            else if(circleClass.equalsIgnoreCase("Mage")){
                circle = new NecromancerCircle();
            }
            else if(circleClass.equalsIgnoreCase("Archer")){
                circle = new WarriorCircle();
            }
            else if(circleClass.equalsIgnoreCase("Demon")){
                circle = new WarriorCircle();
            }
            else if(circleClass.equalsIgnoreCase("Necromancer")){
                circle = new NecromancerCircle();
            }
            else if(circleClass.equalsIgnoreCase("Nymph")){
                circle = new WarriorCircle();
            }
            else{
                circle = new WarriorCircle();
            }
            circle.name(name);
            return circle;
        }catch(Exception e){}
        return null;
    }
}
