/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainScreens;

import java.awt.geom.Point2D;

/**
 *
 * @author Jeff
 */
public class InputStore {
    private boolean leftMouseDown = false, lastLeftMouseDown = false, wPressed = false, aPressed = false, sPressed = false,
            dPressed = false,spacePressed = false,lastSpacePressed = false, rightMouseDown = false, lastRightMouseDown = false;
    private boolean cPressed = false,lastCPressed = false;
    private boolean iPressed = false,lastIPressed = false;
    private boolean kPressed = false,lastKPressed = false;
    private boolean pPressed = false,lastPPressed = false;
    private int mouseWheelScrolled;
    private Point2D.Double mousePosition;

    public InputStore() {
        this.mouseWheelScrolled = 0;
        mousePosition = new Point2D.Double(0,0);
    }
    public void endFrame(){
        lastSpacePressed = spacePressed;
        lastCPressed = cPressed;
        lastIPressed = iPressed;
        lastKPressed = kPressed;
        lastPPressed = pPressed;
        lastLeftMouseDown = leftMouseDown;
        mouseWheelScrolled = 0;
    }
    public boolean isLeftMouseDown() {
        return leftMouseDown;
    }

    public void setLeftMouseDown(boolean leftMouseDown) {
        this.leftMouseDown = leftMouseDown;
    }

    public boolean isLastLeftMouseDown() {
        return lastLeftMouseDown;
    }

    public void setLastLeftMouseDown(boolean lastLeftMouseDown) {
        this.lastLeftMouseDown = lastLeftMouseDown;
    }

    public boolean iswPressed() {
        return wPressed;
    }

    public void setwPressed(boolean wPressed) {
        this.wPressed = wPressed;
    }

    public boolean isaPressed() {
        return aPressed;
    }

    public void setaPressed(boolean aPressed) {
        this.aPressed = aPressed;
    }

    public boolean issPressed() {
        return sPressed;
    }

    public void setsPressed(boolean sPressed) {
        this.sPressed = sPressed;
    }

    public boolean isdPressed() {
        return dPressed;
    }

    public void setdPressed(boolean dPressed) {
        this.dPressed = dPressed;
    }

    public boolean isSpacePressed() {
        return spacePressed;
    }

    public void setSpacePressed(boolean spacePressed) {
        this.spacePressed = spacePressed;
    }

    public boolean isLastSpacePressed() {
        return lastSpacePressed;
    }

    public void setLastSpacePressed(boolean lastSpacePressed) {
        this.lastSpacePressed = lastSpacePressed;
    }

    public boolean isRightMouseDown() {
        return rightMouseDown;
    }

    public void setRightMouseDown(boolean rightMouseDown) {
        this.rightMouseDown = rightMouseDown;
    }

    public boolean isLastRightMouseDown() {
        return lastRightMouseDown;
    }

    public void setLastRightMouseDown(boolean lastRightMouseDown) {
        this.lastRightMouseDown = lastRightMouseDown;
    }

    public boolean iscPressed() {
        return cPressed;
    }

    public void setcPressed(boolean cPressed) {
        this.cPressed = cPressed;
    }

    public boolean isLastCPressed() {
        return lastCPressed;
    }

    public void setLastCPressed(boolean lastCPressed) {
        this.lastCPressed = lastCPressed;
    }

    public boolean isiPressed() {
        return iPressed;
    }

    public void setiPressed(boolean iPressed) {
        this.iPressed = iPressed;
    }

    public boolean isLastIPressed() {
        return lastIPressed;
    }

    public void setLastIPressed(boolean lastIPressed) {
        this.lastIPressed = lastIPressed;
    }

    public boolean iskPressed() {
        return kPressed;
    }

    public void setkPressed(boolean kPressed) {
        this.kPressed = kPressed;
    }

    public boolean isLastKPressed() {
        return lastKPressed;
    }

    public void setLastKPressed(boolean lastKPressed) {
        this.lastKPressed = lastKPressed;
    }

    public boolean ispPressed() {
        return pPressed;
    }

    public void setpPressed(boolean pPressed) {
        this.pPressed = pPressed;
    }

    public boolean isLastPPressed() {
        return lastPPressed;
    }

    public void setLastPPressed(boolean lastPPressed) {
        this.lastPPressed = lastPPressed;
    }

    public int getMouseWheelScrolled() {
        return mouseWheelScrolled;
    }

    public void setMouseWheelScrolled(int mouseWheelScrolled) {
        this.mouseWheelScrolled = mouseWheelScrolled;
    }

    public Point2D.Double getMousePosition() {
        return mousePosition;
    }

    public void setMousePosition(Point2D.Double mousePosition) {
        this.mousePosition = mousePosition;
    }
    
    
}
