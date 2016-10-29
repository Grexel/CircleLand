/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland.Display;

import circleland.RectangleObject;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Position is the bottom center point of text box, so that it may appear over Entities' heads.
 * @author Jeff
 */
public class ChatBox{
    private static final int MAXWIDTH = 300;
    protected Point2D.Double position;
    public Point2D.Double position(){return position;}
    public void position(Point2D.Double pos){position = pos;}
    
    private String displayText;
    private ArrayList<String> lines;
    private int displayHeight;
    private int textHeight;
    public ChatBox(int xPos, int yPos, String txt){
        position = new Point2D.Double(xPos,yPos);
        displayText = txt;
        displayHeight = 0;
        textHeight = 0;
        lines = new ArrayList<>();
    }
    public ChatBox(Point2D.Double p, String txt){
        position = p;
        displayText = txt;
        displayHeight = 0;
        textHeight = 0;
        lines = new ArrayList<>();
    }
    public void draw(Graphics2D graphics){
        wrapText(displayText,graphics.getFontMetrics());
        graphics.setColor(Color.BLACK);
        graphics.fillRect((int)position.x - MAXWIDTH/2, (int)position.y - displayHeight, (int)MAXWIDTH, (int)displayHeight + 5);
        graphics.setColor(Color.WHITE);
        int yPos = (int)position.y - displayHeight + textHeight;
        for(String s : lines){
            graphics.drawString(s,(int)position.x- MAXWIDTH/2 +5, (int)yPos);
            yPos += textHeight;
        }
    }
    public void wrapText(String str,FontMetrics fm){
        textHeight = fm.getHeight();
        lines.clear();
        String[] words = displayText.split(" ");
        int wordIndex = 0;
        while(wordIndex < words.length){
            String newLine = words[wordIndex];
            wordIndex++;
            while(wordIndex < words.length && fm.stringWidth(newLine + " " + words[wordIndex]) < MAXWIDTH){
                newLine += " " + words[wordIndex];
                wordIndex++;
            }
            lines.add(newLine);
        }
        displayHeight = lines.size() * textHeight;
    }
}
