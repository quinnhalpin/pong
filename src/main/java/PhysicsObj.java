/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author halpin
 */
public class PhysicsObj {
    protected Point pos;
    protected Vec vel;
    protected Vec acc;
    private final double maxAbsVel;
    private final double maxAbsAcc;
    private final double accDecay;    
    private final double velDecay;


    public PhysicsObj(double v, double a, double decayAcc, double decayVel) {
        maxAbsVel = v;
        maxAbsAcc = a;
        accDecay = decayAcc;
        velDecay = decayVel;
	pos = new Point(0, 0);
	vel = new Vec(0, 0);
	acc = new Vec(0, 0);
    }

    public Point getPos() {
	return pos;
    }

    public void setPos(int x, int y) {
	pos = new Point(x, y);
    }
    
    public void setPos(Point p) {
	pos = p;
    }

    public Vec getVel() {
	return vel;
    }

    public void setVel(Vec v) {
        vel = new Vec(v.getDegrees(), Math.min(maxAbsVel, v.getMagnitude()));
    }

    public Vec getAcc() {
	return acc;
    }

    public void setAcc(Vec a) {
	acc = new Vec(a.getDegrees(), Math.min(maxAbsAcc, a.getMagnitude()));
    }
    
    public void step() {
        
        // Seems to break all fo the players
//        Point newCenter = getVel().step(getPos());
//        setPos(newCenter);
        
        Vec newVel = Vec.add(vel, acc);
        System.out.println("Vel" + vel.toString());
        System.out.println("Acc" + acc.toString());
        
        setVel(newVel);
        pos = vel.step(pos);
        
        // Use a fric force acc
        double newMagn = Math.max(0, acc.getMagnitude()-accDecay);
        setAcc(new Vec(acc.getDegrees(), newMagn));
        
        double newVelMagn = Math.max(0, vel.getMagnitude()-velDecay);
        System.out.println("Acc" + acc.toString());
        setVel(new Vec(vel.getDegrees(), newVelMagn));
        
        
        // Balls
//        Point newCenter = model.ball.getVel().step(model.ball.getCenter());
//        model.ball.setCenter(newCenter);
        
    }
}
