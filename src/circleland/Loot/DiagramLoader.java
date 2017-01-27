/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Loot;

import circleland.Affixes.*;
import circleland.CircleAffix;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
public class DiagramLoader {
    
    public static DiagramList loadDiagram(File itemFile){
        DiagramList diagramList = new DiagramList();
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(itemFile);
            
            //get document element and glean details from
            //set up necessary data holders
            Element documentElement = document.getDocumentElement();
            NodeList nodeList = document.getDocumentElement().getChildNodes();
            /*
<itemList name="ChaoticItems">
    <!-- Weapons -->
        <!-- Rapiers -->
        <weapon type="Rapier" level="1">
            <name>Sabre</name>
            <size>100</size>
            <moveSpeed>200</moveSpeed>
            <attackSpeed>500</attackSpeed>
            <attackLife>500</attackLife>
            <color>0x808080</color>
            <minDamage>3</minDamage>
            <maxDamage>7</maxDamage>
            <pierce>1</pierce>   
        </weapon>
*/
            //iterate over all top nodes
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    
                    //Add new Weapon diagram
                    if(elem.getTagName().equalsIgnoreCase("weapon")){
                        //parseXML for diagram details
                        int itemLevel = Integer.parseInt(
                                elem.getAttribute("level")); 
                        String weaponType = elem.getAttribute("type");
                        String name = elem.getElementsByTagName("name")
                                .item(0).getTextContent();
                        int rarity = Integer.parseInt(
                            elem.getElementsByTagName("rarity")
                                    .item(0).getTextContent());
                        int size = Integer.parseInt(
                            elem.getElementsByTagName("size")
                                    .item(0).getTextContent());
                        int moveSpeed = Integer.parseInt(
                            elem.getElementsByTagName("moveSpeed")
                                    .item(0).getTextContent());
                        int attackSpeed = Integer.parseInt(
                            elem.getElementsByTagName("attackSpeed")
                                    .item(0).getTextContent());
                        int attackLife = Integer.parseInt(
                            elem.getElementsByTagName("attackLife")
                                    .item(0).getTextContent());
                        Color color = Color.decode(elem.getElementsByTagName("color")
                                    .item(0).getTextContent());
                        int minDamage = Integer.parseInt(
                            elem.getElementsByTagName("minDamage")
                                    .item(0).getTextContent());
                        int maxDamage = Integer.parseInt(
                            elem.getElementsByTagName("maxDamage")
                                    .item(0).getTextContent());
                        int pierce = Integer.parseInt(
                            elem.getElementsByTagName("pierce")
                                    .item(0).getTextContent());
                        NodeList affixList = elem.getElementsByTagName("affix");
                        List<CircleAffix> affixes = new ArrayList<>();
                        if(affixList.getLength() > 0){
                            for (int k = 0; k < affixList.getLength(); k++) {
                                Node aNode = affixList.item(k);
                                if (aNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element aElem = (Element) aNode;
                                    String affixName = elem.getAttribute("name");
                                    int minVal = Integer.parseInt(
                                            elem.getAttribute("minValue")); 
                                    int maxVal = Integer.parseInt(
                                            elem.getAttribute("maxValue"));
                                    affixes.add(createAffix(affixName,minVal,maxVal));
                                }
                            }
                        }
                        //create diagram and add to list
                        WeaponDiagram diag = new WeaponDiagram(name, rarity, itemLevel,
                                weaponType, size, moveSpeed, attackSpeed,
                                attackLife, color, minDamage, maxDamage, pierce,affixes);
                        diagramList.addDiagram(diag);
                        System.out.println("weapon added");
                    }
                    
                    //Add armor diagram(Armor,Helmets,Boots,Gloves,Rings,Amulets)
                    if(elem.getTagName().equalsIgnoreCase("armor")){
                        //parseXML for diagram details
                        int itemLevel = Integer.parseInt(
                                elem.getAttribute("level")); 
                        String armorType = elem.getAttribute("type");
                        String name = elem.getElementsByTagName("name")
                                .item(0).getTextContent();
                        int rarity = Integer.parseInt(
                            elem.getElementsByTagName("rarity")
                                    .item(0).getTextContent());
                        Color color = Color.decode(elem.getElementsByTagName("color")
                                    .item(0).getTextContent());
                        int minDefense = Integer.parseInt(
                            elem.getElementsByTagName("minDefense")
                                    .item(0).getTextContent());
                        int maxDefense = Integer.parseInt(
                            elem.getElementsByTagName("maxDefense")
                                    .item(0).getTextContent());
                        //Affixes
                        NodeList affixList = elem.getElementsByTagName("affix");
                        List<CircleAffix> affixes = new ArrayList<>();
                        if(affixList.getLength() > 0){
                            for (int k = 0; k < affixList.getLength(); k++) {
                                Node aNode = affixList.item(k);
                                if (aNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element aElem = (Element) aNode;
                                    String affixName = elem.getAttribute("name");
                                    int minVal = Integer.parseInt(
                                            elem.getAttribute("minValue")); 
                                    int maxVal = Integer.parseInt(
                                            elem.getAttribute("maxValue"));
                                    affixes.add(createAffix(affixName,minVal,maxVal));
                                }
                            }
                        }
                        //create diagram and add to list
                        ArmorDiagram diag = new ArmorDiagram(name,rarity, itemLevel, 
                                armorType, color, minDefense, maxDefense,affixes);
                        diagramList.addDiagram(diag);
                        System.out.println("armor added");
                    }
                }
            }
        }catch(Exception e){
            System.out.println("Error loading Diagram list xml file: " + itemFile.getName());
            System.out.println(e.getMessage());
        }
        return diagramList;
    }
    public static CircleAffix createAffix(String name, int min, int max){
        CircleAffix affix;
        if(name.equalsIgnoreCase("Strength")){
            affix = new StrengthAffix(1);
            int value = new Random().nextInt(max - min + 1) + min;
        }
        else if(name.equalsIgnoreCase("Armor")){
            affix = new ArmorAffix(1);
            int value = new Random().nextInt(max - min + 1) + min;
        }
        else if(name.equalsIgnoreCase("Dexterity")){
            affix = new DexterityAffix(1);
            int value = new Random().nextInt(max - min + 1) + min;
        }
        else if(name.equalsIgnoreCase("Fortitude")){
            affix = new FortitudeAffix(1);
            int value = new Random().nextInt(max - min + 1) + min;
        }
        else if(name.equalsIgnoreCase("Magic")){
            affix = new MagicAffix(1);
            int value = new Random().nextInt(max - min + 1) + min;
        }
        else if(name.equalsIgnoreCase("MinDamage")){
            affix = new MinDamageAffix(1);
            int value = new Random().nextInt(max - min + 1) + min;
        }
        else{
            affix = new StrengthAffix(1);
            int value = new Random().nextInt(max - min + 1) + min;            
        }
        return affix;
    }
}
