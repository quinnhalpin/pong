/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author halpin
 */
public class Ball {
   public final int radius;
   public Point center;

    public Ball(int r, Point c) {
        radius = r;
        center = c;
    }
    
    public Point getBottomLeft() {
        return new Point(center.x - radius, center.y - radius);
    }
}
