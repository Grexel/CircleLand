/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import circleland.Display.Camera;
import circleland.Classes.NecromancerCircle;
import circleland.Classes.WarriorCircle;
import circleland.Items.Portal;
import circleland.Maps.Act1HomeMap;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.geom.*;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.Timer;


/**
 *
 * @author Jeff
 */
public class CircleLand extends DrawingPanel implements MouseListener, MouseMotionListener, KeyListener,
        MouseWheelListener{

    private Color bgColor = Color.BLACK;
    private boolean isPlaying = true; 
    private boolean leftMouseDown = false, lastLeftMouseDown = false, wPressed = false, aPressed = false, sPressed = false,
            dPressed = false,spacePressed = false,lastSpacePressed = false, rightMouseDown = false, lastRightMouseDown = false;
    private boolean cPressed = false,lastCPressed = false;
    private boolean iPressed = false,lastIPressed = false;
    private boolean kPressed = false,lastKPressed = false;
    private boolean pPressed = false,lastPPressed = false;
    private int mouseWheelScrolled = 0;
    private Point2D.Double mousePosition;
    private double frames, ms,fps;
    private double msToDraw,msDrawHolder;
    
    private CircleMap currentMap;
    private Camera camera;
    private CircleMouse mouse;
    private CircleClass player;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CircleLand world = new CircleLand();
        world.setDoubleBuffered(true);
        world.setFocusable(true);
        world.setIgnoreRepaint(true);
        world.grabFocus();
        world.addMouseListener(world);
        world.addMouseMotionListener(world);
        world.addMouseWheelListener(world);
        world.addKeyListener(world);
        world.addComponentListener(world);
        world.run();
    }
    
    public CircleLand()
    {
        super();
        setMyPanelColor(bgColor);
        offscreen.setFont(new Font("Courier", Font.PLAIN, 12));
        offscreen.setColor(Color.red);
    }
    public void run()
    {
        initialize();
        long currentMS = System.currentTimeMillis();
        long prevMS = System.currentTimeMillis();
        
        while(isPlaying)
        {
            currentMS = System.currentTimeMillis();
            handleInput();
            updateGame(currentMS - prevMS);
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
                drawGame();
                msDrawHolder -= msToDraw;
            }
            if(currentMap.switchToMap() != null)
            {
                switchMap();
            }
            prevMS = currentMS;
        }
        //end game and close
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
    public void initialize()
    {
        //initializeSounds
        SoundManager.initializeSoundManager();
        MapLoader mL = new MapLoader();
        //mL.loadMap("images/Act1MapData.xml");
        
        //initialize Lookup atan2 table
        //Math.atan2 takes a boatload of cpu, let's try a lookup table to speed it up
        LookupAtan2.initialize();
        
        frames = 0;
        ms = 1;
        msToDraw = 15;
        msDrawHolder = 0;
        player = new WarriorCircle();
        //player = new NecromancerCircle();
        camera = new Camera(0,0,PANEL_WIDTH,PANEL_HEIGHT, player);
        mouse = new CircleMouse(camera,player);
        camera.mouse(mouse);
        mousePosition = new Point2D.Double(0,0);
        //
        currentMap = mL.enterMap("images/Act1.xml", camera, mouse, player);
        currentMap.name("Act1");
        //currentMap = new Act1HomeMap(camera, mouse,player,1000,1000);
        
        //set key bindings obj1.getInputMap(IFW).put(KeyStroke.getKeyStroke("UP"), MOVE_UP);
        int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("A"), "aPress");
        this.getActionMap().put("aPress", new aPressed());
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("released A"), "aRelease");
        this.getActionMap().put("aRelease", new aReleased());
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("C"), "cPress");
        this.getActionMap().put("cPress", new cPressed());
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("released C"), "cRelease");
        this.getActionMap().put("cRelease", new cReleased());
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("D"), "dPress");
        this.getActionMap().put("dPress", new dPressed());
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("released D"), "dRelease");
        this.getActionMap().put("dRelease", new dReleased());
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("I"), "iPress");
        this.getActionMap().put("iPress", new iPressed());
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("released I"), "iRelease");
        this.getActionMap().put("iRelease", new iReleased());
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("K"), "kPress");
        this.getActionMap().put("kPress", new kPressed());
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("released K"), "kRelease");
        this.getActionMap().put("kRelease", new kReleased());
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("W"), "wPress");
        this.getActionMap().put("wPress", new wPressed());
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("released W"), "wRelease");
        this.getActionMap().put("wRelease", new wReleased());
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("S"), "sPress");
        this.getActionMap().put("sPress", new sPressed());
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("released S"), "sRelease");
        this.getActionMap().put("sRelease", new sReleased());
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("P"), "pPress");
        this.getActionMap().put("pPress", new pPressed());
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("released P"), "pRelease");
        this.getActionMap().put("pRelease", new pReleased());
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("Space"), "spacePress");
        this.getActionMap().put("spacePress", new spacePressed());
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("released Space"), "spaceRelease");
        this.getActionMap().put("spaceRelease", new spaceReleased());
    }
    public void handleInput()
    {
        if(wPressed){player.moveUp(true);}
            else {player.moveUp(false);}
        if(aPressed){player.moveLeft(true);}
            else {player.moveLeft(false);}
        if(sPressed){player.moveDown(true);}
            else {player.moveDown(false);}
        if(dPressed){player.moveRight(true);}
            else {player.moveRight(false);}
        if(leftMouseDown){player.doAttack(true);}
            else {player.doAttack(false);}
        if(rightMouseDown){player.doSkill(true);}
            else {player.doSkill(false);}
        if(spacePressed == false && lastSpacePressed == true)
        {
            player.pickUp(true);
        }
        if(cPressed == false && lastCPressed == true)
        {
            camera.showCharacterStats(!camera.showCharacterStats());
        }
        if(iPressed == false && lastIPressed == true)
        {
            camera.showInventory(!camera.showInventory());
        }
        if(kPressed == false && lastKPressed == true)
        {
            camera.showSkills(!camera.showSkills());
        }
        if(pPressed == false && lastPPressed == true)
        {
            int i = 0;
            for(CircleSummoned cS : currentMap.player.summoned())
            {
                System.out.println("Sum " + i + ": " + cS.name());
                i++;
            }
        }
        if(leftMouseDown == false && lastLeftMouseDown == true)
        {
            mouse.mouseLeftClicked(true);
        } else {
            mouse.mouseLeftClicked(false);
        }
        if(rightMouseDown == false && lastRightMouseDown == true)
        {
            mouse.mouseRightClicked(true);
        } else {
            mouse.mouseRightClicked(false);
        }
        if(mouseWheelScrolled != 0){
            System.out.println("SCROLLING");
            //scroll through player skills
            if(mouseWheelScrolled > 0)
            {
                player.skills().add(player.equippedSkill());
                player.equippedSkill(player.skills().remove(0));
            }
            if(mouseWheelScrolled < 0)
            {
                player.skills().add(0,player.equippedSkill());
                player.equippedSkill(player.skills().remove(player.skills().size()-1));
            }
            mouseWheelScrolled = 0;
        }
        player.aim().x = camera.rect().x + mousePosition.x;
        player.aim().y = camera.rect().y + mousePosition.y;
        lastSpacePressed = spacePressed;
        lastCPressed = cPressed;
        lastIPressed = iPressed;
        lastKPressed = kPressed;
        lastPPressed = pPressed;
        lastLeftMouseDown = leftMouseDown;
        mouse.position().x = mousePosition.x;
        mouse.position().y = mousePosition.y;
    }
    public void updateGame(long deltaTime)
    {
        currentMap.update(deltaTime);
    }
    public void drawGame()
    {
        
        clearScreen(bgColor);
        currentMap.draw(getGraphics2D());
        getGraphics2D().drawString("" + fps, 10, 10);
        draw();
    } 
    public void switchMap(){
        //save current map
        MapManager.saveMap(currentMap);
        //add player to new map

        //set new map as current map;
        currentMap = MapManager.switchMap(currentMap);
        player.pickUp(false);
    }
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            leftMouseDown = true;
        if(e.getButton() == MouseEvent.BUTTON3)
            rightMouseDown = true;
    }
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            leftMouseDown = false;
        if(e.getButton() == MouseEvent.BUTTON3)
            rightMouseDown = false;
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    public void mouseClicked(MouseEvent e) {
    }
    public void mouseMoved(MouseEvent e) {
        mousePosition.x = e.getPoint().x;
        mousePosition.y = e.getPoint().y;
    }
    public void mouseDragged(MouseEvent e) {
        mousePosition.x = e.getPoint().x;
        mousePosition.y = e.getPoint().y;
    }    
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        mouseWheelScrolled = e.getWheelRotation();
    }
    public void keyTyped(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            isPlaying = false;
    }
    /** Handle the key-pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A)
            aPressed = true;
        if(e.getKeyCode() == KeyEvent.VK_C)
            cPressed = true;
        if(e.getKeyCode() == KeyEvent.VK_D)
            dPressed = true;
        if(e.getKeyCode() == KeyEvent.VK_I)
            iPressed = true;
        if(e.getKeyCode() == KeyEvent.VK_K)
            kPressed = true;
        if(e.getKeyCode() == KeyEvent.VK_W)
            wPressed = true;
        if(e.getKeyCode() == KeyEvent.VK_S)
            sPressed = true;
        if(e.getKeyCode() == KeyEvent.VK_P)
            pPressed = true;
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
            spacePressed = true;
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            isPlaying = false;
        System.out.println("KeyPressed");
    }
    /** Handle the key-released event from the text field. */
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A)
            aPressed = false;
        if(e.getKeyCode() == KeyEvent.VK_C)
            cPressed = false;
        if(e.getKeyCode() == KeyEvent.VK_D)
            dPressed = false;
        if(e.getKeyCode() == KeyEvent.VK_I)
            iPressed = false;
        if(e.getKeyCode() == KeyEvent.VK_K)
            kPressed = false;
        if(e.getKeyCode() == KeyEvent.VK_W)
            wPressed = false;
        if(e.getKeyCode() == KeyEvent.VK_S)
            sPressed = false;
        if(e.getKeyCode() == KeyEvent.VK_P)
            pPressed = false;
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
            spacePressed = false;
    }

    private class aPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            aPressed = true;
        }
    }
    private class aReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            aPressed = false;
        }
    }
    private class cPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            cPressed = true;
        }
    }
    private class cReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            cPressed = false;
        }
    }
    private class dPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            dPressed = true;
        }
    }
    private class dReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            dPressed = false;
        }
    }
    private class iPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            iPressed = true;
        }
    }
    private class iReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            iPressed = false;
        }
    }
    private class kPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            kPressed = true;
        }
    }
    private class kReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            kPressed = false;
        }
    }
    private class wPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            wPressed = true;
        }
    }
    private class wReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            wPressed = false;
        }
    }
    private class sPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            sPressed = true;
        }
    }
    private class sReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            sPressed = false;
        }
    }
    private class pPressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            pPressed = true;
        }
    }
    private class pReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            pPressed = false;
        }
    }
    private class spacePressed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            spacePressed = true;
        }
    }
    private class spaceReleased extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            spacePressed = false;
        }
    }
}
