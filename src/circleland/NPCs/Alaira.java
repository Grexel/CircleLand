/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.NPCs;

import circleland.*;
import circleland.Display.ChatBox;
import circleland.Display.MarketScreen;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * more NPC NAMES
 * http://www.namegenerator.biz/game-name-generator.php
 * Fremond  Kayde   Zuric
 * Tirath   Pas
 * Rayda    Weira
 * Mude     Mira
 * Ceb      Nera
 * @author Jeff
 */
public class Alaira extends CircleNPC{
    
    private Point2D.Double staticPosition;
    public void staticPosition(Point2D.Double m){staticPosition = m;}
    public Point2D.Double staticPosition(){return staticPosition;}
    
    public String welcomeString = "Welcome to Sanctuary. I am Alaira. Where are you from traveler?" + 
            " Feel free to relax. This is one of the last remaining safe havens from Mortuus in all of Circumfra." +
            " ";
    public String impQuestString = "The Imps have been growing in number. Day by day they become more fearless." +
            " Some have even started entering our portal and terrorizing the refugees before Lana can dispatch them." +
            " Please clear out the Imp Den for us and bring peace back to our weary lives.";
    public Alaira(){
        super();
        name = "Alaira";
        staticPosition = new Point2D.Double(200,200);
    }
    public void update(long deltaTime, CircleMap world){
        position.x = staticPosition.x;
        position.y = staticPosition.y;
        super.update(deltaTime, world);
        if(focusedEntity != null){
        aim.x = focusedEntity.position().x;
        aim.y = focusedEntity.position().y;
        }
        else{
            focusedEntity = world.player();
        }
    }
    public void onClick(CircleClass player, CircleMap world){
        if(!player.quests().AlairaIntroduction()){
            ChatBox cb = new ChatBox((int)position.x,(int)position.y - 50,welcomeString);
            world.chatBoxes().add(cb);
            player.quests().AlairaIntroduction(true);
        }
        else{
            Rectangle2D.Double mRect = new Rectangle2D.Double(300,300,200,300);
            MarketScreen mS = new MarketScreen(mRect,player);
            //mS.items().add(new FoilRapier());
            world.camera().marketScreen(mS);
            //player.inventory().add(new VerdunRapier());
            ChatBox cb = new ChatBox((int)position.x,(int)position.y - 50,impQuestString);
            world.chatBoxes().add(cb);
        }
    }
    //show textBox onclick
    //add onclick for entities
}
