package circleland;

// DrawingPanel.java by John Phillips on 8/19/2014
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;

/**
 * The basic drawing application with one panel containing a buffered drawing
 * area. Extend this class and draw on the Graphics2D offscreen object. Then
 * call the show(0) method to copy the offscreen buffer to the onscreen buffer
 * which will make your work visible.
 * 
 * I borrowed some ideas on double buffering and the show and draw methods from:
 * StdDraw.java at http://introcs.cs.princeton.edu/java/stdlib/
 *
 * @author John Phillips
 * @version 0.30
 */
public class DrawingPanel extends JPanel implements ComponentListener{

    /**
     *
     */
    protected static JFrame frame;

    /**
     * The width of the drawing panel in pixels.
     */
    public static int PANEL_WIDTH = 800;

    /**
     * The height of the drawing panel in pixels.
     */
    public static int PANEL_HEIGHT = 600;

    /**
     *
     */
    protected JPanel panel;

    /**
     *
     */
    protected BufferedImage bufImage1;

    /**
     *
     */
    protected BufferedImage bufImage2;

    /**
     *
     */
    protected  Graphics2D onscreen;

    /**
     *
     */
    protected Graphics2D offscreen;

    /**
     *
     */
    protected boolean wait = false;

    private  JLabel myDrawingLbl;

    /**
     * Constructor to create a new double buffered drawing application.
     */
    public DrawingPanel() {
        super();
        this.panel = this;
        bufImage1 = new BufferedImage(PANEL_WIDTH, PANEL_HEIGHT,
                BufferedImage.TYPE_INT_ARGB);
        bufImage2 = new BufferedImage(PANEL_WIDTH, PANEL_HEIGHT,
                BufferedImage.TYPE_INT_ARGB);
        offscreen = bufImage1.createGraphics();
        onscreen = bufImage2.createGraphics();
        myDrawingLbl = new JLabel(new ImageIcon(bufImage2));

        this.setOpaque(true);
        this.setLayout(new BorderLayout());
        this.add(myDrawingLbl, BorderLayout.CENTER);

        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        offscreen.addRenderingHints(hints);
/*
        frame = new JFrame("Circle Land TM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setContentPane(panel);
        frame.pack();
        frame.setLocation(100, 100);
        frame.setVisible(true);
        */
    }

    /**
     * Set the background color for the drawing. This will erase anything
     * already painted on the panel.
     *
     * @param bgcolor
     */
    public void setMyPanelColor(Color bgcolor) {
        offscreen.setColor(bgcolor);
        offscreen.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
    }

    public Color getMyPanelColor() {
        return offscreen.getBackground();
    }
    public Graphics2D getGraphics2D(){return offscreen;}
    public void clearScreen(){
        offscreen.setColor(Color.BLACK);
        offscreen.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        
    }
    public void clearScreen(Color c)    {
        offscreen.setColor(c);
        offscreen.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        
    }
    public void setMyColor(Color color) {
        offscreen.setColor(color);
    }

    public Color getMyColor() {
        return offscreen.getColor();
    }

    /**
     * Prepare to show the current offscreen image after the given time delay in
     * milliseconds.
     *
     * @param timeDelay in milliseconds
     */
    public void show(int timeDelay) {
//        wait = false;
//        if (timeDelay >= 0) {
//            draw();
//        }
//        if (timeDelay > 0) {
//            try {
//                Thread.sleep(timeDelay);
//            } catch (InterruptedException e) {
//                System.out.println("Error in show");
//            }
//            wait = true;
//        }
    }

    /**
     * Copy the offscreen image to the on-screen one, repaint the screen, and
     * then draw the turtle in its current position.
     *
     */
    public void draw() {
//        if (wait) {
//            return;
//        }
        onscreen.drawImage(bufImage1, 0, 0, null);
        //frame.repaint();
    }

    /**
     * Print the given string on the console.
     *
     * @param s string to print
     */
    public void print(String s) {
        System.out.println(s);
    }

    /**
     * Draw the given string on the screen at the current turtle position.
     *
     * @param s string to draw
     */
    public void say(String s) {
        //say("C1N2 by Student Name", 20, PANEL_HEIGHT - 20, 14, Color.red);
        say(s, 20, PANEL_HEIGHT - 20, 14, Color.red);
    }

    /**
     * Draw the given string on the screen at the given x,y coordinates.
     *
     * @param s string to draw
     * @param x x coordinate
     * @param y y coordinate
     */
    public void say(String s, int x, int y) {
        offscreen.drawString(s, x, y);
        show(0);
    }

    /**
     * Draw the given string on the screen at the given x,y coordinates and in
     * the given stroke.
     *
     * @param s message to draw
     * @param x x coordinate
     * @param y y coordinate
     * @param fontSize in points
     */
    public void say(String s, int x, int y, int fontSize) {
        Font font = new Font("Courier", Font.PLAIN, fontSize);
        offscreen.setFont(font);
        say(s, x, y);
    }

    /**
     * Draw the given string on the screen at the given x,y coordinates and in
     * the given color.
     *
     * @param s message to draw
     * @param x x coordinate
     * @param y y coordinate
     * @param c color
     */
    public void say(String s, int x, int y, Color c) {
        Color oldColor = offscreen.getColor();
        offscreen.setColor(c);
        say(s, x, y);
        offscreen.setColor(oldColor);
    }

