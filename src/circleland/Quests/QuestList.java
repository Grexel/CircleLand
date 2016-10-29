/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Quests;

/**
 *
 * @author Jeff
 */
public class QuestList {
    private boolean introduction;
    public boolean introduction(){return introduction;}
    public void introduction(boolean m){introduction = m;}
    private boolean AlairaIntroduction;
    public boolean AlairaIntroduction(){return AlairaIntroduction;}
    public void AlairaIntroduction(boolean m){AlairaIntroduction = m;}
    public QuestList(){
        introduction = false;
        AlairaIntroduction = false;
    }
}