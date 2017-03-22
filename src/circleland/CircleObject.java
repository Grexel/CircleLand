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
    protected Point2D.Double velocity,aim;
    public void aim(Point2D.Double point){aim = point;}
    public Point2D.Double aim(){ return aim;}
    public void velocity(Point2D.Double point){velocity = point;}
    public Point2D.Double velocity(){ return velocity;}
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
        if(rect.rect().contains(position)){
            return true;
        }
        return this.size()/2 > distanceBetweenPoints(this.position(),closestPointToRectangle(rect));
    }
    public Point2D.Double closestPointToRectangle(RectangleObject rect){
        double closestX = this.position().getX();
        double closestY = this.position().getY();
        if(this.position().getX() < rect.rect().getX()){
            closestX = rect.rect().getX();
        }
        else if(this.position().getX() > rect.rect().getX() + rect.rect().getWidth()){
            closestX = rect.rect().getX() + rect.rect().getWidth();
        }
        if(this.position().getY() < rect.rect().getY()){
            closestY = rect.rect().getY();
        }
        else if(this.position().getY() > rect.rect().getY() + rect.rect().getHeight()){
            closestY = rect.rect().getY() + rect.rect().getHeight();
        }
        return new Point2D.Double(closestX,closestY);
        
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
    public void reverseCollideWithPoint(Point2D.Double point){
        if(intersectsCircle(point,0))
        {
                double dX = position.x - point.x;
                double dY = position.y - point.y;
                double cSide = distanceBetweenPoints(point, position);
                double mX = size/2 * dX / cSide;
                double mY = size/2 * dY / cSide;
                position.x = point.x + mX;
                position.y = point.y + mY;
        }

    }
    public void reverseCollideWithRectangleEntity(RectangleObject rect){
        if(intersectsRectangle(rect)){
            Point2D.Double closest = closestPointToRectangle(rect);
            if(rect.rect().contains(position)){
                position.x -= velocity.x;
                position.y -= velocity.y;
            }
            reverseCollideWithPoint(closest);
        }
    }
}