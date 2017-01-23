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
    public static void saveCharacter(CircleClass circle){
        File newFile = new File(savePath + circle.name());
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
            writer.append(circle.name()); writer.newLine();
            writer.append(circle.className()); writer.newLine();
        }catch(Exception e){
            e.printStackTrace(System.err);
        }
    }
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
