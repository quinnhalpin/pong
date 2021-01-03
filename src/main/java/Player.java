
import java.awt.Dimension;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author halpin
 */
public class Player extends PhysicsObj{
    private static Dimension d;
    private String name;

    public Player(Dimension d) {
        super(25, 3, 0.5, 0.05);
        this.d = d;
        pos = new Point(0, 0);
    }
    
    public Player(Dimension d, Point center) {
        super(25, 3, 0.3, 0.05);
        this.d = d;
        setCenter(center);
    }
    
    /**
     * max magnitude of step in a time unit
     * @return int
     */
    public int getMaxStep() {
        return 10;
    }
    
    public void setCenter(Point center) {
        pos = center;
    }
    
    public Point getCenter() {
        return pos;
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getName() {
        return this.name;
    }
    
    public Point getBottomLeft() {
        Point bottomLeft = new Point(pos.x - getWidth()/2, pos.y-getHeight()/2);     
        return bottomLeft;
    }
    
    public void setBottomLeft(Point p) {
        int newX = d.width/2;
        int newY = d.height/2; 
        pos = new Point(pos.x + newX, pos.y + newY); 
    }
    
    public int getLeftEdge() {
        return getCenter().x - getWidth()/2;
    }
    
    public int getRightEdge() {
        return getCenter().x + getWidth()/2;
    }
    
    public int getTopEdge() {
        return getCenter().y - getHeight()/2;
    }
    
    public int getBottomEdge() {
        return getCenter().y + getHeight()/2;
    }
    
    public int getWidth() {
        return d.width;
    }
    
    public int getHeight() {
        return d.height;
    }
    
//    /**
//     * stepSize > 0 means move up (bottom of board)
//     * stepSize < 0 means move down (top of board)
//     * 
//     * |stepSize| is amount to move center by vertically
//     * @param stepSize
//     */
//    public void moveVertically(int stepSize) {
//        Point center = getCenter();
//        Point newCenter = new Point(center.x, center.y + stepSize);
//        setCenter(newCenter);
//    }
//    
//    @Override
//    public void step() {
//        Vec newVel = Vec.add(vel, acc);
//        vel = newVel;
//        pos = vel.step(pos);
//    }
}
