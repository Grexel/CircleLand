/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Loot;

import java.awt.Color;
import java.io.File;
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
                        Color color = Color.getColor(elem.getElementsByTagName("color")
                                    .item(0).getTextContent(),Color.WHITE);
                        int minDamage = Integer.parseInt(
                            elem.getElementsByTagName("minDamage")
                                    .item(0).getTextContent());
                        int maxDamage = Integer.parseInt(
                            elem.getElementsByTagName("maxDamage")
                                    .item(0).getTextContent());
                        int pierce = Integer.parseInt(
                            elem.getElementsByTagName("pierce")
                                    .item(0).getTextContent());
                        //create diagram and add to list
                        WeaponDiagram diag = new WeaponDiagram(name, itemLevel,
                                weaponType, size, moveSpeed, attackSpeed,
                                attackLife, color, minDamage, maxDamage, pierce);
                        diagramList.addDiagram(diag);
                        System.out.println("weapon added");
                        
                    }
                    if(elem.getTagName().equalsIgnoreCase("armor")){
                        //parseXML for diagram details
                        int itemLevel = Integer.parseInt(
                                elem.getAttribute("level")); 
                        String armorType = "armor";
                        String name = elem.getElementsByTagName("name")
                                .item(0).getTextContent();
                        Color color = Color.getColor(elem.getElementsByTagName("color")
                                    .item(0).getTextContent(),Color.WHITE);
                        int minDefense = Integer.parseInt(
                            elem.getElementsByTagName("minDefense")
                                    .item(0).getTextContent());
                        int maxDefense = Integer.parseInt(
                            elem.getElementsByTagName("maxDefense")
                                    .item(0).getTextContent());
                        //create diagram and add to list
                        ArmorDiagram diag = new ArmorDiagram(name, itemLevel, 
                                armorType, color, minDefense, maxDefense);
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
}
