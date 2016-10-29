/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainScreens;

import javax.swing.*;

/**
 *
 * @author Jeff
 */
public class MasterFrame extends JFrame{
    public WelcomeScreen welcome;
    public MasterScreen master;
    public NewPlayerScreen newPlayer;
    public MultiplayerScreen multiplayer;
    public JPanel currentScreen;
    public MasterFrame(){
        super("Chaotic Circle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcome = new WelcomeScreen();
        this.add(welcome);
        setContentPane(welcome);
        pack();
        setLocation(100, 100);
        setVisible(true);
    }
    public static void main(String[] args) {
        MasterFrame mF = new MasterFrame();
    }
}