    /**
     * Draw the given string on the screen at the given x,y coordinates and in
     * the given color.
     *
     * @param s message to draw
     * @param x x coordinate
     * @param y y coordinate
     * @param fontSize in points
     * @param c color
     */
    public void say(String s, int x, int y, int fontSize, Color c) {
        Font font = new Font("Courier", Font.PLAIN, fontSize);
        offscreen.setFont(font);
        Color oldColor = offscreen.getColor();
        offscreen.setColor(c);
        say(s, x, y);
        offscreen.setColor(oldColor);
    }

    /**
     * Display a dialog box on the screen with the given message.
     *
     * @param s message to display
     */
    public void outputDialog(String s) {
        JOptionPane.showMessageDialog(frame, s, "Output Dialog",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Display an input dialog box on the screen.
     *
     * @param s message to display
     * @return String containing the value entered or null if no value
     */
    public String inputDialog(String s) {
        return JOptionPane.showInputDialog(frame, s,
                "Input Dialog", 3);
    }    
    
    
    /**
     * Draw an oval of a given width and height at x and y position
     * with color and stroke width
     * 
     *
     * @param xPos centerX of oval
     * @param yPos centerY of oval
     * @param width 
     * @param height
     * @param c
     * @param strokeWidth
     */
    public void oval(int xPos, int yPos, int width, int height, Color c, int strokeWidth ) {
        offscreen.setColor(c);
        offscreen.setStroke(new BasicStroke(strokeWidth));
        offscreen.drawOval(xPos - width / 2, yPos - height / 2, width, height);
    }
    /**
     * Draw a filled oval of a given width and height at x and y position
     * with color and stroke width
     * 
     *
     * @param xPos centerX of oval
     * @param yPos centerY of oval
     * @param width 
     * @param height
     * @param c
     * @param strokeWidth
     */
    public void filledOval(int xPos, int yPos, int width, int height, Color c, int strokeWidth ) {
        offscreen.setColor(c);
        offscreen.setStroke(new BasicStroke(strokeWidth));
        offscreen.drawOval(xPos - width / 2, yPos - height / 2, width, height);
    }
    
    /**
     * Draw a rectangle of a given width and height 
     * with x and y the top left point of the rectangle
     *
     * @param xPos top left x of desired rectangle
     * @param yPos top left y of desired rectangle
     * @param width width of rectangle
     * @param height height of rectangle
     * @param c color of desired rectangle
     * @param strokeWidth width of rectangle lines
     */
    public void rectangle(int xPos, int yPos, int width, int height, Color c, int strokeWidth) {
        offscreen.setColor(c);
        offscreen.setStroke(new BasicStroke(strokeWidth));
        offscreen.drawRect(xPos, yPos, width, height);
    }
    /**
     * Draw a filled rectangle of a given width and height 
     * with x and y the top left point of the rectangle
     * 
     *
     * @param xPos top left x of desired rectangle
     * @param yPos top left y of desired rectangle
     * @param width width of rectangle
     * @param height height of rectangle
     * @param c color of desired rectangle
     * @param strokeWidth width of rectangle lines
     */
    public void filledRectangle(int xPos, int yPos, int width, int height, Color c, int strokeWidth) {
        offscreen.setColor(c);
        offscreen.setStroke(new BasicStroke(strokeWidth));
        offscreen.fillRect(xPos, yPos, width, height);
    }
     /**
     * Draw a line of the given length along the current heading.
     *
     * @param p1 starting point of line
     * @param p2 end point of line
     * @param c Color of line
     * @param strokeWidth width of line
     */
    public void line(Point p1,Point p2,Color c, int strokeWidth) {
        offscreen.setColor(c);
        offscreen.setStroke(new BasicStroke(strokeWidth));
        offscreen.drawLine(p1.x, p1.y, p2.x, p2.y);
    }
    public void componentHidden(ComponentEvent e) {
    }
    public void componentMoved(ComponentEvent e) {
    }
    public void componentResized(ComponentEvent e) {
        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        this.remove(myDrawingLbl);
        int newWidth = r.width;
        int newHeight = r.height;
//        int newWidth = frame.getBounds().getSize().width;
//        int newHeight = frame.getBounds().getSize().height;
        PANEL_WIDTH = newWidth;
        PANEL_HEIGHT = newHeight;
        bufImage1 = new BufferedImage(newWidth, newHeight,
                BufferedImage.TYPE_INT_ARGB);
        bufImage2 = new BufferedImage(newWidth, newHeight,
                BufferedImage.TYPE_INT_ARGB);
        offscreen = bufImage1.createGraphics();
        onscreen = bufImage2.createGraphics();
        myDrawingLbl = new JLabel(new ImageIcon(bufImage2));
        this.setOpaque(true);
        this.add(myDrawingLbl, BorderLayout.CENTER);
        
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        offscreen.addRenderingHints(hints);

        //this.setSize(frame.getBounds().getSize());
        this.setBounds(frame.getBounds());
        this.validate();
    }
    public void componentShown(ComponentEvent e) {
    }
}
