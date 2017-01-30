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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
            CircleClass newCircle = xmlCharacter(f);
            //CircleClass newCircle = loadCharacter(f);
            if(newCircle != null){
                circles.add(newCircle);
            }
        }
        return circles;
    }
    
    //Save and load Characters
    public static void saveCharacter(CircleClass circle){
        System.out.println("Saving " + circle.name());
        File newFile = new File(savePath + circle.name());
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
            writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); writer.newLine();
            writer.append("<character>"); writer.newLine();
            
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
            writer.append("</stats>"); writer.newLine();
            writer.append("</character>"); writer.newLine();
            writer.flush();
            writer.close();
        }catch(Exception e){
            e.printStackTrace(System.err);
        }
    }
    public static CircleClass xmlCharacter(File file){
        CircleClass player = loadClass("Warrior");
        player.name("Null");
        try{
            //load an xml file and parse
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            
            //get document element and glean details from
            //set up necessary data holders
            Element documentElement = document.getDocumentElement();
            NodeList nodeList = document.getDocumentElement().getChildNodes();
            String charName = "";
            String charClass = "";
            
            System.out.println("Loading XML nodes");
            //iterate over all top nodes
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    System.out.println("elem name= " + elem.getTagName());
                    if(elem.getTagName().equalsIgnoreCase("name")){
                        charName = elem.getTextContent();
                    }
                    if(elem.getTagName().equalsIgnoreCase("class")){
                        player = loadClass(elem.getTextContent());
                    }
                    if(elem.getTagName().equalsIgnoreCase("stats")){
                        loadStats(player, elem);
                    }
                }
            }
            player.name(charName);
        }catch(Exception e){
            System.out.println("Error loading xml Character file: " + file.getName());
            System.out.println(e.getMessage());
        }
        return player;
    }
    public static void loadStats(CircleClass circle, Element elem){
            int level = Integer.parseInt(elem.getElementsByTagName("level")
                    .item(0).getTextContent());
            circle.level(level);
            int experience = Integer.parseInt(
                elem.getElementsByTagName("experience")
                        .item(0).getTextContent());
            circle.experience(experience);
            int nextExperience = Integer.parseInt(
                elem.getElementsByTagName("nextLevelExperience")
                        .item(0).getTextContent());
            circle.experienceToNextLevel(nextExperience);
            int gold = Integer.parseInt(
                elem.getElementsByTagName("gold")
                        .item(0).getTextContent());
            circle.gold(gold);
            int statPoints = Integer.parseInt(
                elem.getElementsByTagName("statPoints")
                        .item(0).getTextContent());
            circle.statPoints(statPoints);
            int skillPoints = Integer.parseInt(
                elem.getElementsByTagName("skillPoints")
                        .item(0).getTextContent());
            circle.skillPoints(skillPoints);
            int baseStrength = Integer.parseInt(
                elem.getElementsByTagName("baseStrength")
                        .item(0).getTextContent());
            circle.baseStrength(baseStrength);
            int baseDexterity = Integer.parseInt(
                elem.getElementsByTagName("baseDexterity")
                        .item(0).getTextContent());
            circle.baseDexterity(baseDexterity);
            int baseMagic = Integer.parseInt(
                elem.getElementsByTagName("baseMagic")
                        .item(0).getTextContent());
            circle.baseMagic(baseMagic);
            int baseFortitude = Integer.parseInt(
                elem.getElementsByTagName("baseFortitude")
                        .item(0).getTextContent());
            circle.baseFortitude(baseFortitude);
            int basePrecision = Integer.parseInt(
                elem.getElementsByTagName("basePrecision")
                        .item(0).getTextContent());
            circle.basePrecision(basePrecision);
            double baseMagicFind = Double.parseDouble(
                elem.getElementsByTagName("baseMagicFind")
                        .item(0).getTextContent());
            circle.baseMagicFind(baseMagicFind);
            double baseMaxHealth = Double.parseDouble(
                elem.getElementsByTagName("baseMaxHealth")
                        .item(0).getTextContent());
            circle.baseMaxHealth(baseMaxHealth);
            double baseHealthRegeneration = Double.parseDouble(
                elem.getElementsByTagName("baseHealthRegeneration")
                        .item(0).getTextContent());
            circle.baseHealthRegeneration(baseHealthRegeneration);
            double baseMaxMana = Double.parseDouble(
                elem.getElementsByTagName("baseMaxMana")
                        .item(0).getTextContent());
            circle.baseMaxMana(baseMaxMana);
            double baseManaRegeneration = Double.parseDouble(
                elem.getElementsByTagName("baseManaRegeneration")
                        .item(0).getTextContent());
            circle.baseManaRegeneration(baseManaRegeneration);
            double baseMoveSpeed = Double.parseDouble(
                elem.getElementsByTagName("baseMoveSpeed")
                        .item(0).getTextContent());
            circle.baseMoveSpeed(baseMoveSpeed);
            double baseAttackSpeed = Double.parseDouble(
                elem.getElementsByTagName("baseAttackSpeed")
                        .item(0).getTextContent());
            circle.baseAttackSpeed(baseAttackSpeed);
            double baseCastSpeed = Double.parseDouble(
                elem.getElementsByTagName("baseCastSpeed")
                        .item(0).getTextContent());
            circle.baseCastSpeed(baseCastSpeed);
        
    }
    public static CircleClass loadClass(String className){
            if(className.equalsIgnoreCase("Warrior")){
                return new WarriorCircle();
            }
            else if(className.equalsIgnoreCase("Mage")){
                return new NecromancerCircle();
            }
            else if(className.equalsIgnoreCase("Archer")){
                return new WarriorCircle();
            }
            else if(className.equalsIgnoreCase("Demon")){
                return new WarriorCircle();
            }
            else if(className.equalsIgnoreCase("Necromancer")){
                return new NecromancerCircle();
            }
            else if(className.equalsIgnoreCase("Nymph")){
                return new WarriorCircle();
            }
            else{
                return  new WarriorCircle();
            }
        
    }
    public static CircleClass loadCharacter(File file){
        try{
            Scanner sc = new Scanner(file);
            CircleClass circle;
            String name = sc.nextLine();
            String circleClass = sc.nextLine();
            circle = loadClass(circleClass);
            circle.name(name);
            return circle;
        }catch(Exception e){}
        return null;
    }
}
