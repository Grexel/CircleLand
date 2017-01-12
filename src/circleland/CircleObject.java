/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circleland;

import java.awt.geom.Point2D;

/**
 *
 * @author Jeff
 */
public class CircleObject {
    protected Point2D.Double position;
    public Point2D.Double position(){return position;}
    public void position(Point2D.Double pos){position = pos;}
    protected String name;
    public String name(){return name;}
    public void name(String s){name = s;}
    protected double size;
    public double size(){return size;}
    public void size(double s){size = s;}
    
    public CircleObject(){
        position = new Point2D.Double();
        name = "";
        size = 10;
    }
    
    public boolean intersectsCircle(CircleObject circle)
    {
        double distance = distanceBetweenPoints(position, circle.position());
        if(distance < size/2 + circle.size()/2)
            return true;
        return false;
    }
    public boolean intersectsRectangle(RectangleObject rect)
    {
        double distance = distanceBetweenPoints(position,new Point2D.Double(rect.rect().getCenterX(),rect.rect().getCenterY()));
        double dX = rect.rect().getCenterX() - rect.rect().x;
        double dY = rect.rect().getCenterY() - rect.rect().y;
        
        double outerRadius = Math.sqrt(dX*dX + dY*dY);
        if (distance > outerRadius + size/2 ) return false;
        if (distance < dX + size/2)  return true;//dX is the inner radius, given that this rect is really a square
        double dCRX = rect.rect().getCenterX() - position.x;
        //also A side
        double dCRY = rect.rect().getCenterY() - position.y;
        double cSide = distance;
        double pointX = position.x + (size/2)*dCRX/distance;//x that circle outside lands on rect
        double pointY = position.y + (size/2)*dCRY/distance;//y that circle outside lands on rect
        if(rect.rect().contains(new Point2D.Double(pointX, pointY))){
            return true;
        }
        return false;
    }
        
    public static double distanceBetweenPoints(Point2D.Double p1, Point2D.Double p2)
    {
        return Math.sqrt(Math.pow(p2.x - p1.x,2)+ Math.pow(p2.y - p1.y,2));
    }
    public double clamp(double value, double min, double max){
        if (value < min)
            return min;
        else if (value > max)
            return max;
        return value;
    }   
    public boolean intersectsCircle(Point2D.Double center, double radius)
    {
        double distance = distanceBetweenPoints(position, center);
        if(distance < size/2 + radius)
            return true;
        return false;
    }
    public boolean collideMouse(CircleMouse mouse){
        
        double distance = distanceBetweenPoints(position, mouse.returnIngamePoint());
        if(distance < size/2)
            return true;
        return false;
    }
    public void collideWithCircleEntity(CircleObject cE)
    {
            if(cE != this)
            {
                //Fully pushes other circle away
                //I like this one the most
                if(intersectsCircle(cE))
                {
                    //push both back along
                    //also B side
                    double dX = cE.position().x - position.x;
                    //also A side
                    double dY = cE.position().y - position.y;
                    double cSide = distanceBetweenPoints(cE.position(), position);
                    double mX = (size/2 + cE.size()/2) * dX / cSide;
                    double mY = (size/2 + cE.size()/2) * dY / cSide;
                    cE.position().x = position.x + mX;
                    cE.position().y = position.y + mY;
                }
                /*
                //Pushes out from center of mass
                //doesn't work the best
                if(intersectsCircle(cE))
                {
                    //push both back along
                    //also B side
                    double dX = cE.position().x - position.x;
                    //also A side
                    double dY = cE.position().y - position.y;
                    //Center pivot
                    double cX = position.x + dX/2;
                    double cY = position.y + dY/2;
                    double cSide = distanceBetweenPoints(cE.position(), position);
                    double mX = (size/2) * dX / cSide;
                    double mY = (size/2) * dY / cSide;
                    position.x = cX - mX;
                    position.y = cY - mY;
                    mX = (cE.size/2) * dX / cSide;
                    mY = (cE.size/2) * dY / cSide;
                    cE.position().x = cX + mX;
                    cE.position().y = cX + mY;
                }
                */
            }
    }
    public void reverseCollideWithCircleEntity(CircleObject cE){
        if(cE != this)
        {
            //Fully pushes other circle away
            //I like this one the most
            if(intersectsCircle(cE))
            {
                double dX = position.x - cE.position().x;
                double dY = position.y - cE.position().y;
                double cSide = distanceBetweenPoints(cE.position(), position);
                double mX = (size/2 + cE.size()/2) * dX / cSide;
                double mY = (size/2 + cE.size()/2) * dY / cSide;
                position.x = cE.position().x + mX;
                position.y = cE.position().y + mY;
                /*
                double dX = cE.position().x - position.x;
                double dY = cE.position().y - position.y;
                double cSide = distanceBetweenPoints(cE.position(), position);
                double mX = (size/2 + cE.size()/2) * dX / cSide;
                double mY = (size/2 + cE.size()/2) * dY / cSide;
                cE.position().x = position.x + mX;
                cE.position().y = position.y + mY;
                */
            }
        }
    }
    public void reverseCollideWithRectangleEntity(RectangleObject rect){
        double rectX = rect.rect().x;
        double rectY = rect.rect().y;
        
        //get closest point
        double closestX = clamp(position.x,rectX,rectX + rect.rect().width);
        double closestY = clamp(position.y,rectY,rectY + rect.rect().height);
        //need to collide from corners first
        boolean collideCorner = false;
        if(closestX == rectX && closestY == rectY)
               collideCorner = true;
        if(closestX == rectX && closestY == rectY + rect.rect().height)
               collideCorner = true;
        if(closestX == rectX + rect.rect().width && closestY == rectY)
               collideCorner = true;
        if(closestX == rectX + rect.rect().width && closestY == rectY + rect.rect().height)
               collideCorner = true;
        if(collideCorner)
        {
            Point2D.Double rectPoint = new Point2D.Double(rect.rect().getCenterX(),rect.rect().getCenterY());
            
            double dXR = rectPoint.x - rect.rect().x;
            double dYR = rectPoint.y - rect.rect().y;
            double outerRadius = Math.sqrt(dXR*dXR + dYR*dYR);
            double dX = position.x - rect.rect().getCenterX();
            double dY = position.y - rect.rect().getCenterY();
            double cSide = distanceBetweenPoints(rectPoint, position);
            double mX = (size/2 + outerRadius) * dX / cSide;
            double mY = (size/2 + outerRadius) * dY / cSide;
            position.x = rectPoint.x + mX;
            position.y = rectPoint.y + mY;
            return;
        }
        if(closestX > position.x)   //collide from leftside
        {
            position.y = closestY;
            position.x = closestX - size/2;
        }
        if(closestX < position.x) // collide from rightside
        {
            position.y = closestY;
            position.x = closestX + size/2;            
        }
        if(closestY > position.y) // collide from top
        {
            position.y = closestY - size/2;
            position.x = closestX;
        }
        if(closestY < position.y) // collide from bottom
        {
            position.y = closestY + size/2;
            position.x = closestX;            
        }
    }
}