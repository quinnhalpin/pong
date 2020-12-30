
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
    public static Dimension d;
    private Point bottomLeft; 
    private String name;

    public Player(Dimension d) {
        this.d = d;
        this.bottomLeft = new Point(0, 0);
    }
    
    public Player(Dimension d, Point center) {
        this.d = d;
        setCenter(center);
    }
    
    public void setCenter(Point center) {
        int newX = d.width/2;
        int newY = d.height/2; 
        System.out.println(newX);      
        System.out.println(center.x);
                
        this.bottomLeft = new Point(center.x - newX, center.y-newY);       
    }
    
    public Point getCenter() {
        int newX = d.width/2;
        int newY = d.height/2;
        System.out.println(newX);      
        System.out.println(bottomLeft.x);
        return new Point( bottomLeft.x + newX, bottomLeft.y + newY);   
    }

    public void setName(String n) {
        this.name = n;
    }

    public String getName() {
        return this.name;
    }
    
    public Point getBottomLeft() {
        return this.bottomLeft;
    }
    
    public void setBottomLeft(Point p) {
        this.bottomLeft = p;
    }
    
    
}
