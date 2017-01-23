/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainScreens;

import circleland.CircleClass;
import circleland.CircleLand;
import circleland.CircleMap;
import circleland.CircleMouse;
import circleland.CircleSummoned;
import circleland.Classes.WarriorCircle;
import circleland.Display.Camera;
import circleland.DrawingPanel;
import static circleland.DrawingPanel.PANEL_HEIGHT;
import static circleland.DrawingPanel.PANEL_WIDTH;
import circleland.LookupAtan2;
import circleland.MapLoader;
import circleland.MapManager;
import circleland.SoundManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import javax.swing.*;

/**
 *
 * @author Jeff
 */
public class MasterFrame extends JFrame implements MouseListener, MouseMotionListener, KeyListener,
        MouseWheelListener{
    public DrawingPanel currentScreen;
    public GameStateManager gameStateManager;
    public InputStore inputStore;
    
    private Color bgColor = Color.BLACK;
    private double frames, ms,fps;
    private double msToDraw,msDrawHolder;
    
    
    public MasterFrame(){
        super("Chaotic Circle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        currentScreen = new DrawingPanel();
        currentScreen.setDoubleBuffered(true);
        currentScreen.setFocusable(true);
        currentScreen.setIgnoreRepaint(true);
        currentScreen.grabFocus();
        currentScreen.addMouseListener(this);
        currentScreen.addMouseMotionListener(this);
        currentScreen.addMouseWheelListener(this);
        currentScreen.addKeyListener(this);
        //currentScreen.addComponentListener(this);
        
        this.add(currentScreen);
        setContentPane(currentScreen);
        pack();
        setLocation(100, 100);
        setVisible(true);
        
        gameStateManager = new GameStateManager();
        gameStateManager.pushState(new PlayerSelectState(gameStateManager));
        //gameStateManager.pushState(new CircleLandState(gameStateManager));
        inputStore = new InputStore();
    }
    public static void main(String[] args) {
        MasterFrame mF = new MasterFrame();
        
        mF.run();
    }
    
    public void run()
    {
        initialize();
        long currentMS = System.currentTimeMillis();
        long prevMS = System.currentTimeMillis();
        
        while(gameStateManager.isRunning())
        {
            currentMS = System.currentTimeMillis();
            gameStateManager.handleInput(inputStore);
            gameStateManager.update(currentMS - prevMS);
            ms += currentMS - prevMS;
            msDrawHolder += currentMS - prevMS;
            frames++;
            if(ms >= 1000){
                fps = frames/ms*1000;
                frames = 0;
                ms -= 1000;
            }
            if(msDrawHolder > msToDraw){
                SoundManager.playSounds();
                //currentScreen.clearScreen(bgColor);
                gameStateManager.draw(currentScreen.getGraphics2D());
                currentScreen.getGraphics2D().drawString("" + fps, 10, 10);
                currentScreen.draw();
                repaint();
                msDrawHolder -= msToDraw;
            }
            prevMS = currentMS;
        }
        //end game and close
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
    public void initialize()
    {
        //initializeSounds
        SoundManager.initializeSoundManager();
        //initialize Lookup atan2 table
        //Math.atan2 takes a boatload of cpu, let's try a lookup table to speed it up
        LookupAtan2.initialize();
        
        frames = 0;
        ms = 1;
        msToDraw = 15;
        msDrawHolder = 0;
        
        //set key bindings obj1.getInputMap(IFW).put(KeyStroke.getKeyStroke("UP"), MOVE_UP);
        int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
        currentScreen.getInputMap(IFW).put(KeyStroke.getKeyStroke("A"), "aPress");
        currentScreen.getActionMap().put("aPress", new aPressed());
        currentScreen.getInputMap(IFW).put(KeyStroke.getKeyStroke("released A"), "aRelease");
        currentScreen.getActionMap().put("aRelease", new aReleased());
        currentScreen.getInputMap(IFW).put(KeyStroke.getKeyStroke("C"), "cPress");
        currentScreen.getActionMap().put("cPress", new cPressed());
        currentScreen.getInputMap(IFW).put(KeyStroke.getKeyStroke("released C"), "cRelease");
        currentScreen.getActionMap().put("cRelease", new cReleased());
        currentScreen.getInputMap(IFW).put(KeyStroke.getKeyStroke("D"), "dPress");
        currentScreen.getActionMap().put("dPress", new dPressed());
        currentScreen.getInputMap(IFW).put(KeyStroke.getKeyStroke("released D"), "dRelease");
        currentScreen.getActionMap().put("dRelease", new dReleased());
        currentScreen.getInputMap(IFW).put(KeyStroke.getKeyStroke("I"), "iPress");
        currentScreen.getActionMap().put("iPress", new iPressed());
        currentScreen.getInputMap(IFW).put(KeyStroke.getKeyStroke("released I"), "iRelease");
        currentScreen.getActionMap().put("iRelease", new iReleased());
        currentScreen.getInputMap(IFW).put(KeyStroke.getKeyStroke("K"), "kPress");
        currentScreen.getActionMap().put("kPress", new kPressed());
        currentScreen.getInputMap(IFW).put(KeyStroke.getKeyStroke("released K"), "kRelease");
        currentScreen.getActionMap().put("kRelease", new kReleased());
        currentScreen.getInputMap(IFW).put(KeyStroke.getKeyStroke("W"), "wPress");
        currentScreen.getActionMap().put("wPress", new wPressed());
        currentScreen.getInputMap(IFW).put(KeyStroke.getKeyStroke("released W"), "wRelease");
        currentScreen.getActionMap().put("wRelease", new wReleased());
        currentScreen.getInputMap(IFW).put(KeyStroke.getKeyStroke("S"), "sPress");
        currentScreen.getActionMap().put("sPress", new sPressed());
        currentScreen.getInputMap(IFW).put(KeyStroke.getKeyStroke("released S"), "sRelease");
        currentScreen.getActionMap().put("sRelease", new sReleased());
        currentScreen.getInputMap(IFW).put(KeyStroke.getKeyStroke("P"), "pPress");
        currentScreen.getActionMap().put("pPress", new pPressed());
        currentScreen.getInputMap(IFW).put(KeyStroke.getKeyStroke("released P"), "pRelease");
        currentScreen.getActionMap().put("pRelease", new pReleased());
        currentScreen.getInputMap(IFW).put(KeyStroke.getKeyStroke("Space"), "spacePress");
        currentScreen.getActionMap().put("spacePress", new spacePressed());
        currentScreen.getInputMap(IFW).put(KeyStroke.getKeyStroke("released Space"), "spaceRelease");
        currentScreen.getActionMap().put("spaceRelease", new spaceReleased());
    }
    
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            inputStore.setLeftMouseDown(true);
        if(e.getButton() == MouseEvent.BUTTON3)
            inputStore.setRightMouseDown(true);
    }
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            inputStore.setLeftMouseDown(false);
        if(e.getButton() == MouseEvent.BUTTON3)
            inputStore.setRightMouseDown(false);
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    public void mouseClicked(MouseEvent e) {
    }
    public void mouseMoved(MouseEvent e) {
        inputStore.getMousePosition().x = e.getPoint().x;
        inputStore.getMousePosition().y = e.getPoint().y;
    }
    public void mouseDragged(MouseEvent e) {
        inputStore.getMousePosition().x = e.getPoint().x;
        inputStore.getMousePosition().y = e.getPoint().y;
    }    
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        inputStore.setMouseWheelScrolled(e.getWheelRotation());
    }
    public void keyTyped(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            gameStateManager.quit();
    }
    /** Handle the key-pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A)
            inputStore.setaPressed(true);
        if(e.getKeyCode() == KeyEvent.VK_C)
            inputStore.setcPressed(true);
        if(e.getKeyCode() == KeyEvent.VK_D)
            inputStore.setdPressed(true);
        if(e.getKeyCode() == KeyEvent.VK_I)
            inputStore.setiPressed(true);
        if(e.getKeyCode() == KeyEvent.VK_K)
            inputStore.setkPressed(true);
        if(e.getKeyCode() == KeyEvent.VK_W)
            inputStore.setwPressed(true);
        if(e.getKeyCode() == KeyEvent.VK_S)
            inputStore.setsPressed(true);
        if(e.getKeyCode() == KeyEvent.VK_P)
            inputStore.setpPressed(true);
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
            inputStore.setSpacePressed(true);
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            gameStateManager.popState();
    }
    /** Handle the key-released event from the text field. */
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A)
            inputStore.setaPressed(false);
        if(e.getKeyCode() == KeyEvent.VK_C)
            inputStore.setcPressed(false);
        if(e.getKeyCode() == KeyEvent.VK_D)
            inputStore.setdPressed(false);
        if(e.getKeyCode() == KeyEvent.VK_I)
            inputStore.setiPressed(false);
        if(e.getKeyCode() == KeyEvent.VK_K)
            inputStore.setkPressed(false);
        if(e.getKeyCode() == KeyEvent.VK_W)
            inputStore.setwPressed(false);
        if(e.getKeyCode() == KeyEvent.VK_S)
            inputStore.setsPressed(false);
        if(e.getKeyCode() == KeyEvent.VK_P)
            inputStore.setpPressed(false);
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
            inputStore.setSpacePressed(false);
    }

    private class aPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            inputStore.setaPressed(true);
        }
    }
    private class aReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            inputStore.setaPressed(false);
        }
    }
    private class cPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            inputStore.setcPressed(true);
        }
    }
    private class cReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            inputStore.setcPressed(false);
        }
    }
    private class dPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            inputStore.setdPressed(true);
        }
    }
    private class dReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            inputStore.setdPressed(false);
        }
    }
    private class iPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            inputStore.setiPressed(true);
        }
    }
    private class iReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            inputStore.setiPressed(false);
        }
    }
    private class kPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            inputStore.setkPressed(true);
        }
    }
    private class kReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            inputStore.setkPressed(false);
        }
    }
    private class wPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            inputStore.setwPressed(true);
        }
    }
    private class wReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            inputStore.setwPressed(false);
        }
    }
    private class sPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            inputStore.setsPressed(true);
        }
    }
    private class sReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e){
            inputStore.setsPressed(false);
        }
    }
    private class pPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            inputStore.setpPressed(true);
        }
    }
    private class pReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            inputStore.setpPressed(false);
        }
    }
    private class spacePressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            inputStore.setSpacePressed(true);
        }
    }
    private class spaceReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            inputStore.setSpacePressed(false);
        }
    }
}
